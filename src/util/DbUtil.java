package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
	private static final String url = "jdbc:oracle:thin:@ITA-031-W7:1521:xe";
	private static final String username = "LIUSU3";
	private static final String password = "LIUSU3";
	private static final String driverClass = "oracle.jdbc.OracleDriver";
	public static Connection connect(){
		Connection connection=null;
		ResultSet resultSet = null;
			try {
				Class.forName(driverClass);

				connection = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;
	}
	
	public static void free(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
		try {
			if(resultSet!=null){
				resultSet.close();
			}
			if(preparedStatement!=null){
				preparedStatement.close();
			}
			if(connection!=null){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static void main(String [] args){
	System.out.println(DbUtil.connect());
}
}

