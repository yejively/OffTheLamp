package com.team2.payment.action;

import java.io.PrintWriter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.payment.db.OrderDAO;
import com.team2.user.DB.NonUserDTO;
import com.team2.user.DB.UserDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class MemberInfo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberInfo() 호출");
		
		
		String id = request.getParameter("user_id");
		
		int idd = id.indexOf('@');
		System.out.println(idd);
		
		System.out.println("M : "+id);
		
		// 전달받은 id값으로 회원, 비회원을 구분해야함 - (회원일시 id에 무조건 문자가있음)(비회원일시 무조건 숫자만있음)
		
		OrderDAO dao = new OrderDAO();
		
		response.setContentType("text/html; charset=UTF-8");
		
		
		if(idd == -1) {
			NonUserDTO dto = dao.getNonuserInfo(id);
			response.getWriter().print(dto.getNonuser_name()+","+id);
			System.out.println("M : 비회원정보조회성공");
			System.out.println("M : NonuserInfo : " + dto.getNonuser_name() + ","+id);
		}else {
			UserDTO dto = dao.getMemberInfo(id);
			response.getWriter().print(dto.getUser_name() + "," +dto.getUser_phone()+","+dto.getUser_num());
			System.out.println("M : 회원정보조회성공");
			System.out.println("M : userInfo : " + dto.getUser_name() + "," + dto.getUser_phone()+","+dto.getUser_num());
		}
		
		


		return null;
	}

}
