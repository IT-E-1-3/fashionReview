package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.vo.MemberShipVO;

import oracle.jdbc.OracleTypes;

import com.controller.DBmanager.*;

public class MemberShipDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private MemberShipDAO() {
	}// 싱글턴 패턴

	private static MemberShipDAO instance = new MemberShipDAO();

	public static MemberShipDAO getInstance() {
		return instance;
	}

	public MemberShipVO getMemberShipALL(String id) {

		MemberShipVO membershipVO = null;

		String runSP = "{ call membership_pack.membership_select_all_user_id(?, ?) }";

		try {
			conn = DBManager.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setString(1, id);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

			try {
				callableStatement.execute();

				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

				membershipVO = new MemberShipVO();
				while (resultSet.next()) {
					String tId = resultSet.getString(1);
					String tGrade = resultSet.getString(2);
					int tPostCount = resultSet.getInt(3);
					int tMPoint = resultSet.getInt(4);

					membershipVO.setId(tId);
					membershipVO.setGrade(tGrade);
					membershipVO.setPost_count(tPostCount);
					membershipVO.setPoint(tMPoint);
				}
				conn.close();
				callableStatement.close();
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membershipVO;
	}

	// TOP 3 view
//	public ArrayList<MemberShipVO> selectTopPostRankers() {
//		ArrayList<MemberShipVO> rankerList = new ArrayList<MemberShipVO>();
//		try {
//
//			// sql --> table --> view
//			String query = "SELECT * from v_1";
//			System.out.println(query);
//			// DHCP
//			conn = DBManager.getConnection();
//			pstmt = conn.prepareStatement(query);
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				String user_id = rs.getString("c_1");
//				String grade = rs.getString("c_2");
//				int post_count = rs.getInt("c_3");
//				int m_point = rs.getInt("c_4");
//				
//				MemberShipVO ranker = new MemberShipVO();
//				ranker.setId(user_id);
//				ranker.setGrade(grade);
//				ranker.setPost_count(post_count);
//				ranker.setPoint(m_point);
//
//				rankerList.add(ranker);
//			} // end while
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} // end try
//		return rankerList;
//	}// end
}
