package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.board.db.ENFBoardDAO;
import com.team2.board.db.ENFBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class ENFBoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : NoticeBoardContentAction_execute() 호출");
		
		// 전달정보 저장 bno, pageNum
		int event_bno = 0;
		int notice_bno = 0;
		int faq_bno = 0;
		String user_id = request.getParameter("user_id");
		Byte categroy = (byte) Integer.parseInt(request.getParameter("category"));
		if(categroy == 0) {
			event_bno = Integer.parseInt(request.getParameter("event_bno"));
			System.out.println("event_bno : "+event_bno);
		}else if(categroy == 1) {
			notice_bno = Integer.parseInt(request.getParameter("notice_bno"));
		}else{
			faq_bno = Integer.parseInt(request.getParameter("faq_bno"));
		}
		String pageNum = request.getParameter("pageNum");
		
		// DAO - 조회수 1증가()
		ENFBoardDAO dao = new ENFBoardDAO();
		if(categroy == 0) {
			dao.updateRead_count(categroy,event_bno);						
		}else {
			dao.updateRead_count(categroy,notice_bno);			
		}
		System.out.println(" M : 조회수 1 증가!");
		
		// DAO - 특정글 정보 조회()
		ENFBoardDTO dto = null;
		if(categroy == 0) {
			dto = dao.getBoard(categroy,event_bno);						
		}else {
			dto = dao.getBoard(categroy,notice_bno);			
		}
		
		// request 영역에 정보 저장
		request.setAttribute("user_id", user_id);
		request.setAttribute("dto", dto);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		if(categroy == 0) {
			forward.setPath("./board/eventContent.jsp");			
		}else {
			forward.setPath("./board/noticeBoardContent.jsp?pageNum="+pageNum);
			
		}
		forward.setRedirect(false);
		
		return forward;
	}

}
