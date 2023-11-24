package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.board.db.BoardDAO;
import com.team2.board.db.qnaRentBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class rentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" C : rentWriteAction_execute() 실행");
		
		qnaRentBoardDTO dto = new qnaRentBoardDTO();
		
		dto.setCategory(1);
		dto.setUser_id(request.getParameter("ruser_id"));
		dto.setRent_name(request.getParameter("rname"));
		dto.setCinema_name(request.getParameter("cinema"));
		dto.setRent_phone(request.getParameter("rphone"));
		dto.setRent_email(request.getParameter("remail"));
		dto.setSubject(request.getParameter("rsubject"));
		dto.setContent(request.getParameter("rcontent"));
		
		System.out.println(" M : " +dto);
		
		//DB연결 => DAO 객체 - insertRentBoard (대관 문의 글쓰기) 
		BoardDAO dao = new BoardDAO();
		dao.insertRentBoard(dto);
		
		// 페이지 이동
		
		ActionForward forward = new ActionForward();
		forward.setPath("./rentMain.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
