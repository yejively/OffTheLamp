package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class QnaBoardAnswerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaBoardAnswerAction_execute() 호출");
		// 전달정보 저장(bno,pageNum)
		String user_id = request.getParameter("user_id");
		int qna_bno = Integer.parseInt(request.getParameter("qna_bno"));
		String pageNum = request.getParameter("pageNum");
		
		// DAO - 특정글 정보 조회
		QRBoardDAO dao = new QRBoardDAO();
		QRBoardDTO dto = dao.getBoard((byte) 0,qna_bno);
		
		// request 영역 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("user_id", user_id);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/qnaBoardAnswer.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}

}
