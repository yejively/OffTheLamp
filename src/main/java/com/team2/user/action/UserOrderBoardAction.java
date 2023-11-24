package com.team2.user.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.util.Action;
import com.team2.util.ActionForward;

public class UserOrderBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = new  ActionForward();
		
		forward.setPath("./user/userOrderBoard.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
