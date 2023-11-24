package com.team2.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team2.board.db.ENFBoardDAO;
import com.team2.board.db.ENFBoardDTO;
import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.JSMethod;

public class ENFBoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println(" M : ENFBoardUpdateProAction_execute 호출 ");
	
		Byte category = (byte) Integer.parseInt(request.getParameter("category"));
//		System.out.println("category : "+category);
		String pageNum = request.getParameter("pageNum");
		String location = request.getServletContext().getRealPath("/img");
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest multi= null; 
		String subject=null; 			
		String content =null;
		Byte event_type = null;
		String element = "";
		String filesystemName = "";
		
		// 전달정보 저장(수정할 데이터)
		ENFBoardDTO dto = new ENFBoardDTO();
		dto.setCategory(category);
		
		if(category == 0) {
			
			multi= new MultipartRequest(request,
					location,
					maxSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			Enumeration<?> files = null; 
			files =	multi.getFileNames();
			
			if (files.hasMoreElements()) { 
				element = (String)files.nextElement(); 
				filesystemName = multi.getFilesystemName(element); 
			}
			event_type = (byte) Integer.parseInt(multi.getParameter("event_type"));
			System.out.println("event_type : "+event_type);
			if (event_type==-1) {
				JSMethod.alertBack(response, "선택해주세요!");
				return null;
			}
			dto.setEvent_type(event_type);
			dto.setEvent_bno(Integer.parseInt(multi.getParameter("event_bno")));
			subject = multi.getParameter("subject");
			content = multi.getParameter("content");
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setImg(filesystemName);
			session.setAttribute("user_id", user_id);
			request.setAttribute("dto", dto);
			
			ENFBoardDAO dao = new ENFBoardDAO();
			dao.updateBoard(dto);
			// 페이지 이동
			ActionForward forward = new ActionForward();
			forward.setPath("./eventMain.bo");
			forward.setRedirect(true);
			
			return forward;
				
		}else if(category == 1) {
			dto.setNotice_bno(Integer.parseInt(request.getParameter("notice_bno")));
		}else{
			dto.setFaq_bno(Integer.parseInt(request.getParameter("faq_bno")));						
		}
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		// DAO - 정보수정메서드 호출
		ENFBoardDAO dao = new ENFBoardDAO();
		dao.updateBoard(dto);
				
		// request 영역에 정보 저장
		request.setAttribute("dto", dto);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		
		if(category == 1) {
			forward.setPath("./noticeMain.bo?pageNum="+pageNum);
		}else {
			forward.setPath("./faqMain.bo");			
			JSMethod.alertLocation(response, "수정완료");
			return null;
		}
		forward.setRedirect(true);
				
		return forward;
	
	}

}
