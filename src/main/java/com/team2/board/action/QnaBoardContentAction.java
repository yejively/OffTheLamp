package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class QnaBoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaBoardContentAction_execute() 호출");
		
		
		// 전달정보 저장 bno, pageNum
		int qna_bno = Integer.parseInt(request.getParameter("qna_bno"));
		String pageNum = request.getParameter("pageNum");
	
		String user_id = request.getParameter("user_id");
		// DAO - 조회수 1증가()
		QRBoardDAO dao = new QRBoardDAO();
		dao.updateRead_count((byte) 0,qna_bno);
		System.out.println(" M : 조회수 1 증가!");
		
		// DAO - 특정글 정보 조회()
		QRBoardDTO dto = dao.getBoard((byte) 0,qna_bno);
		
		// request 영역에 정보 저장
		request.setAttribute("dto", dto);
		request.setAttribute("user_id", user_id);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/qnaBoardContent.jsp?pageNum="+pageNum);
		forward.setRedirect(false);
		
		return forward;
	}

}
