package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DeleteDao;
import util.DbUtil;

public class DeleteDaoImpl implements DeleteDao {

	@Override
	public int deleteDepart(int id) {
		String sql = "delete from depart where id=?";
		String sqlDP="delete from person where departId=?";
		Connection con = null;
		PreparedStatement pst = null;
		PreparedStatement pstDP = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			m = pst.executeUpdate();
			pstDP = con.prepareStatement(sqlDP);
			pstDP.setInt(1, id);
			m = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return m;
	}

	@Override
	public int deletePerson(int id) {
		String sql = "delete from person where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			m = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return m;
	}

}
