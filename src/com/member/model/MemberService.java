package com.member.model;




import java.sql.Date;
import java.util.List;

public class MemberService {

	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMember( String mbNo,
	 String mbLstName,
	 String mbFstName,
	 Integer csTimes,
	 Integer csSuccessTimes,
	 Integer csStopTimes,
	 String comName,
	 String eMail,
	 String mbPw,
	 String mbLoc,
	 String mbAddress,
	 String phone,
	 String mbUserName,
	 String secQuestion,
	 String secAnswer,
	 String suspensionStatus,
	 byte[] poritait,
	 Date sinceDate,
	 String mbIntroduce) {
	
	MemberVO memberVO =new MemberVO();
	
	memberVO.setMbNo(mbNo);
	memberVO.setMbLstName(mbLstName);
	memberVO.setMbFstName(mbFstName);
	memberVO.setCsTimes(csTimes);
	memberVO.setCsSuccessTimes(csSuccessTimes);
	memberVO.setCsStopTimes(csStopTimes);
	memberVO.setComName(comName);
	memberVO.seteMail(eMail);
	memberVO.setMbPw(mbPw);
	memberVO.setMbLoc(mbLoc);
	memberVO.setMbAddress(mbAddress);
	memberVO.setPhone(phone);
	memberVO.setMbUserName(mbUserName);
	memberVO.setSecQuestion(secQuestion);
	memberVO.setSecAnswer(secAnswer);
	memberVO.setSuspensionStatus(suspensionStatus);
	memberVO.setPoritait(poritait);
	memberVO.setSinceDate(sinceDate);
	memberVO.setMbIntroduce(mbIntroduce);
	
	dao.insert(memberVO);
	return memberVO;
	}
	
	public MemberVO updateMember( String mbNo,
			 String mbLstName,
			 String mbFstName,
			 Integer csTimes,
			 Integer csSuccessTimes,
			 Integer csStopTimes,
			 String comName,
			 String eMail,
			 String mbPw,
			 String mbLoc,
			 String mbAddress,
			 String phone,
			 String mbUserName,
			 String secQuestion,
			 String secAnswer,
			 String suspensionStatus,
			 byte[] poritait,
			 Date sinceDate,
			 String mbIntroduce) {
			
			MemberVO memberVO =new MemberVO();
			
			memberVO.setMbNo(mbNo);
			memberVO.setMbLstName(mbLstName);
			memberVO.setMbFstName(mbFstName);
			memberVO.setCsTimes(csTimes);
			memberVO.setCsSuccessTimes(csSuccessTimes);
			memberVO.setCsStopTimes(csStopTimes);
			memberVO.setComName(comName);
			memberVO.seteMail(eMail);
			memberVO.setMbPw(mbPw);
			memberVO.setMbLoc(mbLoc);
			memberVO.setMbAddress(mbAddress);
			memberVO.setPhone(phone);
			memberVO.setMbUserName(mbUserName);
			memberVO.setSecQuestion(secQuestion);
			memberVO.setSecAnswer(secAnswer);
			memberVO.setSuspensionStatus(suspensionStatus);
			memberVO.setPoritait(poritait);
			memberVO.setSinceDate(sinceDate);
			memberVO.setMbIntroduce(mbIntroduce);
			
			dao.update(memberVO);
			return memberVO;
			}
	
			public List<MemberVO>getAll(){
				return dao.getAll();
			}
			
			public MemberVO getOneMember(String mbNo) {
				return dao.findByPrimaryKey(mbNo);
			}

}
