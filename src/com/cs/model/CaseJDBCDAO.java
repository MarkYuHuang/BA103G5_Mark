package com.cs.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaseJDBCDAO implements CaseDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "G5MArk";
	String passwd = "123456";
	
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
	public final static String FIND_BYPK_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE,CSLEVEL, STARTDATE, ENDDATE, FREELANCERSCORE FROM CASE WHERE CSNO = ?";
	public final static String GET_ALL_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE ,CSLEVEL STARTDATE, ENDDATE, FREELANCERSCORE FROM CASE ORDER BY CSNO";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_FREELANCER_STMT);
			
			pstmt.setString(1, caseVO.getFreelancerNo());
			pstmt.setString(2, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_BYADMIN_STMT);
			
			pstmt.setString(1, caseVO.getCsState());
			pstmt.setString(2, caseVO.getCsPayState());
			pstmt.setString(3, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			if(caseVO.getSchedule() != 100){
				pstmt = con.prepareStatement(UPDATE_SCHEDULE_STMT);				
			} else {
				pstmt = con.prepareStatement(UPDATE_SCHEDULE_DONE_STMT);
			}
			
			pstmt.setInt(1, caseVO.getSchedule());
			pstmt.setString(2, caseVO.getCsNo());
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_HIRERSCORE);
			
			pstmt.setInt(1, caseVO.getHirerScore());
			pstmt.setString(2, caseVO.getHirerComment());
			pstmt.setString(3, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_FREELANCERSCORE);
			
			pstmt.setInt(1, caseVO.getFreelancerScore());
			pstmt.setString(2, caseVO.getFreelancerComment());
			pstmt.setString(3, caseVO.getCsNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, csNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("已更新" +a+"筆資料");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);			
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
				list.add(caseVO);
			}
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL2_STMT);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				caseVO = new CaseVO();
				caseVO.setCsName(rs.getString(1));
				caseVO.setPostTime(rs.getDate(2));
				list.add(caseVO);
			}
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		
		CaseJDBCDAO dao = new CaseJDBCDAO();
		
		//insert 
		//member csTimes++
//		CaseVO caseVO1 = new CaseVO();
//		caseVO1.setCsName("手工餅乾網站建置");
//		caseVO1.setHirerNo("MB0000029");
//		caseVO1.setCsLoc("桃園市");
//		caseVO1.setCsPayment(5000);
//		caseVO1.setStartDate("2017-10-12");
//		caseVO1.setEndDate("2018-10-10");
//		caseVO1.setCsLevel("小");
//		caseVO1.setCsDesc("想找人幫我做我們家手工餅乾販賣的網站，謝謝");
//		dao.insert(caseVO1);
		
		//update by member
//		CaseVO caseVO2 = new CaseVO();
//		caseVO2.setCsName("用網頁爬蟲抓氣象局網站");
//		caseVO2.setCsLoc("台北市");
//		caseVO2.setCsPayment(10000);
//		caseVO2.setStartDate("2017-10-12");
//		caseVO2.setEndDate("2018-10-10");
//		caseVO2.setCsLevel("小");
//		caseVO2.setCsDesc("抓抓");
//		caseVO2.setCsNo("CS0000002");
//		dao.updateByMb(caseVO2);
		
		//update Freelancer
//		CaseVO caseVO4 = new CaseVO();
//		caseVO4.setFreelancerNo("MB0000023");
//		caseVO4.setCsNo("CS0000010");
//		dao.updateFreelancer(caseVO4);
		
		//update by Admin
//		CaseVO caseVO3 = new CaseVO();
//		caseVO3.setCsState("進行中");
//		caseVO3.setCsPayState("已付款");
//		caseVO3.setCsNo("CS0000010");
//		dao.updateByAdmin(caseVO3);
		
		
		//update schedule
//		CaseVO caseVO5 = new CaseVO();
//		//not 100%
//		caseVO5.setSchedule(40);
//		//100%  
		//members successTimes++
//		caseVO5.setSchedule(100);
//		caseVO5.setCsNo("CS0000010");
//		dao.updateSchedule(caseVO5);
		
		//update hirerScore and Comment
//		CaseVO caseVO6 = new CaseVO();
//		caseVO6.setHirerComment("不錯的老闆");
//		caseVO6.setHirerScore(5);
//		caseVO6.setCsNo("CS0000010");
//		dao.updateHireScoreComment(caseVO6);
		
		//update freelancerScore and Comment
