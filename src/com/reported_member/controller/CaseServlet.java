package com.reported_member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class CaseServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	
	
	if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("csNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("案件編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			String  csNo = null;
			try {
				csNo = new String (str);
			} catch (Exception e) {
				errorMsgs.add("格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			MemberService csSvc = new MemberService();
			MemberVO caseVO = csSvc.getOneMember(csNo);
			if (caseVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("caseVO", caseVO); // 資料庫取出的empVO物件,存入req
			String url = "member_profile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("member_profile.jsp");
			failureView.forward(req, res);

			}
		}
	}
}