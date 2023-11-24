package com.team2.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SHA256;

public class UserInfoCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDAO dao = new UserDAO();
		ActionForward forward = new ActionForward();
		SHA256 sha = new SHA256();
		
		String id = req.getParameter("user_id");
		String pw = sha.encodSha256(req.getParameter("user_pass"));
		System.out.println("id:"+id);
		System.out.println("pw:"+pw);
		int result = dao.rogin(id, pw);
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		System.out.println(result);
		
		if(result == 1) {
			forward.setPath("./UserInfoAction.me");
			forward.setRedirect(true);
		}else {
			out.println("<script>");
			out.println("alert('다시 입력해 주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
