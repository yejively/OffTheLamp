package com.team2.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/*
 * BoardDAO(Data Access Object)
 * : 데이터(DB)를 처리하는 객체
 * -> DB관련된 모든 처리를 수행
 * 
 */
public class QRBoardDAO {
	// 공용변수선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String sql = "";
	
	// 메서드 선언 -> 수행하는 동작
	
	// 디비 연결 메서드 - getConnect()
//	public Connection getConnect() throws Exception{
//		System.out.println(" DAO : getConnect() 실행");
//		System.out.println(" DAO : 1,2단계를 한번에 처리 -> 연결정보 생성");
//		// 디비연결정보
//		final String DRIVER = "com.mysql.cj.jdbc.Driver";
//		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
//		final String DBID = "root";
//		final String DBPW = "1234";
//		//1. 드라이브 연결
//		Class.forName(DRIVER);
//		System.out.println(" 드라이버 로드 성공");
//		
//		// 2. 디비연결
//		con = DriverManager.getConnection(DBURL, DBID, DBPW);
//		System.out.println(" 디비연결 성공");
//		
//		
//		return con;
//	}
	public Connection getConnect() throws Exception{
		System.out.println(" DAO : getConnect() 실행");
		System.out.println(" DAO : 1,2단계를 한번에 처리 -> 연결정보 생성");
		System.out.println("DAO : 커넥션풀(CP)를 사용");
		
		// 디비 연결정보를 가져오기 (/META-INF/context.xml)
		Context initCTX = new InitialContext();
		
		//context.xml 파일(jdbc/jsp6 이름)접근 -> DAtaSource 형태로 변경
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/team2");
		
		// 연결정보 객체를 사용해서 디비 연결
		con = ds.getConnection();
		
		return con;
	}
	// 디비 연결 메서드 - getConnect()
	
