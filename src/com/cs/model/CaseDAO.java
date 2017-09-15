package com.cs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CaseDAO implements CaseDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/g5mark");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public final static String INSERT_STMT = "INSERT INTO CASE (CSNO, CSNAME, HIRERNO, CSLOC, CSPAYMENT, STARTDATE, ENDDATE, CSLEVEL, CSDESC)"
			+ "VALUES ('CS'||LPAD(CASE_SQ.NEXTVAL, 7, '0'), ?, ?, ?, ?, to_date(?,'YYYY-MM-DD'), to_date(?,'YYYY-MM-DD'), ?, ?)";
	public final static String UPDATE_BYMB_STMT = "UPDATE CASE SET CSNAME=?, CSLOC=?, CSPAYMENT=?, STARTDATE=to_date(?,'YYYY-MM-DD'), ENDDATE=to_date(?,'YYYY-MM-DD'), CSLEVEL=?, CSDESC=? WHERE CSNO = ?";
	public final static String UPDATE_BYADMIN_STMT = "UPDATE CASE SET CSSTATE = ?, CSPAYSTATE= ? WHERE CSNO = ?";
	public final static String UPDATE_FREELANCER_STMT = "UPDATE CASE SET FREELANCERNO = ? WHERE CSNO = ?";
	public final static String UPDATE_SCHEDULE_STMT = "UPDATE CASE SET SCHEDULE = ? WHERE CSNO = ?";
	public final static String UPDATE_SCHEDULE_DONE_STMT = "UPDATE CASE SET SCHEDULE = ?, REALENDDATE = sysdate WHERE CSNO = ?";
	public final static String UPDATE_HIRERSCORE = "UPDATE CASE SET HIRERSCORE = ?, HIRERCOMMENT = ? WHERE CSNO = ?";
	public final static String UPDATE_FREELANCERSCORE = "UPDATE CASE SET FREELANCERSCORE = ?, FREELANCERCOMMENT = ? WHERE CSNO = ?";
	public final static String DELETE = "DELETE FROM CASE WHERE CSNO = ?";
	public final static String FIND_BYPK_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE,CSLEVEL, STARTDATE, ENDDATE,FREELANCERSCORE FROM CASE WHERE CSNO = ?";
	public final static String GET_ALL_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE,CSLEVEL STARTDATE, ENDDATE,FREELANCERSCORE FROM CASE ORDER BY CSNO";
	public final static String GET_ALL2_STMT = "SELECT CSNAME,to_char(POSTTIME,'YYYY-MM-DD') FROM CASE ORDER BY CSNO";
	public final static String FIND_BYHIRENO_STMT = "SELECT CSNO, CSNAME, FREELANCERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT, STARTDATE, ENDDATE, POSTTIME, CSLEVEL, CSDESC"
	+ " FROM CASE WHERE CSSTATE = ? AND HIRERNO = ? ORDER BY CSNO";
	public final static String FIND_FREELANCERNO_STMT = "SELECT CSNO, CSNAME, HIRERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT, STARTDATE, ENDDATE, POSTTIME, CSLEVEL, CSDESC"
	+ " FROM CASE WHERE CSSTATE = ? AND FREELANCERNO = ? ORDER BY CSNO";
	public final static String FIND_BYCSPAYMENT_STMT = "SELECT CSNO, CSNAME, HIRERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT, STARTDATE, ENDDATE, POSTTIME, CSLEVEL, CSDESC"
	+ " FROM CASE WHERE CSPAYMENT > ?  ORDER BY CSPAYMENT DESC";
	public final static String FIND_BYCSLEVEL_STMT = "SELECT CSNO, CSNAME, HIRERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT, STARTDATE, ENDDATE, POSTTIME, CSLEVEL, CSDESC"
	+ " FROM CASE WHERE CSLEVEL = ?  ORDER BY POSTTIME DESC";

	@Override
	public void insert(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, caseVO.getCsName());
			pstmt.setString(2, caseVO.getHirerNo());
			pstmt.setString(3, caseVO.getCsLoc());
			pstmt.setInt(4, caseVO.getCsPayment());
			pstmt.setString(5, caseVO.getStartDate());
			pstmt.setString(6, caseVO.getEndDate());
			pstmt.setString(7, caseVO.getCsLevel());
			pstmt.setString(8, caseVO.getCsDesc());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	@Override
	public void updateByMb(CaseVO caseVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BYMB_STMT);
			
			pstmt.setString(1, caseVO.getCsName());
			pstmt.setString(2, caseVO.getCsLoc());
			pstmt.setInt(3, caseVO.getCsPayment());
			pstmt.setString(4, caseVO.getStartDate());
			pstmt.setString(5, caseVO.getEndDate());
			pstmt.setString(6, caseVO.getCsLevel());
			pstmt.setString(7, caseVO.getCsDesc());
			pstmt.setString(8, caseVO.getCsNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	@Override
	public void updateFreelancer(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FREELANCER_STMT);
			
			pstmt.setString(1, caseVO.getFreelancerNo());
			pstmt.setString(2, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	@Override
	public void updateByAdmin(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BYADMIN_STMT);
			
			pstmt.setString(1, caseVO.getCsState());
			pstmt.setString(2, caseVO.getCsPayState());
			pstmt.setString(3, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	@Override
	public void updateSchedule(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			if(caseVO.getSchedule() != 100){
				pstmt = con.prepareStatement(UPDATE_SCHEDULE_STMT);				
			} else {
				pstmt = con.prepareStatement(UPDATE_SCHEDULE_DONE_STMT);
			}
			
			pstmt.setInt(1, caseVO.getSchedule());
			pstmt.setString(2, caseVO.getCsNo());
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}

	@Override
	public void updateHireScoreComment(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_HIRERSCORE);
			
			pstmt.setInt(1, caseVO.getHirerScore());
			pstmt.setString(2, caseVO.getHirerComment());
			pstmt.setString(3, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}

	@Override
	public void updateFreelancerScoreComment(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FREELANCERSCORE);
			
			pstmt.setInt(1, caseVO.getFreelancerScore());
			pstmt.setString(2, caseVO.getFreelancerComment());
			pstmt.setString(3, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	@Override
	public void delete(String csNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, csNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}

	@Override
	public CaseVO findByPrimaryKey(String csNo) {
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYPK_STMT);
			
			pstmt.setString(1, csNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString(1));
				caseVO.setCsName(rs.getString(2));
				caseVO.setHirerNo(rs.getString(3));
				caseVO.setFreelancerNo(rs.getString(4));
				caseVO.setCsPayment(rs.getInt(5));
				caseVO.setCsState(rs.getString(6));
				caseVO.setCsPayState(rs.getString(7));	
				caseVO.setCsLevel(rs.getString(8));	
				caseVO.setStartDate(rs.getString(9));
				caseVO.setEndDate(rs.getString(10));
				caseVO.setFreelancerScore(rs.getInt(11));
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return caseVO;
	}

	@Override
	public List<CaseVO> findByHirerNo(String hirerNo, String csState) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYHIRENO_STMT);
			pstmt.setString(1, csState);
			pstmt.setString(2, hirerNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString(1));
				caseVO.setCsName(rs.getString(2));
				caseVO.setFreelancerNo(rs.getString(3));
				caseVO.setSchedule(rs.getInt(4));
				caseVO.setCsLoc(rs.getString(5));
				caseVO.setCsPayState(rs.getString(6));
				caseVO.setCsPayment(rs.getInt(7));
				caseVO.setStartDate(rs.getString(8));
				caseVO.setEndDate(rs.getString(9));
				caseVO.setPostTime(rs.getDate(10));
				caseVO.setCsLevel(rs.getString(11));
				caseVO.setCsDesc(rs.getString(12));
				
				list.add(caseVO);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
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
	public List<CaseVO> findByFreelancerNo(String freelancerNo, String csState) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_FREELANCERNO_STMT);
			pstmt.setString(1, csState);
			pstmt.setString(2, freelancerNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString(1));
				caseVO.setCsName(rs.getString(2));
				caseVO.setHirerNo(rs.getString(3));
				caseVO.setSchedule(rs.getInt(4));
				caseVO.setCsLoc(rs.getString(5));
				caseVO.setCsPayState(rs.getString(6));
				caseVO.setCsPayment(rs.getInt(7));
				caseVO.setStartDate(rs.getString(8));
				caseVO.setEndDate(rs.getString(9));
				caseVO.setPostTime(rs.getDate(10));
				caseVO.setCsLevel(rs.getString(11));
				caseVO.setCsDesc(rs.getString(12));
				
				list.add(caseVO);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
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
	public List<CaseVO> findByCsPayment(Integer csPayment) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYCSPAYMENT_STMT);
			pstmt.setInt(1, csPayment);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString(1));
				caseVO.setCsName(rs.getString(2));
				caseVO.setHirerNo(rs.getString(3));
				caseVO.setSchedule(rs.getInt(4));
				caseVO.setCsLoc(rs.getString(5));
				caseVO.setCsPayState(rs.getString(6));
				caseVO.setCsPayment(rs.getInt(7));
				caseVO.setStartDate(rs.getString(8));
				caseVO.setEndDate(rs.getString(9));
				caseVO.setPostTime(rs.getDate(10));
				caseVO.setCsLevel(rs.getString(11));
				caseVO.setCsDesc(rs.getString(12));
				
				list.add(caseVO);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
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
	public List<CaseVO> findByCsLevel(String csLevel) {
		
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYCSLEVEL_STMT);
			pstmt.setString(1, csLevel);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString(1));
				caseVO.setCsName(rs.getString(2));
				caseVO.setHirerNo(rs.getString(3));
				caseVO.setSchedule(rs.getInt(4));
				caseVO.setCsLoc(rs.getString(5));
				caseVO.setCsPayState(rs.getString(6));
				caseVO.setCsPayment(rs.getInt(7));
				caseVO.setStartDate(rs.getString(8));
				caseVO.setEndDate(rs.getString(9));
				caseVO.setPostTime(rs.getDate(10));
				caseVO.setCsLevel(rs.getString(11));
				caseVO.setCsDesc(rs.getString(12));
				
				list.add(caseVO);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
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
	public List<CaseVO> getAll() {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO.setCsNo(rs.getString(1));
				caseVO.setCsName(rs.getString(2));
				caseVO.setHirerNo(rs.getString(3));
				caseVO.setFreelancerNo(rs.getString(4));
				caseVO.setCsPayment(rs.getInt(5));
				caseVO.setCsState(rs.getString(6));
				caseVO.setCsPayState(rs.getString(7));
				caseVO.setCsLevel(rs.getString(8));
				caseVO.setStartDate(rs.getString(9));
				caseVO.setEndDate(rs.getString(10));
				caseVO.setFreelancerScore(rs.getInt(11));
				list.add(caseVO);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
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
	public List<CaseVO> getAll2() {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL2_STMT);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsName(rs.getString(1));
				caseVO.setPostTime(rs.getDate(2));
				list.add(caseVO);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
