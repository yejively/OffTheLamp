package com.team2.board.action;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team2.board.db.BoardDAO;
import com.team2.board.db.CinemaDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class CinemaSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" C : CinemaSelectAction_execute 실행");
		
		
		  BoardDAO dao = new BoardDAO(); 
		  JSONArray cinemaList = dao.selectCinemaList();
		  
		  System.out.println("cinemaList = " + cinemaList );
		  response.setContentType("application/json; charset=UTF-8");
		  PrintWriter out = response.getWriter();
		  out.print(cinemaList);
		  
		return null;
	}
}
