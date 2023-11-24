package com.team2.payment.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.team2.user.DB.NonUserDTO;
import com.team2.user.DB.UserDTO;

public class OrderDAO {
	// 공용변수 선언 // 클래스 안 인스턴스변수(멤버변수)
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 메서드 선언 -> 수행하는 동작

	// DB 연결 메서드 - getConnect()

	// 커넥션풀을이용한 getConnect 메서드
	public Connection getConnect() throws Exception {
		System.out.println("DAO : getConnect()실행");
		System.out.println("DAO : 1,2단계를 한번에처리 -> 연결정보 생성");
		System.out.println("DAO : 커넥션풀(CP)을 사용");

		// 디비연결정보 가져오기 (META-INF/context.xml)
		// Context -> 인터페이스 != 클래스 즉 인터페이스 객체생성x
		// 업캐스팅은 가능! 즉 상속관계

		// 프로젝트 정보 초기화
		Context initCTX = new InitialContext();

		// context.xml 파일(jdbc/jsp 이름)접근
		// DataSource 타입으로 변경
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/team2");

		// 연결정보 객체를 사용해서 디비 연결
		con = ds.getConnection();

		return con;
	}

	// DB 자원해제 메서드 - closeDB()
	// DB 작업시 필수 동작
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			System.out.println("DAO : 자원해제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 지역에 해당하는 극장정보 리스트
	public List<CinemaDTO> getCinema(String region1, String region2) {
		// 극장리스트 저장하는객체
		List<CinemaDTO> cinemaList = new ArrayList<CinemaDTO>();
		String str = region1 + "|" + region2;
		System.out.println("str: " + str);
		try {
			con = getConnect();
			sql = "select cinema_name from cinema where regexp_like(region, ?); ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CinemaDTO dto = new CinemaDTO();
				dto.setCinema_name(rs.getString(1));
				cinemaList.add(dto);
			}
			System.out.println("DAO : 리스트사이즈 : " + cinemaList.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return cinemaList;
	}
	
	// 선택극장에 상영중인 영화정보 리스트
	public List<ScreenDTO> getMovieName(String thaeter, String date) {
		List<ScreenDTO> movieList = new ArrayList<ScreenDTO>();
		
		String str = '%'+date+'%';	
		try {
			con = getConnect();
			sql = "select distinct movie_name from screening where cinema_name=? and screening_date like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, thaeter);
			pstmt.setString(2, str);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScreenDTO dto = new ScreenDTO();
				dto.setMovie_name(rs.getString(1));
				movieList.add(dto);
			}
			System.out.println("DAO 리스트사이즈 : " + movieList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return movieList;
	}
	
	// 극장에 상영중인 영화의 상영시간 리스트
	public List<ScreenDTO> getMovieTime(String movie, String theater) {

		List<ScreenDTO> mTimeList = new ArrayList<>();

		try {
			con = getConnect();
			sql = "select screening_time, price from screening where movie_name=? and cinema_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movie);
			pstmt.setString(2, theater);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScreenDTO dto = new ScreenDTO();
				dto.setScreening_time(rs.getString(1));
				dto.setPrice(rs.getInt(2));
				mTimeList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return mTimeList;
	}

	// 극장에 해당하는 지역 출력 메서드
	public String getRegion(String cinema) {
		System.out.println("DAO cinema : " + cinema);
		String region = null;
		try {
			con = getConnect();
			sql = "select region from cinema where cinema_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cinema);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				region = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return region;
	}

	// 상영관의 팔린 좌석정보 메서드
	public List<OrderDTO> getSeat(String cinema, String time) {
		List<OrderDTO> list = new ArrayList<>();
		try {
			con = getConnect();
			String sTime = "%" + time + "%";
			sql = "select seat from order_board where cinema_name=? and screening_time like ? order by 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cinema);
			pstmt.setString(2, sTime);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setSeat(rs.getString(1));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원이 예매결제할시 필요한 회원이름, 회원휴대폰번호, 회원번호 가져오는 메서드
	public UserDTO getMemberInfo(String user_id) {
		System.out.println(" DAO user_id : "+user_id);
		UserDTO dto = new UserDTO();
		try {
			con = getConnect();
			sql = "select user_name, user_phone, user_num from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setUser_name(rs.getString(1));
				dto.setUser_phone(rs.getString(2));
				dto.setUser_num(rs.getInt(3));
				System.out.println("DAO user_num : "+rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	}
	
	// 비회원이 예매결제시 필요한 비회원이름, 비회원휴대폰번호 가져오는 메서드
	public NonUserDTO getNonuserInfo(String nonuser_id) {
		NonUserDTO dto = new NonUserDTO();
		try {
			con = getConnect();
			sql = "select nonuser_name, nonuser_id from non_user where nonuser_phone=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nonuser_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNonuser_name(rs.getString(1));
				dto.setNonuser_id(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	// payment 결제완료 메서드
	public void successPayment(String payment_id, int order_id, String pg,
			String payment_method, int price, String name, String phone, String movie_name) {
		try {
			con = getConnect();
			sql = "insert into payment values(?,?,0,now(),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, payment_id);
			pstmt.setInt(2, order_id);
			pstmt.setString(3, pg);
			pstmt.setString(4, payment_method);
			pstmt.setString(5, movie_name);
			pstmt.setInt(6, price);
			pstmt.setString(7, name);
			pstmt.setString(8, phone);
			pstmt.executeUpdate();
			System.out.println("DAO : 결제테이블 정보생성완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// 회원일시 예매정보 insert 테이블
	public void successUserOrder(int order_id,int user_num,String region,String cinema,
			String seat,String movie_name,String time,String car_type,String car_num) {
		try {
			con = getConnect();
			sql = "insert into order_board (order_id,user_num,region,cinema_name,seat,movie_name,"
					+ "order_date,order_state,screening_time,car_type,car_num)"
					+ "values(?,?,?,?,?,?,now(),0,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_id);
			pstmt.setInt(2, user_num);
			pstmt.setString(3, region);
			pstmt.setString(4, cinema);
			pstmt.setString(5, seat);
			pstmt.setString(6, movie_name);
			pstmt.setString(7, time);
			pstmt.setString(8, car_type);
			pstmt.setString(9, car_num);
			pstmt.executeUpdate();
			System.out.println("DAO : 회원예매테이블 정보생성완료(회원)");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// 비회원일시 예매정보 insert 테이블
	public void successNonuserOrder(int order_id,String region,String cinema,
			String seat,String movie_name,String time,String car_type,String car_num) {
			
			try {
				con = getConnect();
				sql = "insert into order_board (order_id,region,cinema_name,seat,movie_name,"
						+ "order_date,order_state,screening_time,car_type,car_num)"
						+ "values(?,?,?,?,?,now(),0,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_id);
				pstmt.setString(2, region);
				pstmt.setString(3, cinema);
				pstmt.setString(4, seat);
				pstmt.setString(5, movie_name);
				pstmt.setString(6, time);
				pstmt.setString(7, car_type);
				pstmt.setString(8, car_num);
				pstmt.executeUpdate();
				System.out.println("DAO : 회원예매테이블 정보생성완료(비회원)");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
	}
	
	// 회원 max예매횟수 출력메서드
	public int getMaxOrderCount(int user_num) {
		int moc = 0;
		try {
			con = getConnect();
			sql = "select user_orderCount from user where user_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				moc = rs.getInt(1);
				System.out.println("DAO : moc : "+moc);
				return moc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	
		return moc;
	}
	
	// 회원이 예매할시 회원의 예매횟수 update 메서드
	public void updateOrderCount(int moc, int user_num) {
		int suc;
		try {
			con = getConnect();
			sql = "update user set user_orderCount=? where user_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, moc+1);
			pstmt.setInt(2, user_num);
			suc = pstmt.executeUpdate();
			System.out.println("DAO : 예매횟수증가 : "+suc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// 회원 한명의 마이페이지 예매 내역 리스트
		public List<OrderDTO> getOrderBoard(String id,int startRow,int pageSize) {
			try {
				con = getConnect();
				sql = "select p.order_id, p.payment_state, p.payment_date, p.pg, p.payment_method, p.movie_name, p.price, p.name, p.phone, o.seat, o.order_date, o.movie_name, o.car_num, o.order_state "
						+ " from order_board o join payment p "
						+ "on o.order_id = p.order_id "
						+ "where o.user_num = (select user_num from user where user_id = ?) order by o.order_date desc limit ?,? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				
				OrderDTO dto;
				List<OrderDTO> list = new ArrayList<>();
				while (rs.next()) {
					dto = new OrderDTO();
					dto.setOrder_date(rs.getDate("o.order_date"));
					dto.setMovie_name(rs.getString("o.movie_name"));
					dto.setCar_num(rs.getString("o.car_num"));
					dto.setOrder_state(rs.getInt("o.order_state"));
					dto.setOrder_id(rs.getInt("p.order_id"));
					dto.setSeat(rs.getString("o.seat"));

					list.add(dto);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				closeDB();
			}

		}
		
		//	 회원 한명의 마이페이지 예매 내역 리스트
			public List<PaymentDTO> getPayment(String id,int startRow,int pageSize) {
				try {
					con = getConnect();
					sql = "select p.order_id, p.payment_id, p.payment_state, p.payment_date, p.pg, p.payment_method, p.movie_name, p.price, p.name, p.phone, o.order_date, o.movie_name, o.car_num, o.order_state "
							+ " from order_board o join payment p "
							+ "on o.order_id = p.order_id "
							+ "where o.user_num = (select user_num from user where user_id = ?)limit ?,? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setInt(2, startRow-1);
					pstmt.setInt(3, pageSize);
					rs = pstmt.executeQuery();
					
					PaymentDTO dto;
					List<PaymentDTO> list = new ArrayList<>();
					while (rs.next()) {
						dto  = new PaymentDTO();
						dto.setPayment_id(rs.getString(2));
						dto.setPayment_state(rs.getInt(3));
						dto.setPayment_date(rs.getDate(4));
						dto.setPg(rs.getString(5));
						dto.setPayment_method(rs.getString(6));
						dto.setMovie_name(rs.getString(7));
						dto.setPrice(rs.getShort(8));
						dto.setName(rs.getString(9));
						dto.setPhone(rs.getString(10));

						list.add(dto);
					}
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					closeDB();
				}

			}
		
		
		
		// 회원의 마이페이지 게시글수
		public int getBoardCount(String id) {
			System.out.println(" DAO : 매개변수 전달 id : "+id);
			System.out.println(" DAO : getBoardCount() 호출 ");
			System.out.println(" DAO : 실행목적 : 글의 개수(int) 리턴");
			int result = 0;
			
			try {
				// 1.2. 디비연결
				con = getConnect();
				// 3. SQL 작성(select) & pstmt 객체
				sql = "select count(*) from order_board where user_num = (select user_num from user where user_id = ?)";
				pstmt = con.prepareStatement(sql);
				// 4. SQL 실행
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if(rs.next()) {
					//result = rs.getInt(1);
					result = rs.getInt("count(*)");
				}
				System.out.println(" DAO : 글의 개수 조회 완료! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
				System.out.println("DAO : "+id);
			}
			
			return result;
		}	
		
		// 회원의 마이페이지 게시글수
			public int getBoardCountJoin(String num) {
				System.out.println(" DAO : getBoardCount() 호출 ");
				System.out.println(" DAO : 실행목적 : 글의 개수(int) 리턴");
				System.out.println(" DAO 전달받은 아이디 : "+num);
				int result = 0;
				
				try {
					// 1.2. 디비연결
					con = getConnect();
					// 3. SQL 작성(select) & pstmt 객체
					sql = "select count(*) from order_board o join payment p on o.order_id = p.order_id where user_num = (select user_num from user where user_id = ?)";
					pstmt = con.prepareStatement(sql);
					// 4. SQL 실행
					pstmt.setString(1, num);
					rs = pstmt.executeQuery();
					// 5. 데이터 처리
					if(rs.next()) {
						//result = rs.getInt(1);
						result = rs.getInt("count(*)");
					}
					System.out.println(" DAO : 글의 개수 조회 완료! ");
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				return result;
			}	
		
		// 관리자페이지 예매/취소 게시글수
		public int getBoardCount() {
			System.out.println(" DAO : getBoardCount() 호출 ");
			System.out.println(" DAO : 실행목적 : 글의 개수(int) 리턴");
			int result = 0;
			
			try {
				// 1.2. 디비연결
				con = getConnect();
				// 3. SQL 작성(select) & pstmt 객체
				sql = "select count(*) from order_board";
				pstmt = con.prepareStatement(sql);
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if(rs.next()) {
					//result = rs.getInt(1);
					result = rs.getInt("count(*)");
				}
				System.out.println(" DAO : 글의 개수 조회 완료! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return result;
		}	
		
		// 모든 회원의 정보를 불러옴
		public List<PaymentDTO> getPrice(int startRow,int pageSize) {
			List<PaymentDTO> list = new ArrayList<>();
			PaymentDTO pdto;
			try {
				con = getConnect();
				//sql = "select o.order_id, o.movie_name, o.order_date, o.car_num, o.order_state, u.user_phone, u.user_name, o.screening_time, u.user_id from order_board o join user u on (o.user_num = u.user_num)limit ?,?;";
				sql = "SELECT p.payment_id, p.order_id, p.payment_state, p.payment_date, p.pg, p.payment_method, p.movie_name, p.price, p.name, p.phone "
						+ "FROM user u  JOIN order_board o ON u.user_num = o.user_num join payment p ON o.order_id = p.order_id order by o.order_date desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					pdto = new PaymentDTO();
					pdto.setPayment_id(rs.getString(1));
					pdto.setOrder_id(rs.getInt(2));
					pdto.setPayment_state(rs.getInt(3));
					pdto.setPayment_date(rs.getDate(4));
					pdto.setPg(rs.getString(5));
					pdto.setPayment_method(rs.getString(6));
					pdto.setMovie_name(rs.getString(7));
					pdto.setPrice(rs.getInt(8));
					pdto.setName(rs.getString(9));
					pdto.setPhone(rs.getString(10));
					list.add(pdto);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				closeDB();
			}

		}
		
		// 한명의 회원의 결제정보를 불러옴
		public List<PaymentDTO> getPrice(String user_id, int startRow, int pageSize) {
			List<PaymentDTO> list = new ArrayList<>();
			PaymentDTO pdto;
			try {
				con = getConnect();
				sql = "SELECT p.payment_id, p.order_id, p.payment_state, p.payment_date, p.pg, p.payment_method, p.movie_name, p.price, p.name, p.phone "
						+ "FROM user u  JOIN order_board o ON u.user_num = o.user_num join payment p ON o.order_id = p.order_id where u.user_id = ? order by o.order_date desc limit ?,? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					pdto = new PaymentDTO();
					pdto.setPayment_id(rs.getString(1));
					pdto.setOrder_id(rs.getInt(2));
					pdto.setPayment_state(rs.getInt(3));
					pdto.setPayment_date(rs.getDate(4));
					pdto.setPg(rs.getString(5));
					pdto.setPayment_method(rs.getString(6));
					pdto.setMovie_name(rs.getString(7));
					pdto.setPrice(rs.getInt(8));
					pdto.setName(rs.getString(9));
					pdto.setPhone(rs.getString(10));
					list.add(pdto);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				closeDB();
			}

		}
		
		// 모든 회원의 정보를 불러옴
		public List<UserDTO> getUserInfo(int startRow,int pageSize) {
			List<UserDTO> ulist = new ArrayList<>();
			UserDTO udto;
			try {
				con = getConnect();
				sql = "select o.order_id, o.movie_name, o.order_date, o.car_num, o.order_state, u.user_phone, u.user_name, o.screening_time, u.user_id from order_board o join user u on (o.user_num = u.user_num) order by o.order_date desc limit ?,?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					udto = new UserDTO();
					udto.setUser_id(rs.getString(9));
					udto.setUser_name(rs.getString(7));
					udto.setUser_phone(rs.getString(6));
					ulist.add(udto);
				}
				return ulist;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				closeDB();
			}

		}
		
		// 한명의 유저의 정보를 가져옴
		public List<UserDTO> getUserInfo(String user_id, int startRow, int pageSize) {
			List<UserDTO> ulist = new ArrayList<>();
			UserDTO udto;
			try {
				con = getConnect();
				sql = "select o.order_id, o.movie_name, o.order_date, o.car_num, o.order_state, u.user_phone, u.user_name, o.screening_time, u.user_id from order_board o join user u on (o.user_num = u.user_num) where u.user_id = ? order by o.order_date desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					udto = new UserDTO();
					udto.setUser_id(rs.getString(9));
					udto.setUser_name(rs.getString(7));
					udto.setUser_phone(rs.getString(6));
					ulist.add(udto);
				}
				return ulist;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				closeDB();
			}
		}
		
		// 모든 회원의 정보를 불러옴
			public List<OrderDTO> getOrderInfo(int startRow,int pageSize) {
				List<OrderDTO> list = new ArrayList<>();
				OrderDTO odto;
				try {
					con = getConnect();
					sql = "select o.order_id, o.movie_name, o.order_date, o.car_num, o.order_state, u.user_phone, u.user_name, o.screening_time, u.user_id from order_board o join user u on (o.user_num = u.user_num) order by o.order_date desc limit ?,?;";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startRow-1);
					pstmt.setInt(2, pageSize);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						odto = new OrderDTO();
						odto.setOrder_id(rs.getInt(1));
						odto.setMovie_name(rs.getString(2));
						odto.setOrder_date(rs.getDate(3));
						odto.setCar_num(rs.getString(4));
						odto.setOrder_state(rs.getInt(5));
						odto.setScreening_time(rs.getString("o.screening_time"));
					
						list.add(odto);
					}
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					closeDB();
				}

			}
			
			// 한명의 예매정보를 보여줌
			public List<OrderDTO> getOrderInfo(String user_id, int startRow, int pageSize) {
				List<OrderDTO> list = new ArrayList<>();
				OrderDTO odto;
				try {
					con = getConnect();
					sql = "select o.order_id, o.movie_name, o.order_date, o.car_num, o.order_state, u.user_phone, u.user_name, o.screening_time, u.user_id from order_board o join user u on (o.user_num = u.user_num) where u.user_id = ? order by o.order_date desc limit ?,? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user_id);
					pstmt.setInt(2, startRow-1);
					pstmt.setInt(3, pageSize);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						odto = new OrderDTO();
						odto.setOrder_id(rs.getInt(1));
						odto.setMovie_name(rs.getString(2));
						odto.setOrder_date(rs.getDate(3));
						odto.setCar_num(rs.getString(4));
						odto.setOrder_state(rs.getInt(5));
						odto.setScreening_time(rs.getString("o.screening_time"));
					
						list.add(odto);
					}
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					closeDB();
				}

			}
			
			// 결제취소시 예매테이블 예매상태변경
			public void updateOrderState(int order_id) {
				try {
					con = getConnect();
					sql = "update order_board set order_state=1 where order_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, order_id);
					pstmt.executeUpdate();
					System.out.println("예매상태 변경완료");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
			}
			
			// 결제취소시 결제테이블 결제상태변경
			public void updatePaymentState(int order_id) {
				try {
					con = getConnect();
					sql = "update payment set payment_state=1 where order_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, order_id);
					pstmt.executeUpdate();
					System.out.println("결제상태 변경완료");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
			}
}