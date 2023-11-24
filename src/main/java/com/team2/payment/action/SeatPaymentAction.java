package com.team2.payment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.team2.payment.db.CinemaDTO;
import com.team2.payment.db.OrderDAO;
import com.team2.payment.db.OrderDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

import netscape.javascript.JSObject;

public class SeatPaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : seatPaymentAction() 호출");	
		
		// 전달정보 저장
		String cinema = request.getParameter("theater");
		String movie = request.getParameter("movie");
		String time = request.getParameter("time");
		
		JSONObject obj = new JSONObject();
		
		JSONArray oary = new JSONArray();

		// 극장에 해당하는 지역 theaterDB		-  (결제후 order_board 업데이트를위해 지역을 가져온다)
		OrderDAO dao = new OrderDAO();
		String region = dao.getRegion(cinema);
		
		obj.put("region", region);
//		obj2.put("seat", region);
			
		System.out.println(" M : 지역 : "+region);
		
		// 상영관의 남은좌석 order_boardDB
		List<OrderDTO> seatList = dao.getSeat(cinema, time);
		for(int i=0;i<seatList.size();i++) {
			OrderDTO dto = new OrderDTO();
			JSONObject obj2 = new JSONObject();
			dto.setSeat(seatList.get(i).getSeat());
			obj2.put("seat", dto.getSeat());
			oary.add(obj2);
		};
			oary.add(obj);
//			oary.add(obj2);
		
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(oary);


				
		return null;
	}

}
