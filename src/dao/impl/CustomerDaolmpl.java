package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import oracle.jdbc.util.RepConversion;
import po.Customer;
import util.DbUtil;

public class CustomerDaolmpl implements CustomerDao {

	@Override
	public int addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.connect();
			String sql = "insert into customer(id,cname) values (Sunvin_seq01.nextval,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCname());
//			boolean b = preparedStatement.execute();
			connection.setAutoCommit(false);
			int m = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(connection, preparedStatement, null);
		}
		return 0;
	}

	@Override
	public int deleteCustomer(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.connect();
			String sql = "delete from customer where id=?";
			preparedStatement = connection.prepareStatement(sql);
//			boolean b = preparedStatement.execute();
			connection.setAutoCommit(false);
			int m = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(connection, preparedStatement, null);
		}
		return 0;
	}

	@Override
	public int updateCustomer(Customer customer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.connect();
			String sql = "update customer set cname=?,age=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCname());
			preparedStatement.setLong(2, customer.getAge());
			preparedStatement.setLong(3, customer.getId());
//			boolean b = preparedStatement.execute();
			connection.setAutoCommit(false);
			int m = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(connection, preparedStatement, null);
		}
		return 0;
	}

	@Override
	public List<Customer> showAllCustomers() {
		List<Customer> customers= new ArrayList<Customer>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.connect();
			String sql = "select * from customer";
			preparedStatement = connection.prepareStatement(sql);
//			boolean b = preparedStatement.execute();
			ResultSet resultSet=null;
			resultSet =preparedStatement.executeQuery();
			while(resultSet.next()){
				int id=resultSet.getInt("id");
				String cname=resultSet.getString("cname");
				int age=resultSet.getInt("age");
				Customer customer=new  Customer(cname, age);
				customer.setId(id);
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
			
		 finally {
			DbUtil.free(connection, preparedStatement, null);
		}
		return customers;
	}

	@Override
	public Customer loadCustomer(int id) {
		Customer c=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.connect();
			String sql = "select * from customer where id=?";
			preparedStatement = connection.prepareStatement(sql);
//			boolean b = preparedStatement.execute();
			ResultSet resultSet=null;
			preparedStatement.setInt(1,	 id);
			resultSet =preparedStatement.executeQuery();
			if(resultSet.next()){
				String cname=resultSet.getString("cname");
				int age=resultSet.getInt("age");
				Customer customer=new  Customer(cname, age);
				customer.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}
			
		 finally {
			DbUtil.free(connection, preparedStatement, null);
		}
		return c;
	}

}
