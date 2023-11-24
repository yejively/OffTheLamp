package com.team2.user.action;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.user.DB.UserDAO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;


public class UserFindIdAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
			
			UserDAO dao =  new UserDAO();
	
			
		//	dto.setUser_name(request.getParameter("user_name"));
			
			String phone = request.getParameter("user_phone");
			String name = request.getParameter("user_name");
		
			String result = 	dao.findId(phone, name);
			
			System.out.println("result:"+result);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
		if(result != null) {
		    System.out.println("ddd");
			out.print("<script>");
			out.print("alert('고객님의 아이디는 "+result+"입니다');");
			//  out.print("alert('고객님의 아이디는 입니다');");
				out.print("location.href='./UserLogin.me';");
				out.print("</script>");
		} else {
			System.out.println("aaaa");
			out.print("<script>alert('입력하신 정보가 없습니다');history.back();</script>");
		}
			
			
			return null;
			
	}
	}


