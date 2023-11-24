package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.JSMethod;

public class RentAnswerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : RentAnswerAction_execute() 호출");
		
		Byte answer= (byte) Integer.parseInt(request.getParameter("answer"));
		int rent_bno = Integer.parseInt(request.getParameter("rent_bno"));
		System.out.println("answer : " +answer);
		if(answer == 0) {
			answer= 1;
		}else {
			answer= 0;
		}
		
		QRBoardDAO dao = new QRBoardDAO();
		dao.rentUpdateBoard(rent_bno,answer);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminRentInfoBoardAction.me");
		forward.setRedirect(true);
		
		return forward;
	}

}
