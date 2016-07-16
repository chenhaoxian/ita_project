package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UpdateDao;
import po.Depart;
import po.Person;
import util.DbUtil;

public class UpdateDaoImpl implements UpdateDao {

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
	public int updatePerson(Person person) {
		String sql = "update person set cname=?,birth=?,tel=?,departId=?,salary=? where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setString(1, person.getCname());
			pst.setString(2, person.getBirth());
			pst.setString(3, person.getTel());
			pst.setInt(4, person.getDepartId());
			pst.setInt(5, person.getSalary());
			pst.setInt(6, person.getId());
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
	public Depart loadDepart(int id) {
		Depart departNew=null;

		String sql = "select * from depart where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
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

	@Override
	public Person loadPerson(int id) {
		Person personNew=null;

		String sql = "select * from person where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String cname = rs.getString("cname");
				String birth = rs.getString("birth");
				String tel = rs.getString("tel");
				int departId = rs.getInt("departId");
				int salary = rs.getInt("salary");
				personNew = new Person(cname, birth,tel,departId,salary);
				personNew.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return personNew;
	}

}
