package com.team2.user.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.team2.board.db.QRBoardDAO;
import com.team2.board.db.QRBoardDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;

public class AdminRentInfoBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminRentInfoBoardAction_execute() 실행");
		// M : DB + 처리(계산)
		
		// DB - BoardDAO 객체- M
		QRBoardDAO dao = new QRBoardDAO();
		
		String search = null;
		search = request.getParameter("search");
		System.out.println("search "+search);
		// 작성되어 있는 전체 글의 개수 계산(DB메서드) -M
		int count = 0;
		if(search == null || search.equals("")) {
			count = dao.getBoardCount((byte) 1);			
		}else {
			count = dao.getBoardCount((byte) 1,search);		
		}
		System.out.println(" M : 전체 글 개수 : "+count+"개");
		// 페이징처리-1 -M
		////////////////페이징처리 -1 /////////////////
		
		int pageSize = 5; // 한페이지에 10개씩 출력
		
		// 페이지의 정보(몇페이지인지 확인하는 정보)
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1"; //페이지가 없을경우
		}
		
		// 시작행 번호 계산 1 11 21 31 ...
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1 )*pageSize+1;
		
		// 끝행 번호 계산 10 20 30 40
		int endRow = currentPage * pageSize;
		////////////////페이징처리 -1 /////////////////
		
		// 페이징처리 글 데이터를 가져오기(DB메서드) -M
		List<QRBoardDTO> boardList = null;
		if(count > 0) {
			if(search == null || search.equals("")) {
				boardList = dao.rentGetBoardListPage(startRow, pageSize);
			}else {
				boardList = dao.rentGetBoardListPage(startRow, pageSize,search);				
			}
		}
		String jList = new Gson().toJson(boardList);
		
		// 테이블에 출력(반복문) ->view
		
		// 페이징 처리-2 -M
			  // 전체 페이지 수 => 글 / 페이지당 출력 개수
			  //               50 / 10 => 5    55 / 10 => 6   
			  int pageCount = count / pageSize + (count%pageSize != 0? 1:0);
			  
			  // 한 화면에서 보여줄 페이지번호 개수(block)  1....10
			  int pageBlock = 4;
			  
			  // 페이지 블럭의 시작번호   1~10 => 1  11~20 => 11  21~30 => 21
			  int startPage = ((currentPage - 1)/pageBlock) * pageBlock + 1;
			  
			  // 페이지 블럭의 끝 번호   1~10 => 10  11~20 => 20  21~30 => 30
			  int endPage = startPage + pageBlock - 1;	
			  if(endPage > pageCount){
				  endPage = pageCount;
		}

		// 페이징처리 블럭출력 ->view
		
		// Model -> 데이터 처리동작(DB사용, 계산동작들....)
		// View -> 정보 전달받아서 출력
		
		// 계산된 정보를 view 페이지로 전달(request 영역에 저장)
		// boardList, 페이징처리 정보
		request.setAttribute("boardList", boardList);
		request.setAttribute("jList", jList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./user/admin_rentInfo.jsp");
		forward.setRedirect(false);
		

		
		return forward;
	}

}
