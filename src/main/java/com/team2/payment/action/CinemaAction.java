package com.team2.payment.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team2.payment.db.CinemaDTO;
import com.team2.payment.db.OrderDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class CinemaAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : CinemaAction() 호출");	
		
		String region = request.getParameter("region1");
		String region2 = request.getParameter("region2");
		System.out.println("region1 : "+region);
		System.out.println("region2 : "+region2);
		
		OrderDAO dao = new OrderDAO();
		List<CinemaDTO> cinemaList = dao.getCinema(region, region2);
		
		// JSONArray 정보 전달
		JSONArray cinema = new JSONArray();
		
		for(int i=0;i<cinemaList.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("cinema_name", cinemaList.get(i).getCinema_name());
			cinema.add(obj);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(cinema);
		
			
		
		
		return null;
	}

}
