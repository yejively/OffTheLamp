package com.team2.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SHA256;

public class UserInfoChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = null;
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		SHA256 sha = new SHA256();
		dto.setUser_id(req.getParameter("user_id"));
		dto.setUser_name(req.getParameter("user_name"));
		dto.setUser_phone(req.getParameter("user_phone"));
		dto.setUser_pass(sha.encodSha256(req.getParameter("user_pass")));
		int result =0;
		
		if(dto.getUser_name().equals("null")) {
			result = dao.updateUserPw(dto);
		}else {
			result = dao.updateUserInfo(dto);
		}
		
		System.out.println(result);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if(result == 1) {
			out.println("<script>");
			out.println("alert('회원정보 수정 성공');");
			out.println("location.href='./UserInfoAction.me'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원정보 수정 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