	// 디비 자원해제 메서드 - closeDB()
	// 디비 작업시 필수 동작
	public void closeDB() {
			try { //역순으로 종료
				if(rs != null) { rs.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(con != null) { con.close(); }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	// 디비 자원해제 메서드 - closeDB()
	
	
	
	// 글쓰기 - insertBoard();
	public void insertBoard(QRBoardDTO bb) throws Exception {
		// 사용자가 입력한 데이터를 DB에 저장
//		System.out.println(" 전달정보 : "+bb);
		System.out.println(" DAO : insertBoard(bb) 호출-시작");
//		//1. 드라이버로드
//		Class.forName(DRIVER);
//		System.out.println(" 드라이버 로드 성공");
//		//2. DB연결
//		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
//		System.out.println(" 디비 연결 성공");
		
		// 1.2. 디비연결
		con = getConnect();
		byte category = bb.getCategory();
		//3. SQL구문 작성 & pstmt 객체
		// 글번호(bno) 계산하기
	
		if(category == 0) {
			sql = "select max(qna_bno) from qna_rent_board";			
		}else{
			sql = "select max(rent_bno) from qna_rent_board";		
		}
		pstmt = con.prepareStatement(sql);
		//4. SQL구문 실행
		rs = pstmt.executeQuery();
		//5. 데이터 처리
		int bno=0;
		if(rs.next()) { // select문의 실행결과 커서가 있을때 //작성된 글이 있음
			//글번호를 저장하는 변수
			System.out.println("@@@@@@@ 게시판 글 있음 @@@@@@@");
//			bno = rs.getInt("Max(bno)")+1;
			// 1번 인덱스 컬럼의 값을 가져오기
			if(category == 0 ) {
				bno = rs.getInt("Max(qna_bno)")+1;
			}else {
				bno = rs.getInt("Max(rent_bno)")+1;				
			}
		}
		System.out.println(" 글번호 : "+bno);
		
		// 글쓰기 동작처리
		// 3. sql구문 작성 & pstmt 객체
		
		if(category == 0 ) {
			sql = "insert into qna_rent_board(category,qna_bno,user_id,"
					+ "subject,content,read_count,regdate,answer) "
					+ "values(?,?,?,?,?,?,now(),0)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setByte(1, category);
			pstmt.setInt(2, bno);
			pstmt.setString(3, bb.getUser_id());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0);// 모드글 생성시 조회수는 0
			pstmt.executeUpdate();
		}else {
			sql = "insert into qna_rent_board(category,rent_bno,user_id,"
					+ "subject,content,read_count,regdate,rent_name,"
					+ "cinema_name,rent_email,rent_phone,answer) "
					+ "values(?,?,?,?,?,?,now(),?,?,?,?,0)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setByte(1, category);
			pstmt.setInt(2, bb.getRent_bno());
			pstmt.setString(3, bb.getUser_id());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0);// 모드글 생성시 조회수는 0
			pstmt.setString(7, bb.getRent_name());
			pstmt.setString(8, bb.getCinema_name());
			pstmt.setString(9, bb.getRent_email());
			pstmt.setString(10, bb.getRent_phone());
			pstmt.executeUpdate();
			
		}
		System.out.println("글쓰기 완료");
		
		closeDB();
		System.out.println(" DAO : insertBoard(bb) 호출-끝");
	}// 글쓰기 - insertBoard();
	
	
	// 글 리스트 조회 -boardList()
	public ArrayList<QRBoardDTO> boardList() throws Exception{
		ArrayList<QRBoardDTO> boardList = new ArrayList<>();
//		List boardList = new ArrayList();//업캐스팅
		System.out.println(" DAO : boardList() 실행");

//		//1. 드라이버로드
//		Class.forName(DRIVER);
//		System.out.println("드라이버 로드 성공");
//		//2. 디비연결
//		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
//		System.out.println("디비 연결 성공");
		//1.2.디비연결
		con = getConnect();
		//3. sql구문작성 & pstmt 객체
		sql = "select * from qna_rent_board where category = 0";
		pstmt = con.prepareStatement(sql);
		//4. sql 실행
		rs = pstmt.executeQuery();
		//5. 데이터 처리하기
		// rs(select문의 결과)-> 글 하나의 정보를 저장 객체 -> ArrayList 저장
		//							   BoardBean
		while(rs.next()) {
			// rs -> BoardBean 저장
			QRBoardDTO bb = new QRBoardDTO();
			bb.setCategory(rs.getByte("category"));
			bb.setQna_bno(rs.getInt("qna_bno"));
			bb.setRent_bno(rs.getInt("rent_bno"));
			bb.setUser_id(rs.getString("User_id"));
			bb.setSubject(rs.getString("subject"));
			bb.setContent(rs.getString("content"));
			bb.setRead_count(rs.getInt("read_count"));
			bb.setRegdate(rs.getTimestamp("regdate"));
			bb.setUpdatedate(rs.getTimestamp("updatedate"));
			bb.setRent_name(rs.getString("rent_name"));
			bb.setCinema_name(rs.getString("cinema_name"));
			bb.setRent_phone(rs.getString("rent_phone"));
			bb.setRent_email(rs.getString("rent_email"));
			bb.setAnswer(rs.getByte("answer"));
			bb.setAnswer_context(rs.getString("answer_context"));
			
			
			//BoardBean -> ArrayList 한칸에 저장
			boardList.add(bb);
		}//while
		System.out.println(" 게시판 목록 조회 성공! ");
//		System.out.println(boardList);
		System.out.println(" DAO : boardList() 실행");
		System.out.println("=========================");
		
		return boardList;
	}// 글 리스트 조회 -boardList()
	
	// 글의 개수 -getBoardCount
	public int getBoardCount(Byte category) {
		System.out.println(" DAO : getBoardCount() 호출");
		System.out.println(" DAO : 실행목적: 글의 개수(int)리턴");
		
	
		int result = 0;
	
		try {
			// 1.2. 디비연결
			con = getConnect();
			//3. sql구문 작성 &pstmt 객체
			if(category == 0) {
				sql = "select count(*) from qna_rent_board where category = 0";
			}else {
				sql = "select count(*) from qna_rent_board where category = 1";				
			}
			pstmt = con.prepareStatement(sql);
			//4.sql 실행
			rs = pstmt.executeQuery();
			//5.데이터처리
			if(rs.next()) {
//			result = rs.getInt(1);
				result = rs.getInt("count(*)");
				System.out.println(" DAO : 글의 개수 조회 완료! ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		return result;
	}
	// 글의 개수 -getBoardCount
	public int getBoardCount(Byte category, String user_id) {
		System.out.println(" DAO : getBoardCount() 호출");
		System.out.println(" DAO : 실행목적: 글의 개수(int)리턴");
		
		
		int result = 0;
		
		try {
			// 1.2. 디비연결
			con = getConnect();
			//3. sql구문 작성 &pstmt 객체
			if(category == 0) {
				sql = "select count(*) from qna_rent_board where category = 0 && user_id=?";
			}else {
				sql = "select count(*) from qna_rent_board where category = 1 && user_id=?";				
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			//4.sql 실행
			rs = pstmt.executeQuery();
			//5.데이터처리
			if(rs.next()) {
//			result = rs.getInt(1);
				result = rs.getInt("count(*)");
				System.out.println(" DAO : 글의 개수 조회 완료! ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	public int getBoardCount(Byte category, String searchField, String searchText) {
		System.out.println(" DAO : getBoardCount() 호출");
		System.out.println(" DAO : 실행목적: 글의 개수(int)리턴");
		
		searchField = searchField.trim();
		searchText = searchText.trim();
		System.out.println("category : "+ category);
		System.out.println("searchField: "+searchField);
		System.out.println("searchText: "+searchText);
		int result = 0;
		
		try {
			// 1.2. 디비연결
			con = getConnect();
			//3. sql구문 작성 &pstmt 객체
		
//			sql = "select count(*) from event_notice_faq_board where "+searchField+" like "+"'%"+searchText+"%' and category=1";								
//			sql = "select count(*) from event_notice_faq_board where "+searchField+" like "+"'%"+searchText+"%' and category=?";								
			sql = "select count(*) from qna_rent_board where "+searchField+" like ? and category=?";								
			
		
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1,searchField);
			pstmt.setString(1,"%"+searchText+"%");
			pstmt.setByte(2, category);
			//4.sql 실행
			rs = pstmt.executeQuery();
			//5.데이터처리
			if(rs.next()) {
//			result = rs.getInt(1);
				result = rs.getInt("count(*)");
				System.out.println(" DAO : 글의 개수 조회 완료! ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	// 글의 개수 -getBoardCount
	
	public int getBoardCount(Byte category, String user_id, String searchField, String searchText) {
		System.out.println(" DAO : getBoardCount() 호출");
		System.out.println(" DAO : 실행목적: 글의 개수(int)리턴");
		
		searchField = searchField.trim();
		searchText = searchText.trim();
		System.out.println("category : "+ category);
		System.out.println("user_id : "+ user_id);		
		System.out.println("searchField: "+searchField);
		System.out.println("searchText: "+searchText);
		int result = 0;
		
		try {
			// 1.2. 디비연결
			con = getConnect();
			//3. sql구문 작성 &pstmt 객체
			
//			sql = "select count(*) from event_notice_faq_board where "+searchField+" like "+"'%"+searchText+"%' and category=1";								
//			sql = "select count(*) from event_notice_faq_board where "+searchField+" like "+"'%"+searchText+"%' and category=?";								
			sql = "select count(*) from qna_rent_board where "+searchField+" like ? and category=? and user_id=?";								
			
			
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1,searchField);
			pstmt.setString(1,"%"+searchText+"%");
			pstmt.setByte(2, category);
			pstmt.setString(3, user_id);
			//4.sql 실행
			rs = pstmt.executeQuery();
			//5.데이터처리
			if(rs.next()) {
//			result = rs.getInt(1);
				result = rs.getInt("count(*)");
				System.out.println(" DAO : 글의 개수 조회 완료! ");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	// 글의 개수 -getBoardCount
	
	// 글의 개수 -getBoardCount
	public List<QRBoardDTO> searchGetBoardListPage(
			Byte category, String searchField, String searchText, int startRow,int pageSize){
		
		System.out.println(" DAO : noticeGetBoardListPage(startRow,pageSize) 호출");
		List<QRBoardDTO> boardList = new ArrayList<QRBoardDTO>();
		System.out.println("stratRow : "+startRow);
		System.out.println("pageSize : "+pageSize);
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기			
			sql = "select * from qna_rent_board where "
						+ "category = ? and "+searchField+" like ? limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setByte(1, category);
			pstmt.setString(2, "%"+searchText+"%");
			pstmt.setInt(3, startRow-1); //시작위치 starRow 1
			pstmt.setInt(4, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				QRBoardDTO bb = new QRBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setQna_bno(rs.getInt("qna_bno"));
				bb.setRent_bno(rs.getInt("rent_bno"));
				bb.setUser_id(rs.getString("User_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setRent_name(rs.getString("rent_name"));
				bb.setCinema_name(rs.getString("cinema_name"));
				bb.setRent_phone(rs.getString("rent_phone"));
				bb.setRent_email(rs.getString("rent_email"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈 : "+boardList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	public List<QRBoardDTO> searchGetBoardListPage(
			Byte category, String user_id, String searchField, String searchText, int startRow,int pageSize){
		
		System.out.println(" DAO : noticeGetBoardListPage(startRow,pageSize) 호출");
		List<QRBoardDTO> boardList = new ArrayList<QRBoardDTO>();
		System.out.println("stratRow : "+startRow);
		System.out.println("pageSize : "+pageSize);
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기			
			sql = "select * from qna_rent_board where "
					+ "category = ? and user_id=? and "+searchField+" like ? limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setByte(1, category);
			pstmt.setString(2, user_id);
			pstmt.setString(3, "%"+searchText+"%");
			pstmt.setInt(4, startRow-1); //시작위치 starRow 1
			pstmt.setInt(5, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				QRBoardDTO bb = new QRBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setQna_bno(rs.getInt("qna_bno"));
				bb.setRent_bno(rs.getInt("rent_bno"));
				bb.setUser_id(rs.getString("User_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setRent_name(rs.getString("rent_name"));
				bb.setCinema_name(rs.getString("cinema_name"));
				bb.setRent_phone(rs.getString("rent_phone"));
				bb.setRent_email(rs.getString("rent_email"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈 : "+boardList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	public List<QRBoardDTO> qnaGetBoardListPage(int startRow,int pageSize){
		
		System.out.println(" DAO : qnaGetBoardListPage(startRow,pageSize) 호출");
		List<QRBoardDTO> boardList = new ArrayList<QRBoardDTO>();
		
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기
		
			sql = "select * from qna_rent_board where category = 0 limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, startRow-1); //시작위치 starRow 1
			pstmt.setInt(2, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				QRBoardDTO bb = new QRBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setQna_bno(rs.getInt("qna_bno"));
				bb.setUser_id(rs.getString("user_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
			
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈 : "+boardList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	
	
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	public List<QRBoardDTO> qnaGetBoardListPage(int startRow,int pageSize,String user_id){
		
		System.out.println(" DAO : qnaGetBoardListPage(startRow,pageSize) 호출");
		List<QRBoardDTO> boardList = new ArrayList<QRBoardDTO>();
		
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기
			
			sql = "select * from qna_rent_board where category = 0 && user_id=? limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startRow-1); //시작위치 starRow 1
			pstmt.setInt(3, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				QRBoardDTO bb = new QRBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setQna_bno(rs.getInt("qna_bno"));
				bb.setUser_id(rs.getString("user_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
				
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈"+boardList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	
	
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	public List<QRBoardDTO> rentGetBoardListPage(int startRow,int pageSize){
		
		System.out.println(" DAO : rentGetBoardListPage(startRow,pageSize) 호출");
		List<QRBoardDTO> boardList = new ArrayList<QRBoardDTO>();
		
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기
			
			sql = "select * from qna_rent_board where category = 1 limit ?, ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, startRow-1); //시작위치 starRow 1
			pstmt.setInt(2, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				QRBoardDTO bb = new QRBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setRent_bno(rs.getInt("rent_bno"));
				bb.setUser_id(rs.getString("user_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setRent_name(rs.getString("rent_name"));
				bb.setCinema_name(rs.getString("cinema_name"));
				bb.setRent_phone(rs.getString("rent_phone"));
				bb.setRent_email(rs.getString("rent_email"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
				
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈"+boardList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	
	public List<QRBoardDTO> rentGetBoardListPage(int startRow,int pageSize,String user_id){
		
		System.out.println(" DAO : rentGetBoardListPage(startRow,pageSize,search) 호출");
		List<QRBoardDTO> boardList = new ArrayList<QRBoardDTO>();
		
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기
			
			sql = "select * from qna_rent_board where category = 1 && user_id=? limit ?, ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startRow-1); //시작위치 starRow 1
			pstmt.setInt(3, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				QRBoardDTO bb = new QRBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setRent_bno(rs.getInt("rent_bno"));
				bb.setUser_id(rs.getString("user_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setRent_name(rs.getString("rent_name"));
				bb.setCinema_name(rs.getString("cinema_name"));
				bb.setRent_phone(rs.getString("rent_phone"));
				bb.setRent_email(rs.getString("rent_email"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
				
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
			System.out.println(" DAO : 리스트 사이즈"+boardList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	
	
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	
	// 글 조회수 1증가 - updateread_count(bno)
	public void updateRead_count(Byte category, int bno) {
		System.out.println("DAO : 글 조회수 1증가 - updateRead_count(bno) 호출");
		// 1.2. 디비연결
		try {
			con = getConnect();
			// 3. sql구문작성 & pstmt객체
			// sql -> 기존의 조회수 + 1(update)
			if(category == 0) {
				sql = "update qna_rent_board set read_count=read_count+1 where qna_bno=? ";
			}else if(category == 1) {
				sql = "update qna_rent_board set read_count=read_count+1 where rent_bno=? ";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글 조회수 1 증가");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
	}
	// 글 조회수 1증가 - updateread_count(bno)
	
	// 글 정보 (1개) 가져오기 - getBoard(bno)
	public QRBoardDTO getBoard(byte category, int bno) {
		System.out.println("DAO : 글 정보 1개 가져오기 - getBoard(category,bno)");
		// 1.2. 디비연결
		QRBoardDTO bb = null;
		try {
			con = getConnect();
			// 3.sql구문 작성 (select)& pstmt객체
			if(category == 0) {
				sql = "select * from qna_rent_board where category=0 && qna_bno=?";				
			}else if(category == 1) {
				sql = "select * from qna_rent_board where category=1 && rent_bno=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4.sql구문 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리(rs-> 객체(BoardBean))
			if(rs.next()) {
				bb = new QRBoardDTO();
				
				// rs -> bb 저장
				bb.setCategory(rs.getByte("category"));
				bb.setQna_bno(rs.getInt("qna_bno"));
				bb.setRent_bno(rs.getInt("rent_bno"));
				bb.setUser_id(rs.getString("User_id"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setRent_name(rs.getString("rent_name"));
				bb.setCinema_name(rs.getString("cinema_name"));
				bb.setRent_phone(rs.getString("rent_phone"));
				bb.setRent_email(rs.getString("rent_email"));
				bb.setAnswer(rs.getByte("answer"));
				bb.setAnswer_context(rs.getString("answer_context"));
				
				
			}//if
			System.out.println("DAO : Category "+category+"번 유형 "+bno+"번 글정보 저장완료!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		// 글정보 리턴
		return bb;
		
	}
	
	// 글 정보 (1개) 가져오기 - getBoard(bno)
	
	// 글 정보 수정하기 -updateBoard(bb)

	public void updateBoard(QRBoardDTO bb) {
		
		//1.2. 디비연결
		Byte category = bb.getCategory();
		try {
			
			con = getConnect();
			if(category == 0) {
				sql = "update qna_rent_board set subject=?,content=?,"
						+ "answer=?,answer_context=?,updatedate=now()"
						+ " where qna_bno=?";
			}else {
				sql = "update qna_rent_board set subject=?,content=?,"
						+ " answer=?,answer_context=?,updatedate=now()"
						+ " where rent_bno=?";;
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb.getSubject());
			pstmt.setString(2, bb.getContent());
			pstmt.setByte(3, bb.getAnswer());
			pstmt.setString(4, bb.getAnswer_context());
			pstmt.setInt(5, bb.getQna_bno());
					
			//4.sql 실행
			pstmt.executeUpdate();	
			System.out.println(" DAO : 글 정보 수정완료! ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		
	}
	// 글 정보 수정하기 -updateBoard(bb)
	
	// 답변 정보 수정하기 -rentUpdateBoard(bb)
	
	public void rentUpdateBoard(int rent_bno, Byte answer) {
		
		//1.2. 디비연결
		
		try {
			
			con = getConnect();
			
			sql = "update qna_rent_board set answer=? where rent_bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setByte(1, answer);
			pstmt.setInt(2, rent_bno);
			
			//4.sql 실행
			pstmt.executeUpdate();	
			System.out.println(" DAO : 글 정보 수정완료! ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
	}
	// 글 정보 수정하기 -updateBoard(bb)
	
	
	// 글 정보 삭제하기 -deleteBoard(bb)

	public int deleteBoard(QRBoardDTO bb,String pass) {
		int result = -1;
		Byte category = bb.getCategory();
		System.out.println("category : "+category);
		String user_id = bb.getUser_id();
		System.out.println("user_id : "+user_id);
		try {
			//1.2. 디비연결
			con = getConnect();
			sql = "select user_pass from user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 계정있는지
				System.out.println("계정O");
				if(pass.equals(rs.getString("user_pass"))) { // 비밀번호 같은지
					System.out.println("비밀번호같음");
					if( category == 0) { // 해당 게시글이 있는지
						System.out.println("qna게시판임");
						sql = "select * from qna_rent_board where qna_bno=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, bb.getQna_bno());
						rs = pstmt.executeQuery();
						if(rs.next()) {  // 해당 게시글 삭제
							System.out.println("해당게시판이 있음");
							
							sql = "delete from qna_rent_board where qna_bno=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, bb.getQna_bno());
							pstmt.executeUpdate();	
							System.out.println(" DAO : 글 삭제 수정완료! ");
							
							result = 1; 
						}else {
							System.out.println("category == 0 게시물없음");
							result = -1; // 게시물 없음
						}
					}else { // category ==1
						System.out.println("rent게시판임");
						sql = "select * from qna_rent_baord where rent_bno=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, bb.getQna_bno());
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							System.out.println("해당게시판이 있음");
							sql = "delete from qna_rent_board where rent_bno=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, bb.getQna_bno());
							pstmt.executeUpdate();	
							System.out.println(" DAO : 글 삭제 수정완료! ");
							
							result = 1; 
						}else {
							System.out.println("category == 1 게시물없음");
							result = -1; // 게시물 없음
						}
					}
					
				}else {
					result = 0; // 비밀번호 오류
				}
			}else {
				result = 0; // 계정오류
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
	}
	// 글 정보 삭제하기 -deleteBoard(bb)
	
	
	
	
	
}
