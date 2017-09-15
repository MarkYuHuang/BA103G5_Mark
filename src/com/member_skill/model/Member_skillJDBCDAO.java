package com.member_skill.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Member_skillJDBCDAO implements Member_skillDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:Oracle:thin:@localhost:1521:XE";
	String userid = "g5mark";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO Member_skill (skillno,mbno) values (?,?)";
	private static final String DELETE = "DELETE from Applicant where skillno=? and mbno=?";
	private static final String GET_ONE_STMT = "Select skillno,mbno from Member_skill where skillno=? and mbno=?";
	private static final String GET_ALL_STMT = "Select skillno,mbno from Member_skill order by mbno";

	@Override
	public void insert(Member_skillVO member_skillVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member_skillVO.getSkillNo());
			pstmt.setString(2, member_skillVO.getMbNo());

			pstmt.executeUpdate();

			System.out.println("insert mehtod���!");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String skillNo, String mbNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, skillNo);
			pstmt.setString(2, mbNo);

			pstmt.executeUpdate();
			
			System.out.println("�������ethod ����澈�����");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Member_skillVO findByPrimaryKey(String skillNo, String mbNo) {
		Member_skillVO member_skillVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, skillNo);
			pstmt.setString(2, mbNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				member_skillVO = new Member_skillVO();
				member_skillVO.setSkillNo(rs.getString("skillno"));
				member_skillVO.setMbNo(rs.getString("mbno"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return member_skillVO;
	}

	@Override
	public List<Member_skillVO> getAll() {
		List<Member_skillVO> list = new ArrayList<Member_skillVO>();
		Member_skillVO member_skillVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				member_skillVO = new Member_skillVO();
				member_skillVO.setSkillNo(rs.getString("skillno"));
				member_skillVO.setMbNo(rs.getString("mbno"));

				list.add(member_skillVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Member_skillJDBCDAO dao = new Member_skillJDBCDAO();

	// �憓�
//		Member_skillVO member_skillVO1 = new Member_skillVO();
//		
//	member_skillVO1.setSkillNo("ST0000003");
//	member_skillVO1.setMbNo("MB0000025");
//
//	dao.insert(member_skillVO1);

//	// 靽格

//
//	// ��
//dao.delete("CS0000015","MB0000044");
//
//	// �閰�											
		Member_skillVO member_skillVO3 = dao.findByPrimaryKey("ST0000001", "MB0000024");
	System.out.print(member_skillVO3.getSkillNo() + ",");
	System.out.print(member_skillVO3.getMbNo() + ",");
	
	System.out.println("---------------------");
//
//	// �閰�
	List<Member_skillVO> list = dao.getAll();
	for (Member_skillVO aMemberSkillList : list) {
		System.out.print(aMemberSkillList.getSkillNo() + ",");
		System.out.print(aMemberSkillList.getMbNo() + "!");
		
	
		System.out.println();
	}
}
}
