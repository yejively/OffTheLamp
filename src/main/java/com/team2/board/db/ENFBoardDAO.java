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
public class ENFBoardDAO {
	// 공용변수선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String sql = "";
	

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
	public void insertBoard(ENFBoardDTO bb) throws Exception {
		// 사용자가 입력한 데이터를 DB에 저장
		System.out.println(" DAO : insertBoard(bb) 호출-시작");

		// 1.2. 디비연결
		con = getConnect();
		byte category = bb.getCategory();
		//3. SQL구문 작성 & pstmt 객체
		// 글번호(bno) 계산하기
	
		if(category == 0) {
			sql = "select max(event_bno) from event_notice_faq_board";			
		}else if(category == 1 ) {
			sql = "select max(notice_bno) from event_notice_faq_board";					
		}else {
			sql = "select max(faq_bno) from event_notice_faq_board";		
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
			if(category == 0) {
				bno = rs.getInt("Max(event_bno)")+1;
			}else if(category == 1) {
				bno = rs.getInt("Max(notice_bno)")+1;				
			}else {
				bno = rs.getInt("Max(faq_bno)")+1;				
			}
		}
		System.out.println(" 글번호 : "+bno);
		
		// 글쓰기 동작처리
		// 3. sql구문 작성 & pstmt 객체
		if(category == 0) {
			sql = "insert into event_notice_faq_board (category,event_bno,subject,"
					+ "content,read_count,regdate,event_type,img) "
					+ "values(?,?,?,?,?,now(),?,?)";	
			
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, category);
			pstmt.setInt(2, bno);
			pstmt.setString(3, bb.getSubject());
			pstmt.setString(4, bb.getContent());
			pstmt.setInt(5, 0);// 모드글 생성시 조회수는 0
			pstmt.setByte(6, (byte) 0);// 모드글 생성시 이벤트시작
			pstmt.setString(7, bb.getImg());;// 모드글 생성시 이벤트시작
			pstmt.executeUpdate();
		}else if(category == 1) {
			sql = "insert into event_notice_faq_board (category,notice_bno,subject,"
					+ "content,read_count,regdate) "
					+ "values(?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, category);
			pstmt.setInt(2, bno);
			pstmt.setString(3, bb.getSubject());
			pstmt.setString(4, bb.getContent());
			pstmt.setInt(5, 0);// 모드글 생성시 조회수는 0
			pstmt.executeUpdate();			
		}else {
			
			sql = "insert into event_notice_faq_board (category,faq_bno,subject,"
					+ "content,read_count,regdate) "
					+ "values(?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, category);
			pstmt.setInt(2, bno);
			pstmt.setString(3, bb.getSubject());
			pstmt.setString(4, bb.getContent());
			pstmt.setInt(5, 0);// 모드글 생성시 조회수는 0
			pstmt.executeUpdate();
		}
		System.out.println("글쓰기 완료");
		
