package com.team2.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.team2.board.db.ENFBoardDAO;
import com.team2.board.db.ENFBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class EventMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : eventMainAction_execute() 호출");
		
		ENFBoardDAO dao = new ENFBoardDAO();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		List<ENFBoardDTO> boardList = dao.BoardList((byte) 0);
		request.setAttribute("boardList", boardList);
		request.setAttribute("user_id", user_id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/eventMain.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