//		CaseVO caseVO7 = new CaseVO();
//		caseVO7.setFreelancerScore(5);
//		caseVO7.setFreelancerComment("網站做的超棒的");
//		caseVO7.setCsNo("CS0000010");
//		dao.updateFreelancerScoreComment(caseVO7);
		
		//delete case
		//case_type delete too
//		dao.delete("CS0000008");
		
		//search for one
//		CaseVO caseVO8 = dao.findByPrimaryKey("CS0000010");
//		System.out.println(caseVO8.getCsNo());
//		System.out.println(caseVO8.getCsName());
//		System.out.println(caseVO8.getHirerNo());
//		System.out.println(caseVO8.getFreelancerNo());
//		System.out.println(caseVO8.getCsPayment());
//		System.out.println(caseVO8.getCsState());
//		System.out.println(caseVO8.getCsPayState());
		
		//search All
//		List<CaseVO> list = dao.getAll();
//		for(CaseVO caseVO: list){
//			System.out.print(caseVO.getCsNo() + ",");
//			System.out.print(caseVO.getCsName() + ",");
//			System.out.print(caseVO.getHirerNo() + ",");
//			System.out.print(caseVO.getFreelancerNo() + ",");
//			System.out.print(caseVO.getCsPayment() + ",");
//			System.out.print(caseVO.getCsState() + ",");
//			System.out.print(caseVO.getCsPayState() + ",");
//			System.out.println();
//		}
		
		//search for android
//		List<CaseVO> list = dao.getAll2();
//		for(CaseVO caseVO: list){
//			System.out.print(caseVO.getCsName() + ",");
//			System.out.print(caseVO.getPostTime());
//			System.out.println();
//		}
		
		//find by hirerNo
		CaseVO caseVO = dao.findByPrimaryKey("CS0000002");
		//for(CaseVO caseVO : list){
			System.out.print(caseVO.getCsNo() + ",");
			System.out.print(caseVO.getCsName() + ",");
			System.out.print(caseVO.getFreelancerNo() + ",");
			System.out.print(caseVO.getSchedule() + ",");
			System.out.print(caseVO.getCsLoc() + ",");
			System.out.print(caseVO.getCsPayState() + ",");
			System.out.print(caseVO.getCsPayment() + ",");
			System.out.print(caseVO.getStartDate() + ",");
			System.out.print(caseVO.getEndDate() + ",");
			System.out.print(caseVO.getPostTime() + ",");
			System.out.print(caseVO.getCsLevel() + ",");
			System.out.print(caseVO.getCsDesc() + ",");
			System.out.println();
		//}
		
		//find by case Payment
//		List<CaseVO> list = dao.findByCsPayment(5000);
//		for(CaseVO caseVO : list){
//			System.out.print(caseVO.getCsNo() + ",");
//			System.out.print(caseVO.getCsName() + ",");
//			System.out.print(caseVO.getFreelancerNo() + ",");
//			System.out.print(caseVO.getSchedule() + ",");
//			System.out.print(caseVO.getCsLoc() + ",");
//			System.out.print(caseVO.getCsPayState() + ",");
//			System.out.print(caseVO.getCsPayment() + ",");
//			System.out.print(caseVO.getStartDate() + ",");
//			System.out.print(caseVO.getEndDate() + ",");
//			System.out.print(caseVO.getPostTime() + ",");
//			System.out.print(caseVO.getCsLevel() + ",");
//			System.out.print(caseVO.getCsDesc() + ",");
//			System.out.println();
//		}
		//find by case Payment
//				List<CaseVO> list = dao.findByCsLevel("小");
//				for(CaseVO caseVO : list){
//					System.out.print(caseVO.getCsNo() + ",");
//					System.out.print(caseVO.getCsName() + ",");
//					System.out.print(caseVO.getFreelancerNo() + ",");
//					System.out.print(caseVO.getSchedule() + ",");
//					System.out.print(caseVO.getCsLoc() + ",");
//					System.out.print(caseVO.getCsPayState() + ",");
//					System.out.print(caseVO.getCsPayment() + ",");
//					System.out.print(caseVO.getStartDate() + ",");
//					System.out.print(caseVO.getEndDate() + ",");
//					System.out.print(caseVO.getPostTime() + ",");
//					System.out.print(caseVO.getCsLevel() + ",");
//					System.out.print(caseVO.getCsDesc() + ",");
//					System.out.println();
//				}
		
		
	}

	

		
	

	

	

	

}
