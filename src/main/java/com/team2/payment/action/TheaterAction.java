package com.team2.payment.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team2.payment.db.OrderDAO;
import com.team2.payment.db.ScreenDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class TheaterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MovieAction() 호출!");
		
		String theater = request.getParameter("theater");
		String date = request.getParameter("date");
		
		String date2 = date.substring(2, 2);
		
		System.out.println("M : theater : "+theater+" date : "+date2);
		
		OrderDAO dao = new OrderDAO();
		
		List<ScreenDTO> movieList = dao.getMovieName(theater, date2);
		
		JSONArray movie = new JSONArray();
		for(int i=0;i<movieList.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("movie_name", movieList.get(i).getMovie_name());
			movie.add(obj);
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(movie);
		
		return null;
	}

}
