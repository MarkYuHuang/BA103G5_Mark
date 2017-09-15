package com.cs.model;

import java.util.List;


public interface CaseDAO_interface {
	public void insert(CaseVO caseVO);
	
    public void updateByMb(CaseVO caseVO);
    public void updateFreelancer(CaseVO caseVO);
    public void updateByAdmin(CaseVO caseVO);    
    public void updateSchedule(CaseVO caseVO);
    public void updateHireScoreComment(CaseVO caseVO);
    public void updateFreelancerScoreComment(CaseVO caseVO);
    
    public void delete(String csNo);
    
    public CaseVO findByPrimaryKey(String csNo);
    public List<CaseVO> findByHirerNo(String hirerNo, String csState);
    public List<CaseVO> findByFreelancerNo(String freelancerNo, String csState);
    public List<CaseVO> findByCsPayment(Integer csPayment);
    public List<CaseVO> findByCsLevel(String csLevel);
    public List<CaseVO> getAll();
    
    public List<CaseVO> getAll2();
}
