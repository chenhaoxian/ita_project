package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ShowListDao;
import po.Depart;
import po.Person;
import util.DbUtil;

public class ShowListDaoImpl implements ShowListDao {

	@Override
	public List<Depart> showAllDepart() {
		List<Depart> ds = new ArrayList<Depart>();

		String sql = "select * from depart";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
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
	public List<Person> showAllPerson() {
		List<Person> ps = new ArrayList<Person>();

		String sql = "select * from person";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String cname = rs.getString("cname");
				String birth = rs.getString("birth");
				String tel = rs.getString("tel");
				int departId = rs.getInt("departId");
				int salary = rs.getInt("salary");
				Person person  = new Person(cname,birth,tel,departId,salary);
				person.setId(id);
				ps.add(person);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return ps;
	}

	@Override
	public List<Person> findPersonByDid(int id) {
		List<Person> ps = new ArrayList<Person>();

		String sql = "select * from person left join depart on person.departId= depart.id where depart.id=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		Person person  = new Person();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			while (rs.next()) {
				person.setId(rs.getInt("id")); 
				person.setCname(rs.getString("cname"));
				person.setBirth(rs.getString("birth"));
				person.setTel(rs.getString("tel"));
				person.setDepartId(rs.getInt("departId"));
				person.setSalary(rs.getInt("salary"));
				person.setCity(rs.getString("city"));
				person.setDname(rs.getString("dname"));
				person.setSalary(rs.getInt("salary"));
				ps.add(person);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return ps;
	}

}
