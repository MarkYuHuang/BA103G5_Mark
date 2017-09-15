package com.cs.model;

	import java.sql.Date;
	import java.util.List;

	import com.member.model.MemberDAO;
	import com.member.model.MemberDAO_interface;
	import com.member.model.MemberVO;

	public class CaseService {
		
		private CaseDAO_interface dao;
		
		public  CaseService() {
			dao = new CaseDAOJNDIDAO();
		}
		public CaseVO addCase( String csNo,
		 String csName,
		 String csState,
		 String hirerNo,
		 String freelancerNo,
		 Integer schedule,
		 String csLoc,
		 String csPayState,
		 Integer csPayment,
		 Integer hirerScore,
		 Integer freelancerScore,
		 String hirerComment,
		 String freelancerComment,
		 String startDate,
		 String endDate,
		 Date realEndDate,
		 Date postTime,
		 String csLevel,
		 String csDesc) {
		
			CaseVO caseVO =new CaseVO();
			caseVO.setCsName(csName);
			caseVO.setCsState(csState);
			caseVO.setHirerNo(hirerNo);
			caseVO.setFreelancerNo(freelancerNo);
			caseVO.setSchedule(schedule);
			caseVO.setCsLoc(csLoc);
			caseVO.setCsPayState(csPayState);
			caseVO.setCsPayment(csPayment);
			caseVO.setHirerComment(hirerComment);
			caseVO.setFreelancerComment(freelancerComment);
			caseVO.setStartDate(startDate);
			caseVO.setEndDate(endDate);
			caseVO.setPostTime(postTime);
			caseVO.setCsLevel(csLevel);
			caseVO.setCsDesc(csDesc);
			
			dao.insert(caseVO);
			return caseVO;
			
		}
			public CaseVO updateCase( String csNo,
					 String csName,
					 String csState,
					 String hirerNo,
					 String freelancerNo,
					 Integer schedule,
					 String csLoc,
					 String csPayState,
					 Integer csPayment,
					 Integer hirerScore,
					 Integer freelancerScore,
					 String hirerComment,
					 String freelancerComment,
					 String startDate,
					 String endDate,
					 Date realEndDate,
					 Date postTime,
					 String csLevel,
					 String csDesc) {
					
						CaseVO caseVO =new CaseVO();
						caseVO.setCsNo(csNo);
						caseVO.setCsName(csName);
						caseVO.setCsState(csState);
						caseVO.setHirerNo(hirerNo);
						caseVO.setFreelancerNo(freelancerNo);
						caseVO.setSchedule(schedule);
						caseVO.setCsLoc(csLoc);
						caseVO.setCsPayState(csPayState);
						caseVO.setCsPayment(csPayment);
						caseVO.setHirerComment(hirerComment);
						caseVO.setFreelancerComment(freelancerComment);
						caseVO.setStartDate(startDate);
						caseVO.setEndDate(endDate);
						caseVO.setPostTime(postTime);
						caseVO.setCsLevel(csLevel);
						caseVO.setCsDesc(csDesc);
						
						dao.updateByMb(caseVO);
						return caseVO;
	}
	public List<CaseVO>getAll(){
		return dao.getAll();
	}
			
	public CaseVO getOneCase(String csNo) {
		return dao.findByPrimaryKey(csNo);
	}
	public CaseVO getCase(String csNo){
		return dao.findByPrimaryKey(csNo);
	}
			
}

