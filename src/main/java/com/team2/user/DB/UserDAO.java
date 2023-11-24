package com.team2.user.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.team2.util.ConnectionDB;

public class UserDAO {
	ConnectionDB con = new ConnectionDB();
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	String sql;
	
	public int rogin( String id, String pw ) {
		sql = "select user_pass from user where user_id=? and user_pass=?";
		int result = 0;
		try {
			conn = con.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pw.equals(rs.getString("user_pass"))) {
				 result = 1; //로그인성공
				}else { 
				result = 0; //실패
				
			}
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			
			con.closeDB(conn, rs, pstmt);
		}
		return result;
	} // 로그인
	
	// 회원가입
	public int join(UserDTO m, String is) {
		sql = "insert into user (user_id,user_name,user_pass,user_phone,user_regdate,last_access,user_type) values(?,?,?,?,default,default,0)";

		conn = con.getConnection();
		if (!is.equals("true")) {
			return -1;
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getUser_name());
			pstmt.setString(3, m.getUser_pass());
			pstmt.setString(4, m.getUser_phone());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}

	}

	
	///////////########

	// 아이디 중복 확인
	public boolean checkId(String id) {

		sql = "select count(*) from user where user_id=?";
		conn = con.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					return false; // 아이디가 이미 존재함
				} else {
					return true; // 아이디 사용 가능
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}
	
	//아이디 찾기 findId()
	public String findId(String phone, String name) {
		String  id = null;
		sql = "select user_id from user where user_phone=? and user_name=?";
		conn  = con.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone );
			pstmt.setString(2, name);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				
				id = (rs.getString(1));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			con.closeDB(conn, rs, pstmt);
			}
		return id;
	}
	
	// 유저 정보 가져오기
	public UserDTO getUserInfo(String user_id) {
		sql = "select user_id, user_name, user_phone from user where user_id=?";
		conn = con.getConnection();
		UserDTO dto = new UserDTO();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setUser_id(rs.getString(1));
				dto.setUser_name(rs.getString(2));
				dto.setUser_phone(rs.getString(3));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}

	// 유저 정보 수정
	public int updateUserInfo(UserDTO dto) {
		conn = con.getConnection();

		try {
			conn  = con.getConnection();
			sql = "update user set user_name=?, user_phone=?, user_pass=? where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_name());
			pstmt.setString(2, dto.getUser_phone());
			pstmt.setString(3, dto.getUser_pass());
			pstmt.setString(4, dto.getUser_id());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}
	
	// 유저 비밀번호 수정
		public int updateUserPw(UserDTO dto) {
			sql = "update user set user_pass=? where user_id=?";
			conn = con.getConnection();

			try {
				conn  = con.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUser_pass());
				pstmt.setString(2, dto.getUser_id());

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				con.closeDB(conn, rs, pstmt);
			}
		}

	// 유저 삭제
	public int deleteUserInfo(UserDTO dto) {
		sql = "delete from user where user_id=? and user_pass=?";
		String sql2 = "SET foreign_key_checks = 0";

		conn = con.getConnection();

		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pass());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}

	// 관리자 유저정보페이지 리스트 가져오기
	public List<UserDTO> getAllUserInfo(int startRow, int pageSize) {
		sql = "select * from user limit ?,?";
		conn = con.getConnection();
		UserDTO dto = null;
		List<UserDTO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new UserDTO();
				dto.setUser_num(rs.getInt(1));
				dto.setUser_id(rs.getString(2));
				dto.setUser_name(rs.getString(3));
				dto.setUser_phone(rs.getString(5));
				dto.setUser_regdate(rs.getTimestamp(6));
				dto.setUser_orderCount(rs.getInt(7));
				dto.setUser_car_num(rs.getString(8));
				dto.setUser_car_type(rs.getString(9));
				dto.setLast_access(rs.getTimestamp(10));
				dto.setUser_type(rs.getInt(11));

				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}

	// 특정 회원의 정보만 가져오기
	public UserDTO getAllUserInfo(String id) {
		sql = "select * from user where user_id=?";
		conn = con.getConnection();
		UserDTO dto = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new UserDTO();
				dto.setUser_num(rs.getInt(1));
				dto.setUser_id(rs.getString(2));
				dto.setUser_name(rs.getString(3));
				dto.setUser_phone(rs.getString(5));
				dto.setUser_regdate(rs.getTimestamp(6));
				dto.setUser_orderCount(rs.getInt(7));
				dto.setUser_car_num(rs.getString(8));
				dto.setUser_car_type(rs.getString(9));
				dto.setLast_access(rs.getTimestamp(10));
				dto.setUser_type(rs.getInt(11));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}

	public int userListCount() {
		sql = "select count(*) from user";

		try {
			conn = con.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("errerM :" + e);
			return -1;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}// boardCount() end
	public int userListCount(String user_id) {
		sql = "select count(*) from user where user_id=?";
		
		try {
			conn = con.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("errerM :" + e);
			return -1;
		} finally {
			con.closeDB(conn, rs, pstmt);
		}
	}// boardCount() end
	
	// 유저 등급 바꾸기
		public int updateUserType(int user_num, int chType) {
			sql = "update user set user_type=? where user_num=?";

			conn = con.getConnection();

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, chType);
				pstmt.setInt(2, user_num);

				return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				con.closeDB(conn, rs, pstmt);
			}
		}
		
	// 비밀번호 찾기 findPw()
	public String findPw(String id) {
	String pw = "";
		sql = "select user_pass from user where user_id=?";
		conn = con.getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pw =rs.getString(1);
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.closeDB(conn, rs, pstmt);}
		return pw;
		}
			
 
	

}