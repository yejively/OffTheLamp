package com.team2.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SHA256;


public class UserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		String isCertification = "true";
//		String imp_uid = req.getParameter("imp_uid");
		SHA256 sha = new SHA256();

		dto.setUser_id(req.getParameter("user_id"));
		dto.setUser_pass(sha.encodSha256(req.getParameter("user_pass")));
		dto.setUser_name(req.getParameter("user_name"));
		dto.setUser_phone(req.getParameter("user_phone"));
		
		int result = dao.join(dto, isCertification);

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		
		if(result == -1 || result == 0  ) {
			out.println("<script>");
			out.println("alert('오류');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원가입 성공');");
			out.println("location.href='./UserLogin.me';");
			out.println("</script>");
		}
		
		return null;
	}
	
}