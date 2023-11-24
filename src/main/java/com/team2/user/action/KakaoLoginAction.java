package com.team2.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.user.DB.UserDAO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.SHA256;

public class KakaoLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		SHA256 sha = new SHA256();
		
		String kakaoEmail = req.getParameter("kakaoEmail");
		String kakaoLogin = req.getParameter("kakaoLogin");
		String kakaoNickname = req.getParameter("kakaoNickname");
		HttpSession se = req.getSession();
		String isCertification = "false";
		boolean idCh = dao.checkId(kakaoEmail);
		int result = -1;
		
		int rand =(int)(Math.random()*100000);

		System.out.println("kakaoEmail: "+kakaoLogin);
		System.out.println("kakaoEmail: "+kakaoEmail);
		System.out.println("kakaoEmail: "+kakaoNickname);
		
	
		if(kakaoLogin.equals("true")) {
			isCertification = "true";
			dto.setUser_id(kakaoEmail);
			dto.setUser_name(kakaoNickname);
			dto.setUser_pass(sha.encodSha256("kakaoPW"+rand));
			dto.setUser_phone("kakao"+rand);
		}
	
		if(idCh == true) {
			result = dao.join(dto, isCertification);
		}else {
			result =2;
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		
		if(result == -1 || result == 0  ) {
			out.println("false");
		}else {
			se.setAttribute("user_id", kakaoEmail);
			System.out.println(req.getAttribute("user_id"));
			out.println("true");
		}
		System.out.println("dtd:"+result);
		
		return null;
	}

}
