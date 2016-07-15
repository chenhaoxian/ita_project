package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DepartDao;
import po.Customer;
import po.Depart;
import util.DbUtil;

public class DepartDaoImpl implements DepartDao {

	@Override
	public int addDepart(Depart depart) {
		String sql = "insert into depart (id,dname,city) values (department.nextval,?,?)";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, depart.getDname());
			pst.setString(2, depart.getCity());
			m = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}

		return m;
	}

	@Override
	public int deleteDepart(int id) {
		String sql = "delete from depart where id=?";
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

	@Override
	public int updateDepart(Depart depart) {
		String sql = "update depart set dname=?,city=? where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setString(1, depart.getDname());
			pst.setString(2, depart.getCity());
			pst.setInt(3, depart.getId());
			m = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}

		return m;
	}

	@Override
	public List<Depart> showAllDepart() {
		List<Depart> ds = new ArrayList<Depart>();

		String sql = "select * from depart";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String dname = rs.getString("dname");
				String city = rs.getString("city");
				Depart depart  = new Depart(dname, city);
				depart.setId(id);
				ds.add(depart);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return ds;
	}

	@Override
	public Depart loadDepart(int id) {
		Depart departNew=null;

		String sql = "select * from depart where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String dname = rs.getString("dname");
				String city = rs.getString("city");
				departNew = new Depart(dname, city);
				departNew.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return departNew;
	}

}
