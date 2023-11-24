package com.team2.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.payment.db.OrderDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class PaymentSuccessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : PaymentSuccessAction() 호출 !");
		
		String payment_id = request.getParameter("imp_uid");
		String order_id2 = request.getParameter("merchant_uid");
		
		int order_id = Integer.parseInt(order_id2);
		String pg = request.getParameter("pg");
		String payment_method = request.getParameter("payment_method");
		String movie_name = request.getParameter("movie_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String name = request.getParameter("name");
		
		String region = request.getParameter("region");
//		int user_num = Integer.parseInt(request.getParameter("user_num"));
//		가져오는 데이터가 null인경우 int형으로 자료형변경시 numberFormatException이 발생.. 
		String id = request.getParameter("user_id");
		String cinema = request.getParameter("cinema");
		String seat = request.getParameter("seat");
		String car_type = request.getParameter("car_type");
		String car_num = request.getParameter("car_num");
		String time = request.getParameter("time");
		System.out.println("M : time "+time);
		String phone = request.getParameter("phone");
		System.out.println("M : payment_id : "+payment_id);
		System.out.println("M : order_id : "+order_id);
		
		// 결제테이블 처리
		OrderDAO dao = new OrderDAO();
		dao.successPayment(payment_id, order_id, pg, payment_method, price, name, phone, movie_name);
		
		// 예매테이블 처리		
		try {
			int user_num = Integer.parseInt(request.getParameter("user_num"));
			// 회원
			dao.successUserOrder(order_id,user_num,region,cinema,seat,movie_name,time,car_type,car_num);
			// user테이블 예매횟수 증가메서드
			int moc = dao.getMaxOrderCount(user_num);
			dao.updateOrderCount(moc, user_num);
			System.out.println("M : 예매횟수 조회, 증가 성공");
		} catch (NumberFormatException nfe) {
			try {
//				String nonuser_phone = id;	// 비회원 휴대폰번호
//				dao.getNonuserInfo(nonuser_phone);
				dao.successNonuserOrder(order_id,region,cinema,seat,movie_name,time,car_type,car_num);
			} catch (NumberFormatException nfen) {
				
			}

		}
		
		return null;
	}
	
	

}
