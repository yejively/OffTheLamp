package com.team2.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.user.DB.NonUserDAO;
import com.team2.user.DB.NonUserDTO;
import com.team2.user.DB.UserDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SHA256;

public class NonUserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		NonUserDAO dao = new NonUserDAO();
		NonUserDTO dto = new NonUserDTO();		
		SHA256 sha = new SHA256();
		HttpSession se = request.getSession();
		
		dto.setNonuser_name(request.getParameter("nonuser_name"));
		dto.setNonuser_phone(request.getParameter("nonuser_phone"));
		dto.setNonuser_pass(sha.encodSha256(request.getParameter("nonuser_pass")));
		System.out.println(dto.getNonuser_name());
		int result = dao.nonlogin(dto);
		
		se.setAttribute("user_id", dto.getNonuser_phone());
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(result);
		if(result == -1 || result ==0 ) {
			
			out.println("<script>");
			out.println("alert('다시 입력해 주세요');");
			out.println("history.back();");
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('예매페이지로 이동합니다');");
			out.println("location.href='./orderMain.or'");
			out.println("</script>");
		}
		
		return null;
	}
	
	
}

