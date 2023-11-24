package com.team2.payment.action;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team2.payment.db.OrderDAO;
import com.team2.payment.db.OrderDTO;
import com.team2.payment.db.PaymentDTO;
import com.team2.util.Action;
import com.team2.util.ActionForward;
import com.team2.user.DB.UserDTO;

public class ManagerPaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OrderDAO dao = new OrderDAO();
		List<OrderDTO> olist = new ArrayList<>();
		List<UserDTO> ulist = new ArrayList<>();
		List<PaymentDTO> plist = new ArrayList<>();
		OrderDTO odto = new OrderDTO();
		UserDTO udto = new UserDTO();
		PaymentDTO pdto = new PaymentDTO();
		String idcheck = request.getParameter("idcheck");
		
		int count=0;
		if(idcheck == null || idcheck.equals("")) {
			count = dao.getBoardCount();
		System.out.println(" M : 전체 글 개수 :" + count + "개");
		} else {
			count = dao.getBoardCount(idcheck);
			System.out.println(" 검색된 글 개수 :" + count + "개");
		}
		// 페이징처리-1 => Model
		//////////////////////// 페이징처리-1//////////////////////////

		// 페이지당 출력할 글의 개수
		int pageSize = 6; // 한페이지에 10개씩 출력

		// 페이지의 정보(몇페이지 인지 확인하는 정보)
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1"; // 페이지 정보가 없을경우 1페이지로 고정(기본값)
			
		}

		// 시작행 번호 계산 1 11 21 31 ....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		

		// 끝행 번호 계산 10 20 30 40 ...
		int endRow = currentPage * pageSize;
		//////////////////////// 페이징처리-1//////////////////////////

		// 페이징처리된 글 데이터를 가져오기(DB메서드) => Model
		ResultSet rs = null;
		
		System.out.println("**********************"+idcheck);
		if (idcheck == null || idcheck.equals("")) {
			
			ulist = dao.getUserInfo(startRow, pageSize);
			olist = dao.getOrderInfo(startRow, pageSize);
			plist = dao.getPrice(startRow, pageSize);
			  String ujList = new Gson().toJson(ulist);
			  String ojList = new Gson().toJson(olist);
			  String pjList = new Gson().toJson(plist);
	         request.setAttribute("ulist", ulist);
	         request.setAttribute("olist", olist);
	         request.setAttribute("plist", plist);
	         request.setAttribute("ujlist", ujList);
	         request.setAttribute("ojlist", ojList);
	         request.setAttribute("pjlist", pjList);
	         System.out.println(ulist.size());
	         System.out.println(olist.size());
	         System.out.println(plist.size());
	      }else {
	         if(dao.getUserInfo(idcheck, startRow, pageSize)!=null) {
	        	 ulist = dao.getUserInfo(idcheck, startRow, pageSize);
	 			olist = dao.getOrderInfo(idcheck, startRow, pageSize);
	 			plist = dao.getPrice(idcheck, startRow, pageSize);
				  String ujList = new Gson().toJson(ulist);
				  String ojList = new Gson().toJson(olist);
				  String pjList = new Gson().toJson(plist);
		         request.setAttribute("ulist", ulist);
		         request.setAttribute("olist", olist);
		         request.setAttribute("plist", plist);
		         request.setAttribute("ujlist", ujList);
		         request.setAttribute("ojlist", ojList);
		         request.setAttribute("pjlist", pjList);
		         System.out.println(ulist.size());
		         System.out.println(olist.size());
		         System.out.println(plist.size());
	         }
	      }
		

		// 테이블에 출력(반복문) => view

		// 페이징 처리 -2 => Model

		// 전체 페이지 수 => 글 / 페이지당 출력 개수
		// 50 / 10 => 5 55 / 10 => 6
		int pageCount = count / pageSize + (count % pageSize != 0 ? 1 : 0);

		// 한 화면에서 보여줄 페이지번호 개수(block) 1....10
		int pageBlock = 4;

		// 페이지 블럭의 시작번호 1~10 => 1 11~20 => 11 21~30 => 21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지 블럭의 끝 번호 1~10 => 10 11~20 => 20 21~30 => 30
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
			
		}
		

		// 페이징처리 블럭 출력 => view

		// Model => 데이터 처리동작(DB사용,계산동작들...)
		// View => 정보 전달받아서 출력

		// 계산된 정보를 view페이지로 전달 (request 영역에 저장)
		// boardList, 페이징처리 정보

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("idcheck", idcheck);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("./order/adminList.jsp");
		
		return forward;
		
		
	}

}
