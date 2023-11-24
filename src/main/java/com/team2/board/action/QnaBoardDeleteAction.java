package com.team2.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.JSMethod;

public class QnaBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaBoardDeleteAction_execute 호출 "); 
		
		// 전달정보 저장(bno,pass,pageNum)
		QRBoardDTO dto = new QRBoardDTO();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		dto.setCategory((byte) 0);
		dto.setQna_bno(Integer.parseInt(request.getParameter("qna_bno")));
		System.out.println("qna_bno : "+dto.getQna_bno());
		dto.setUser_id(user_id);
		session.setAttribute("user_id", user_id);
		
		String pass = request.getParameter("pass");
		System.out.println("pass : "+pass);
		
		// DAO - 글 삭제 메서드() 
		QRBoardDAO dao = new QRBoardDAO();
		int result = dao.deleteBoard(dto,pass);
		System.out.println("result : "+result);
		if(result == 0) {
			JSMethod.alertBack(response, "비밀번호 오류!");
			return null;
		}
		if(result == -1) {
			JSMethod.alertBack(response, "게시글 없음");
			return null;
		}
		JSMethod.alertLocation(response, "삭제 완료!");
		// 처리결과에 따른 페이지 이동
		
		return null;
	}

}
