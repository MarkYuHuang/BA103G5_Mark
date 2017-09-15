package com.reported_member.model;

import java.util.List;

public class Reported_memberService {
	private Reported_memberDAO_interface dao;
	
	public Reported_memberService() {
		dao=new Reported_memberDAO();
	}
	public Reported_memberVO addReported_member(String reportno,String reportmbno,String reportedmbno,String reportreason,String reportdesc,String reportresult) {
		Reported_memberVO reported_memberVO=new Reported_memberVO();
		
		reported_memberVO.setReportNo(reportno);
		reported_memberVO.setReportMbno(reportmbno);
		reported_memberVO.setReportedMbno(reportedmbno);
		reported_memberVO.setReportReason(reportreason);
		reported_memberVO.setReportDesc(reportdesc);
		reported_memberVO.setReportResult(reportresult);
		dao.insert(reported_memberVO);
	
	return reported_memberVO;
	}
	public Reported_memberVO updateReported_member(String reportno,String reportmbno,String reportedmbno,String reportreason,String reportdesc,String reportresult) {
		Reported_memberVO reported_memberVO=new Reported_memberVO();
		
		reported_memberVO.setReportNo(reportno);
		reported_memberVO.setReportMbno(reportmbno);
		reported_memberVO.setReportedMbno(reportedmbno);
		reported_memberVO.setReportReason(reportreason);
		reported_memberVO.setReportDesc(reportdesc);
		reported_memberVO.setReportResult(reportresult);
		dao.update(reported_memberVO);
	
	return reported_memberVO;
		
	}
	public void deleteReported_member( String reportno) {
		dao.delete(reportno);
}
	public Reported_memberVO getOneReported_member(String reportno) {
		return dao.findByPrimaryKey(reportno);
	}
	public List<Reported_memberVO>getAll(){
		return dao.getAll();
	}
}

