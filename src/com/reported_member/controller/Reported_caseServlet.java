package com.reported_member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reported_case.model.Reported_caseService;
import com.reported_case.model.Reported_caseVO;

public class Reported_caseServlet extends HttpServlet{


		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			
			if ("getOne_For_Display".equals(action)) { // 來自Reported_case.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("rpCaseNo");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入檢舉案件編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/Reported_case.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					String rpCaseNo = null;
					try {
						rpCaseNo = new String(str);
					} catch (Exception e) {
						errorMsgs.add("檢舉案件編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/Reported_case.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					Reported_caseService empSvc = new Reported_caseService();
					Reported_caseVO reported_caseVO = empSvc.getOneReported_case(rpCaseNo);
					if (reported_caseVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/Reported_case.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("reported_caseVO", reported_caseVO); // 資料庫取出的reported_caseVO物件,存入req
					String url = "/emp/listOneEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/Reported_case.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String rpCaseNo = new String(req.getParameter("rpCaseNo"));
					
					/***************************2.開始查詢資料****************************************/
					Reported_caseService empSvc = new Reported_caseService();
					Reported_caseVO reported_caseVO = empSvc.getOneReported_case(rpCaseNo);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("reported_caseVO", reported_caseVO);         // 資料庫取出的reported_caseVO物件,存入req
					String url = "/emp/update_emp_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/listAllEmp.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String rpCaseNo = new String(req.getParameter("rpCaseNo").trim());
					String rpedMb = req.getParameter("rpedMb").trim();
					String rpedCase = req.getParameter("rpedCase").trim();	
					String rpReason = req.getParameter("rpReason").trim();
					String rpDesc = req.getParameter("rpDesc").trim();	
					String rpResult = req.getParameter("rpResult").trim();	
					
					

					Reported_caseVO reported_caseVO = new Reported_caseVO();
					reported_caseVO.setRpCaseNo(rpCaseNo);
					reported_caseVO.setRpedMb(rpedMb);
					reported_caseVO.setRpedCase(rpedCase);
					reported_caseVO.setRpReason(rpReason);
					reported_caseVO.setRpDesc(rpDesc);
					reported_caseVO.setRpResult(rpResult);
			

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reported_caseVO", reported_caseVO); // 含有輸入格式錯誤的reported_caseVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/update_emp_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					Reported_caseService empSvc = new Reported_caseService();
					reported_caseVO = empSvc.updateReported_case(rpCaseNo, rpedMb, rpedCase,rpReason, rpDesc, rpResult);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("reported_caseVO", reported_caseVO); // 資料庫update成功後,正確的的reported_caseVO物件,存入req
					String url = "/emp/listOneEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
				}
			}

	        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String rpCaseNo = new String(req.getParameter("rpCaseNo").trim());
					String rpedMb = req.getParameter("rpedMb").trim();
					String rpedCase = req.getParameter("rpedCase").trim();	
					String rpReason = req.getParameter("rpReason").trim();
					String rpDesc = req.getParameter("rpDesc").trim();	
					String rpResult = req.getParameter("rpResult").trim();	
					
				

					Reported_caseVO reported_caseVO = new Reported_caseVO();
					reported_caseVO.setRpCaseNo(rpCaseNo);
					reported_caseVO.setRpedMb(rpedMb);
					reported_caseVO.setRpedCase(rpedCase);
					reported_caseVO.setRpReason(rpReason);
					reported_caseVO.setRpDesc(rpDesc);
					reported_caseVO.setRpResult(rpResult);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reported_caseVO", reported_caseVO); // 含有輸入格式錯誤的reported_caseVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					Reported_caseService empSvc = new Reported_caseService();
					reported_caseVO = empSvc.addReported_case(rpCaseNo, rpedMb, rpedCase,rpReason, rpDesc, rpResult);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String rpCaseNo = new String(req.getParameter("rpCaseNo"));
					
					/***************************2.開始刪除資料***************************************/
					Reported_caseService empSvc = new Reported_caseService();
					empSvc.deleteReported_case(rpCaseNo);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/listAllEmp.jsp");
					failureView.forward(req, res);
				}
			}
		}
	}
