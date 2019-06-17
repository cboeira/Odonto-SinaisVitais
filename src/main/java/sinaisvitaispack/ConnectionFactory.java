package sinaisvitaispack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private final static String DRIVER ="com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/bancodeteste?useTimezone=true&serverTimezone=UTC";
	private final static String USER = "root";
	private final static String PASS = "0000";
	
	/*private final static String URL = "jdbc:mysql://remotemysql.com:3306/gWefRqLf1V?useTimezone=true&serverTimezone=UTC";
	private final static String USER = "gWefRqLf1V";
	private final static String PASS = "U3h8MZiO6L";*/
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(" Erro na conexão: ", e);
		}
		
	}
	
    public static void FecharConexao(Connection con) {	
    	try {
    	if(con != null) {
        	con.close();
        	}
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }

}
