package com.team2.user.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class UserTypeChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		UserDAO dao = new UserDAO();
		List<UserDTO> list = new ArrayList<>();
		
		int startRow = Integer.parseInt(req.getParameter("startRow"));
		int pageSize= Integer.parseInt(req.getParameter("pageSize"));
		int choice= Integer.parseInt(req.getParameter("choice"));
		int user_type= Integer.parseInt(req.getParameter("user_type"));
		
		list = dao.getAllUserInfo(startRow, pageSize);
		
		int user_num = list.get(choice).getUser_num();
		
		int result = dao.updateUserType(user_num, user_type);
	
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.print(result);

		
		return null;
	}

}
