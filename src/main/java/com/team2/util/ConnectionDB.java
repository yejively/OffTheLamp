package com.team2.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionDB {
	
	//DB 연결 메서드
	public Connection getConnection() {
		Connection conn=null;
		try {
			Context initCTX = new InitialContext();
			
			// context.xml 파일 접근 DataSource 형태로 변경
			DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/team2");
			
			try {
				return conn = ds.getConnection(); //db연결
			} catch (SQLException e) {
				e.printStackTrace();
				return conn;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return conn;
		}
	}
	
	//자원 해제
	public void closeDB(Connection conn, ResultSet rs, PreparedStatement pstmt) {
		try {
			if(conn != null )		
			conn.close();
			if(rs != null)
			rs.close();
			if(pstmt != null)
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
