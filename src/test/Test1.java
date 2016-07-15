package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
	private static final String url = "jdbc:oracle:thin:@ITA-031-W7:1521:xe";
	private static final String username = "LIUSU3";
	private static final String password = "LIUSU3";
	private static final String driverClass = "oracle.jdbc.OracleDriver";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(driverClass);

			connection = DriverManager.getConnection(url, username, password);
			System.out.println(connection);

			String cname = "C";
//			String sql = "insert into customer(id,cname) values (Sunvin_seq01.nextval,'Abc')";
			String sql = "insert into customer(id,cname) values (Sunvin_seq01.nextval,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cname);
			boolean b = preparedStatement.execute();
			connection.setAutoCommit(false);
			int m = preparedStatement.executeUpdate();
			connection.commit();

			System.out.println(b);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				// report
			}
			e.printStackTrace();
		} finally {

			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
