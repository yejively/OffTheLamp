package com.team2.board.action;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team2.board.db.ENFBoardDAO;
import com.team2.board.db.ENFBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.util.JSMethod;

public class ENFBoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ENFBoardAddAction_execute() 호출");
		String location = null;
		int maxSize = 1024 * 1024 * 10; // 키로바이트 * 메가바이트 * 기가바이트   
		MultipartRequest multi =null; 
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		
		Enumeration<?> files = null;
		String element = "";
		String filesystemName = "";
		String subject = null;
		String content = null;
		ENFBoardDTO dto = new ENFBoardDTO();
		Byte category = (byte) Integer.parseInt(request.getParameter("category"));
		dto.setCategory(category);
		
		if(category == 0) { //이벤트 게시판
			location = request.getServletContext().getRealPath("/img"); // 실제저장되는경로
			System.out.println("location : "+location);
			multi = new MultipartRequest(request,
					location,
					maxSize,
					"utf-8",
					new DefaultFileRenamePolicy()); 
			files = multi.getFileNames();
			if (files.hasMoreElements()) { 
				
				element = (String)files.nextElement(); 
				
				filesystemName 	= multi.getFilesystemName(element); //저장되는 실제파일이름
				
			}
			subject = multi.getParameter("subject");
			content = multi.getParameter("content");
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setImg(filesystemName);
			session.setAttribute("user_id", user_id);
			
			ENFBoardDAO dao = new ENFBoardDAO();
			dao.insertBoard(dto);
			
			JSMethod.alertLocation(response, "추가 완료!");
			
			return null;
		}
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		session.setAttribute("user_id", user_id);
		
		ENFBoardDAO dao = new ENFBoardDAO();
//		System.out.println(" M : "+dto);
		dao.insertBoard(dto);
		
		JSMethod.alertLocation(response, "추가 완료!");
		
		return null;
	}

}
