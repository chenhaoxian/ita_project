package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.AddDao;
import po.Depart;
import po.Person;
import util.DbUtil;

public class AddDaoImpl implements AddDao {

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
	public int addPerson(Person person) {
		String sql = "insert into person (id,cname,birth,tel,departId,salary) values (sun.nextval,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, person.getCname());
			pst.setString(2, person.getBirth());
			pst.setString(3, person.getTel());
			pst.setInt(4, person.getDepartId());
			pst.setInt(5, person.getSalary());
			m = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}

		return m;
	}

}
