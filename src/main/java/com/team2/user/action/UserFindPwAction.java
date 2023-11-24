package com.team2.user.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class UserFindPwAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		
			UserDAO dao =  new UserDAO();
			UserDTO dto =  new UserDTO();
			ActionForward af = new ActionForward();
			dto.setUser_phone(request.getParameter("user_id"));
			String id = request.getParameter("user_id");
			PrintWriter out = response.getWriter();
			System.out.println(id);
			String result  = dao.findPw(id);
			if(result.equals("")) {
				out.print("<script>");
				out.print("alert('비밀번호 찾기를 실패했습니다');");
				out.print("history.back();");
				out.print("</script>");
				
			}else {
				
				request.setAttribute("user_id", id);
				request.setAttribute("user_pass", result);
				
				af.setPath("./user/findPwView.jsp");
				af.setRedirect(false);
			}
			
			System.out.println("result:"+result);
			
		
		
			return af;
			
			
	}
}
