package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.board.db.ENFBoardDAO;
import com.team2.board.db.ENFBoardDTO;
import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.JSMethod;

public class ENFBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : NoticeBoardDeleteAction_execute 호출 "); 
		Byte category = (byte) Integer.parseInt(request.getParameter("category"));
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		// 전달정보 저장(bno,pass,pageNum)
		ENFBoardDTO dto = new ENFBoardDTO();
		dto.setCategory(category);
		if(category == 0) {
			dto.setEvent_bno(Integer.parseInt(request.getParameter("event_bno")));			
		}else if(category == 1) {
			dto.setNotice_bno(Integer.parseInt(request.getParameter("notice_bno")));
		}else{
			dto.setFaq_bno(Integer.parseInt(request.getParameter("faq_bno")));						
		}
		System.out.println("qna_bno : "+dto.getNotice_bno());
		session.setAttribute("user_id", user_id);
				
		// DAO - 글 삭제 메서드() 
		ENFBoardDAO dao = new ENFBoardDAO();
		dao.deleteBoard(dto);

		JSMethod.alertLocation(response, "삭제 완료!");
		
		
		return null;
	}

}
