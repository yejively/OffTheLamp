package com.team2.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.user.DB.UserDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SHA256;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession se = request.getSession();

		SHA256 sha = new SHA256();
		UserDAO mDAO = new UserDAO();
		ActionForward action = null;

		String id = request.getParameter("id");
		String pw = sha.encodSha256(request.getParameter("pw"));

		System.out.println(id + "dtd");
		int result = mDAO.rogin(id, pw);

		if (result == 0) {
			out.print("<script>");
			out.print("alert('아이디 비밀번호를 다시 입력해주세요');");
			out.print("location.href='./UserLogin.me';");
			out.print("</script>");

		} else {

			action = new ActionForward();

			se.setAttribute("user_id", id);
			action.setPath("./Main.me");
			action.setRedirect(true);
		}
		
		return action;

	}

}