		closeDB();
		System.out.println(" DAO : insertBoard(bb) 호출-끝");
	}// 글쓰기 - insertBoard();
	
	
	// 글 리스트 조회 -boardList()
	public ArrayList<ENFBoardDTO> BoardList(Byte category) throws Exception{
		ArrayList<ENFBoardDTO> boardList = new ArrayList<>();
//		List boardList = new ArrayList();//업캐스팅
		System.out.println(" DAO : boardList() 실행");
	
		//1.2.디비연결
		con = getConnect();
		//3. sql구문작성 & pstmt 객체
		if(category == 0) {
			sql = "select * from event_notice_faq_board where category=0";				
		}else if(category == 1) {
			sql = "select * from event_notice_faq_board where category=1";				
		}else { // category == 2
			sql = "select * from event_notice_faq_board where category=2";				
			
		}
		pstmt = con.prepareStatement(sql);
		//4. sql 실행
		rs = pstmt.executeQuery();
		//5. 데이터 처리하기
		// rs(select문의 결과)-> 글 하나의 정보를 저장 객체 -> ArrayList 저장
		//							   BoardBean
		while(rs.next()) {
			// rs -> BoardBean 저장
			ENFBoardDTO bb = new ENFBoardDTO();
			bb.setCategory(rs.getByte("category"));
			bb.setEvent_bno(rs.getInt("event_bno"));
			bb.setNotice_bno(rs.getInt("notice_bno"));
			bb.setFaq_bno(rs.getInt("faq_bno"));
			bb.setSubject(rs.getString("subject"));
			bb.setContent(rs.getString("content"));
			bb.setRead_count(rs.getInt("read_count"));
			bb.setRegdate(rs.getTimestamp("regdate"));
			bb.setUpdatedate(rs.getTimestamp("updatedate"));
			bb.setEvent_type(rs.getByte("event_type"));
			bb.setImg(rs.getString("img"));
		
			
			//BoardBean -> ArrayList 한칸에 저장
			boardList.add(bb);
		}//while
		System.out.println(" 게시판 목록 조회 성공! ");
		
		return boardList;
	}// 글 리스트 조회 -boardList()
	// 글 리스트 조회 -boardList()
	
	// 글의 개수 -getBoardCount
	public int getBoardCount(Byte category) {
		System.out.println(" DAO : getBoardCount() 호출");
		System.out.println(" DAO : 실행목적: 글의 개수(int)리턴");
		
	
		int result = 0;
	
		try {
			// 1.2. 디비연결
			con = getConnect();
			//3. sql구문 작성 &pstmt 객체
		
			if(category == 0 ) {
				sql = "select count(*) from event_notice_faq_board where category = 0";
			}else if(category == 1) {
				sql = "select count(*) from event_notice_faq_board where category = 1";			
			}else {
				sql = "select count(*) from event_notice_faq_board where category = 2";
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
	
	// 글의 개수 -getBoardCount
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
			sql = "select count(*) from event_notice_faq_board where "+searchField+" like ? and category=?";								
			
		
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
	
	
	// 글 리스트 정보 가져오기(페이징처리)-getBoardListPage(startRow,pageSize)
	public List<ENFBoardDTO> noticeGetBoardListPage(int startRow,int pageSize){
		
		System.out.println(" DAO : noticeGetBoardListPage(startRow,pageSize) 호출");
		List<ENFBoardDTO> boardList = new ArrayList<ENFBoardDTO>();
		
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기
		
			sql = "select * from event_notice_faq_board where category = 1 limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, startRow-1); //시작위치 starRow 1
			pstmt.setInt(2, pageSize); // 개수 pageSize 10
			//4. sql 실행
			rs = pstmt.executeQuery();
			//5. 데이터처리 (rs->BoardBean -> List)
			while(rs.next()) {
				// rs->BoardBean
				ENFBoardDTO bb = new ENFBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setNotice_bno(rs.getInt("notice_bno"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				
				
				//BoardBean -> List
				boardList.add(bb);
			}//while
			
			System.out.println(" DAO : (페이징처리된) 글 리스트를 저장");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return boardList;
	}
	public List<ENFBoardDTO> searchGetBoardListPage(
			Byte category, String searchField, String searchText, int startRow,int pageSize){
		
		System.out.println(" DAO : noticeGetBoardListPage(startRow,pageSize) 호출");
		List<ENFBoardDTO> boardList = new ArrayList<ENFBoardDTO>();
		System.out.println("stratRow : "+startRow);
		System.out.println("pageSize : "+pageSize);
		try {
			//1.2. 디비연결 (커넥션풀)
			con = getConnect();
			//3.sql구문 작성 & pstmt 객체
			// 게시판 글 리스트 원하는 만큼만 조회
			// 	limit 시작위치, 개수
			//		: 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져오기			
			sql = "select * from event_notice_faq_board where "
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
				ENFBoardDTO bb = new ENFBoardDTO();
				bb.setCategory(rs.getByte("category"));
				bb.setEvent_bno(rs.getInt("event_bno"));
				bb.setNotice_bno(rs.getInt("notice_bno"));
				bb.setFaq_bno(rs.getInt("faq_bno"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setEvent_type(rs.getByte("event_type"));
			
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
	
	// 글 조회수 1증가 - updateread_count(bno)
	public void updateRead_count(Byte category,int bno) {
		System.out.println("DAO : 글 조회수 1증가 - updateread_count(bno) 호출");
		// 1.2. 디비연결
		try {
			con = getConnect();
			// 3. sql구문작성 & pstmt객체
			// sql -> 기존의 조회수 + 1(update)
			if(category == 0) {
				sql = "update event_notice_faq_board set read_count=read_count+1 where event_bno=? ";
			}else if(category == 1) {
				sql = "update event_notice_faq_board set read_count=read_count+1 where notice_bno=? ";
			}else {//category == 2
				sql = "update event_notice_faq_board set read_count=read_count+1 where faq_bno=? ";
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
	public ENFBoardDTO getBoard(byte category, int bno) {
		System.out.println("DAO : 글 정보 1개 가져오기 - getBoard(bno)");
		// 1.2. 디비연결
		ENFBoardDTO bb = null;
		try {
			con = getConnect();
			// 3.sql구문 작성 (select)& pstmt객체
			if(category == 0) {
				sql = "select * from event_notice_faq_board where category=0 && event_bno=?";				
			}else if(category == 1) {
				sql = "select * from event_notice_faq_board where category=1 && notice_bno=?";				
			}else { // category == 2
				sql = "select * from event_notice_faq_board where category=2 && faq_bno=?";				
				
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4.sql구문 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리(rs-> 객체(BoardBean))
			if(rs.next()) {
				bb = new ENFBoardDTO();
				
				// rs -> bb 저장
				bb.setCategory(rs.getByte("category"));
				bb.setEvent_bno(rs.getInt("event_bno"));
				bb.setNotice_bno(rs.getInt("notice_bno"));
				bb.setFaq_bno(rs.getInt("faq_bno"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setRead_count(rs.getInt("read_count"));
				bb.setRegdate(rs.getTimestamp("regdate"));
				bb.setUpdatedate(rs.getTimestamp("updatedate"));
				bb.setEvent_type(rs.getByte("event_type"));
				bb.setImg(rs.getString("img"));
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

	public void updateBoard(ENFBoardDTO bb) {
		
		//1.2. 디비연결
		try {
			
			con = getConnect();
			//3. sql구문작성(기존의 회원여부확인) & pstmt객체
			if(bb.getCategory() == 0) {
				if(bb.getImg() == "" || bb.getImg() == null) {
					System.out.println(" M : 파일없는버젼수정 시작");
					sql = "update event_notice_faq_board set subject=?,content=?,event_type=?,updatedate=now() where event_bno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bb.getSubject());
					pstmt.setString(2, bb.getContent());
					pstmt.setByte(3, bb.getEvent_type());
					pstmt.setInt(4, bb.getEvent_bno());
				}else {
					System.out.println(" M : 파일있는버젼수정 시작");
					sql = "update event_notice_faq_board set subject=?,content=?,event_type=?,updatedate=now(),img=? where event_bno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bb.getSubject());
					pstmt.setString(2, bb.getContent());
					pstmt.setByte(3, bb.getEvent_type());
					pstmt.setString(4, bb.getImg());
					pstmt.setInt(5, bb.getEvent_bno());
				}
			}else if(bb.getCategory() == 1) {
				sql = "update event_notice_faq_board set subject=?,content=?,updatedate=now() where notice_bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bb.getSubject());
				pstmt.setString(2, bb.getContent());
				pstmt.setInt(3, bb.getNotice_bno());
			}else {
				sql = "update event_notice_faq_board set subject=?,content=?,updatedate=now() where faq_bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bb.getSubject());
				pstmt.setString(2, bb.getContent());
				pstmt.setInt(3, bb.getFaq_bno());
			}
					
			//4.sql 실행
			pstmt.executeUpdate();	
			System.out.println(" DAO : 글 정보 수정완료! ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		
	}
	// 글 정보 수정하기 -updateBoard(bb)
	
	
	// 글 정보 삭제하기 -deleteBoard(bb)

	public void deleteBoard(ENFBoardDTO bb) {
		
		try {
			//1.2. 디비연결
			con = getConnect();
			if(bb.getCategory() == 0) {
				sql = "delete from event_notice_faq_board where event_bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bb.getEvent_bno());
			}else if(bb.getCategory() == 1) {
				sql = "delete from event_notice_faq_board where notice_bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bb.getNotice_bno());
			}else {
				sql = "delete from event_notice_faq_board where faq_bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bb.getFaq_bno());
			}
			pstmt.executeUpdate();	
			System.out.println(" DAO : 글 삭제 수정완료! ");
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
	
	}
	// 글 정보 삭제하기 -deleteBoard(bb)
	
	
	
	
	
	
}
