package com.team2.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.payment.db.OrderDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class CancelOrder implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : CancelOrder() 호출");
		
//		HttpSession session = request.getSession();
		int order_id = Integer.parseInt(request.getParameter("order_id"));
		
		System.out.println("order_id : "+order_id);
		
		// 예매취소	예매상태 0 -> 1 변경
		OrderDAO dao = new OrderDAO();
		dao.updateOrderState(order_id);
		
		// 결제취소	결제상태 0 -> 1 변경
		dao.updatePaymentState(order_id);
		
		
		return null;
	}

}
