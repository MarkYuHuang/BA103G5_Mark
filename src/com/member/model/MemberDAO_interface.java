package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
	public void setSecQuestion(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public MemberVO findByPrimaryKey(String mbNo);//email--public MemberVO findByPrimaryKey(String eMail);
    public List<MemberVO> findBySuccessTimes(Integer csSuccessTimes);
    public List<MemberVO> findByLoc(String mbLoc);
    public List<MemberVO> findBySuspenionStatus(String suspensionStatus);
    public List<MemberVO> getAll();
    public MemberVO loginCheck(String eMail);
 
    
    
    
}
