package com.team2.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class UserIdCheckAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDAO dao = new UserDAO();
		
		String id = req.getParameter("userId");
		
		boolean result = dao.checkId(id);
		
		PrintWriter out = resp.getWriter();
		System.out.println(result);
		out.print(result);
		
		return null;
	}
 
}
