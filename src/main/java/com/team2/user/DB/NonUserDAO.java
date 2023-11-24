package com.team2.user.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.util.ConnectionDB;

public class NonUserDAO {
	ConnectionDB con = new ConnectionDB();
	PreparedStatement pstmt;
	Connection conn;
	ResultSet rs;
	String sql;
	// 비회원 로그인 ---> 성공시 예매 페이지로 이동
	public int nonlogin(NonUserDTO dto) {
		sql = "insert into non_user (nonuser_name,nonuser_phone,nonuser_pass) values(?,?,?)";
		
		conn = con.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNonuser_name());
			pstmt.setString(2, dto.getNonuser_phone());
			pstmt.setString(3, dto.getNonuser_pass());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			con.closeDB(conn, rs, pstmt);
		}
	}
}
