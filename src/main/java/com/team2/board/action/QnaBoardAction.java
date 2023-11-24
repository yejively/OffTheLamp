package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.JSMethod;

public class QnaBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M :QnaBoardAction_execute() 호출");
		
		// 한글처리(생략) -> web.xml에 필터적용해서
		// 글정보 저장 => DTO
		QRBoardDTO dto = new QRBoardDTO();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		System.out.println("user_id : "+user_id);
		dto.setCategory((byte) 0);
		dto.setUser_id(user_id); // 로그인한 user_id값을가져와야함
//		dto.setUser_id("test1"); // test용
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		session.setAttribute("user_id", user_id);
		System.out.println(" M : "+dto);
		
		// DB연결 -> DAO 객체 - 글쓰기 메서드
		QRBoardDAO dao = new QRBoardDAO();
		dao.insertBoard(dto);
		JSMethod.alertLocation(response, "추가 완료!");
		return null; // 정보만보내고 화면끔
	}

}
