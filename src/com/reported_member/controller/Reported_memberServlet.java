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
import com.reported_member.model.Reported_memberService;
import com.reported_member.model.Reported_memberVO;

public class Reported_memberServlet extends HttpServlet{


		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			
		

	        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					
					
					String reportno = new String(req.getParameter("reportno").trim());
					String reportmbno = req.getParameter("reportmbno").trim();
					String reportedmbno = req.getParameter("reportedmbno").trim();	
					String reportreason = req.getParameter("reportreason").trim();
					String reportdesc = req.getParameter("reportdesc").trim();	
					String reportresult = req.getParameter("reportresult").trim();
					
				

					Reported_memberVO reported_memberVO = new Reported_memberVO();
					reported_memberVO.setReportNo(reportno);
					reported_memberVO.setReportMbno(reportmbno);
					reported_memberVO.setReportedMbno(reportedmbno);
					reported_memberVO.setReportReason(reportreason);
					reported_memberVO.setReportDesc(reportdesc);
					reported_memberVO.setReportResult(reportresult);
					
		

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reported_memberVO", reported_memberVO); // 含有輸入格式錯誤的reported_memberVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/reported/addmember.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					Reported_memberService empSvc = new Reported_memberService();
					reported_memberVO = empSvc.addReported_member(reportno,reportmbno,reportedmbno,reportreason,reportdesc,reportresult);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/reported/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reported/addmember.jsp");
					failureView.forward(req, res);
				}
			}
		}
	}
	
