package com.team2.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BoardDAO {

	// 공통 변수 선언 	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	//DB 연결 메서드 - getCon()
	
	private Connection getConnect() throws Exception {
		
		System.out.println(" DAO : getConnect() 실행");
		
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/team2");
		
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공 " + con );
		return con;
	}
	// 디비 자원해제 메서드 - closeDB()
	
		public void closeDB() {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
				System.out.println(" DAO : 자원해제 완료 ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	// 디비 자원해제 메서드 - closeDB()
		
	//1:1글쓰기 insertQnaBoard();
		public void insertRentBoard(qnaRentBoardDTO qbb) throws Exception {
			
			int rent_bno = 0 ;
			
			System.out.println(" DAO : insertRentBoard(qbb) 호출 - 시작 ");
			
			//DB연결
			con = getConnect();
			
			//SQL 구문 작성, pstmt 객체 
			sql = "select max(rent_bno) from qna_rent_board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("게시판 글 있음");
				rent_bno = rs.getInt(1) + 1;
			}
			System.out.println("DAO : rent_bno - " + rent_bno);
			
			// 글쓰기 동작 처리 (insert)
			
			sql = "insert into qna_rent_board (category, rent_bno, user_id, rent_name,"
					+ "cinema_name, rent_phone, rent_email, subject, content, regdate, answer)"
					+ "values(1,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(),?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, rent_bno);
			pstmt.setString(2, qbb.getUser_id()); 
			pstmt.setString(3, qbb.getRent_name()); 
			pstmt.setString(4, qbb.getCinema_name());
			pstmt.setString(5, qbb.getRent_phone());
			pstmt.setString(6, qbb.getRent_email());
			pstmt.setString(7, qbb.getSubject());
			pstmt.setString(8, qbb.getContent());
			pstmt.setInt(9, 0);
			
			//sql 실행 
			
			pstmt.executeUpdate();
			System.out.println("글쓰기 완료");
			
			closeDB();
			
			System.out.println(" DAO : insertQnaBoard(qbb) 호출 - 끝");
			
			}
			// 글쓰기 동작 처리 (insert) 
		
	//1:1글쓰기 insertQnaBoard();
	
	//문의 게시판 극장 목록 불러오기 selectCinemaList();
		public JSONArray selectCinemaList(){
			
			ArrayList<CinemaDTO> cinemaList = new ArrayList<CinemaDTO>();
			
			JSONArray jcinemaList = new JSONArray();
			
			try {
				con = getConnect();
				sql = "SELECT region, GROUP_CONCAT(cinema_name) AS cinema_list"
						+ " FROM cinema"
						+ " GROUP BY region";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				int result = 0 ;
				
				while (rs.next()) {
					
					
					JSONObject obj = new JSONObject();
					obj.put("cinema_list", rs.getString("cinema_list"));
					obj.put("region", rs.getString("region"));
//					System.out.println("obj = "+ obj);
					jcinemaList.add(obj);
//					System.out.println("DAO : jcinemaList =" + jcinemaList);
					
				}
				
				System.out.println("DAO : 극장 목록 저장 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return jcinemaList;
		}
	//문의 게시판 극장 목록 불러오기 selectCinemaList();
		

}
