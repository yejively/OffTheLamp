package com.team2.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.util.Action;
import com.team2.util.ActionForward;

public class UserLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberLogoutAction_execute() 호출 ");
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('  정상적으로 로그아웃 되었습니다 ');");
		out.println("location.href='./Main.me';");
		out.println("</script>");
		
		// 컨트롤러 이동 X (ㅌㅣ켓생성X)
		System.out.println("M : JS페이지 이동 O, 컨트롤러 페이지 이동 X ");
		return null;
		
	}

	
	
}
