package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public final static String INSERT_STMT = "INSERT INTO MEMBER (MBNO, MBLSTNAME, MBFSTNAME, COMNAME, EMAIL, MBPW, MBLOC, MBADDRESS, "
			+ "PHONE, MBUSRNAME, PORTRAIT, MBINTRODUCE) VALUES( 'MB'||lpad(member_sq.nextval,7,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	public final static String UPDATE_STMT = "UPDATE MEMBER SET MBLSTNAME = ?, MBFSTNAME = ?, COMNAME = ?, MBLOC = ?, MBADDRESS = ?, "
			+ "PHONE = ?, MBUSRNAME = ?, PORTRAIT = ?, MBINTRODUCE = ? WHERE MBNO = ?";
	public final static String UPDATE_SEC_STMT = "UPDATE MEMBER SET SECQUESTION = ?, SECANSWER = ? WHERE MBNO = ?";
	public final static String GET_ALL_STMT = "SELECT * FROM MEMBER";
	public final static String FIND_BY_SUCCESSTIMES_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, MBLOC, PORTRAIT, MBINTRODUCE FROM MEMBER"
			+ " WHERE CSSUCCESSTIMES >= ? ORDER BY CSSUCCESSTIMES";
	public final static String FIND_BY_LOC_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, MBLOC, PORTRAIT, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBLOC LIKE ? ORDER BY MBLOC";
	public final static String FIND_BY_SUSPENSIONSTATUS_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS ,MBINTRODUCE FROM MEMBER"
			+ " WHERE SUSPENSIONSTATUS = ? ORDER BY MBNO";
	public final static String FIND_BYPK_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBNO = ?";
	public final static String LOGIN_CHECK_STMT = "SELECT EMAIL, MBPW FROM MEMBER WHERE MBNO = ?";
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memberVO.getMbLstName());
			pstmt.setString(2, memberVO.getMbFstName());
			pstmt.setString(3, memberVO.getComName());
			pstmt.setString(4, memberVO.geteMail());
			pstmt.setString(5, memberVO.getMbPw());
			pstmt.setString(6, memberVO.getMbLoc());
			pstmt.setString(7, memberVO.getMbAddress());
			pstmt.setString(8, memberVO.getPhone());
			pstmt.setString(9, memberVO.getMbUserName());
			pstmt.setBytes(10, memberVO.getPoritait());
			pstmt.setString(11, memberVO.getMbIntroduce());
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		
	}
	
	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, memberVO.getMbLstName());
			pstmt.setString(2, memberVO.getMbFstName());
			pstmt.setString(3, memberVO.getComName());
			pstmt.setString(4, memberVO.getMbLoc());
			pstmt.setString(5, memberVO.getMbAddress());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setString(7, memberVO.getMbUserName());
			pstmt.setBytes(8, memberVO.getPoritait());
			pstmt.setString(9, memberVO.getMbIntroduce());
			pstmt.setString(10, memberVO.getMbNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void setSecQuestion(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SEC_STMT);
			
			pstmt.setString(1, memberVO.getSecQuestion());
			pstmt.setString(2, memberVO.getSecAnswer());
			pstmt.setString(3, memberVO.getMbNo());			
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setComName(rs.getString(7));
				memberVO.seteMail(rs.getString(8));
				memberVO.setMbLoc(rs.getString(10));
				memberVO.setMbAddress(rs.getString(11));
				memberVO.setPhone(rs.getString(12));
				memberVO.setMbUserName(rs.getString(13));
				memberVO.setSecQuestion(rs.getString(14));
				memberVO.setSecAnswer(rs.getString(15));
				memberVO.setSuspensionStatus(rs.getString(16));
				memberVO.setPoritait(rs.getBytes(17));
				memberVO.setSinceDate(rs.getDate(18));
				memberVO.setMbIntroduce(rs.getString(19));
				list.add(memberVO);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public MemberVO findByPrimaryKey(String MBNO) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYPK_STMT);
			pstmt.setString(1, MBNO);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setMbLoc(rs.getString(7));
				memberVO.setPoritait(rs.getBytes(8));
				memberVO.setSuspensionStatus(rs.getString(9));
				memberVO.setMbIntroduce(rs.getString(10));
				System.out.print(rs.getString(10));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> findBySuccessTimes(Integer csSuccessTimes) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SUCCESSTIMES_STMT);
			pstmt.setInt(1, csSuccessTimes);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));				
				memberVO.setMbLoc(rs.getString(6));
				memberVO.setPoritait(rs.getBytes(7));
				memberVO.setMbIntroduce(rs.getString(8));
				list.add(memberVO);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<MemberVO> findByLoc(String mbLoc) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_LOC_STMT);
			pstmt.setString(1, "%" + mbLoc + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));				
				memberVO.setMbLoc(rs.getString(6));
				memberVO.setPoritait(rs.getBytes(7));
				memberVO.setMbIntroduce(rs.getString(8));
				list.add(memberVO);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<MemberVO> findBySuspenionStatus(String suspensionStatus) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SUSPENSIONSTATUS_STMT);
			pstmt.setString(1, suspensionStatus);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setMbLoc(rs.getString(7));
				memberVO.setPoritait(rs.getBytes(8));
				memberVO.setSuspensionStatus(rs.getString(9));
				list.add(memberVO);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public MemberVO loginCheck(String eMail) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_CHECK_STMT);
			pstmt.setString(1, eMail);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.seteMail(rs.getString(1));
				memberVO.setMbPw(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memberVO;
	}
}
