package com.team2.payment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.util.Action;
import com.team2.util.ActionForward;

public class OrderFrontController extends HttpServlet {	

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
			System.out.println("C : doProcess()호출 !");
			
			/***************************** 1. 가상주소 계산 *******************************/
			System.out.println("\n C : 1. 가상주소 계산 - 시작");
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			// 계산된 가상주소
			String command = requestURI.substring(contextPath.length());
			System.out.println("command : " + command);
			System.out.println("C : 가상주소 계산 - 끝");
			/***************************** 1. 가상주소 계산 *******************************/
			
			/***************************** 2. 가상주소 매핑 *******************************/
			System.out.println("\n C : 가상주소 매핑 - 시작");
			Action action = null;
			ActionForward forward = null;
			
			// 예매첫페이지
			if(command.equals("/orderMain.or")){
				System.out.println("C : orderMain.or 호출");
				System.out.println("C : 패턴1 - 페이지이동");
				
				forward = new ActionForward();
				forward.setPath("./order/orderMain.jsp");
				forward.setRedirect(false);
			
			// 지역에 맞는 극장정보
			}else if(command.equals("/cinemaInfo.or")) {
				System.out.println("C : cinemaInfo.or 호출");
				System.out.println("C : 패턴2or3 - db사용x 비동기처리");
				
				action = new CinemaAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 극장에맞는 영화정보
			else if(command.equals("/theaterInfo.or")) {
				System.out.println("C : theaterInfo.or 호출");
				System.out.println("C : 패턴2or3 - db사용x 비동기처리");
				
				action = new TheaterAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 영화 상영시간, 영화 가격
			else if(command.equals("/movieInfo.or")) {
				System.out.println("C : movieInfo.or 호출");
				System.out.println("C : 패턴2or3 - db사용o 비동기처리");
				
				action = new MovieAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 좌석정보
			else if(command.equals("/seatPayment.or")) {
				System.out.println("C : seatPayment.or 호출");
				System.out.println("C : 패턴2 - db사용o, 페이지이동");
				
				action = new SeatPaymentAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 예매2페이지
			else if(command.equals("/seatPaymentPro.or")) {
				System.out.println("C : seatPaymentPro.or 호출");
				System.out.println("C : 패턴1 - 페이지이동 ");
				
				forward = new ActionForward();
				forward.setPath("./order/orderMainPro.jsp");
				forward.setRedirect(false);
			}
			
			// 아이디값으로 회원, 비회원 구분 후 휴대폰번호, 회원넘버, 이름정보
			else if(command.equals("/getMemberInfo.or")) {
				System.out.println("C : getMemberInfo.or 호출");
				System.out.println("C : 패턴2 - db사용o, 페이지이동");
				
				action = new MemberInfo();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 결제테이블 insert , 예매테이블 insert(회원일시 예매횟수증가)
			else if(command.equals("/paymentSuccess.or")) {
				System.out.println("C : paymentSuccess.or 호출");
				System.out.println("C : 패턴3 - db사용o, 화면출력");
				
				action = new PaymentSuccessAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			// 마이페이지 - 예매내역 
			else if(command.equals("/MyPageMain.or")) {
				System.out.println("C : MypageMain.or 호출");
				System.out.println("C : 패턴2or3 - db사용o 비동기처리");
				
				action = new GetOrderBoardAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
			// 관리자 예매내역 페이지
			else if(command.equals("/managerList.or")) {
				System.out.println("C : managerList.or 호출");
				System.out.println("C : 패턴2or3 - db사용o 비동기처리");
				
				action = new ManagerPaymentAction();
				try {
					forward = action.execute(request, response);
					System.out.println("@@@@@@@@@@"+forward);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("123@@@@@@@@@@"+forward);
				}
			}
			
			else if(command.equals("/Main.or")) {
				forward = new ActionForward();
				forward.setPath("./main.jsp");
				forward.setRedirect(false);
			}
			else if(command.equals("/cancelOrder.or")) {
				System.out.println("C : cancelOrder.or 호출");
				System.out.println("C : 패턴3 - db사용");
				
				action = new CancelOrder();
				try {
					action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("C : 가상주소 매핑 - 끝");
			/***************************** 2. 가상주소 매핑 *******************************/
			
			/***************************** 3. 가상주소 이동 *******************************/
			System.out.println("\n C : 가상주소 이동 - 시작");

			if (forward != null) { // forward != null -> 티켓이 있을때(패턴1이 정상실행)
				if (forward.isRedirect()) { // 이동방식이 true, false에따라 지정
					System.out.println("C : " + forward.getPath() + " 주소로 방식 : " + forward.isRedirect());
					response.sendRedirect(forward.getPath());
				} else {
					System.out.println("C : " + forward.getPath() + " 주소로 방식 : " + forward.isRedirect());
					RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
					dis.forward(request, response);
				}
			}
			System.out.println("C : 가상주소 이동 - 끝");
			/***************************** 3. 가상주소 이동 *******************************/
			System.out.println("========== 컨트롤러 끝 ============");
			
			
			
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		doProcess(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request,response);
	}
}
