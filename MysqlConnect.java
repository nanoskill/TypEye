import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class MysqlConnect {
    // init database constants
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/typeye";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                System.out.println("Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    /*public static void queryUser(String user) throws SQLException
    {
    	PreparedStatement ps = null;
	    String query = "CREATE TABLE `users` (" +
	    		"`dataid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ," + 
	    		"`date` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP ," + 
	    		"`name` VARCHAR(20) NOT NULL ," +
	    		"`id` VARCHAR(10) NOT NULL ," +
	    		"PRIMARY KEY (`dataid`(10)))";
	    try
	    {
	    	ps = connection.
	    	ps.execute();
	
	    } catch (SQLException e )
	    {
	        e.printStackTrace();
	    } finally{
	        if (ps != null) { ps.close(); }
	    }
    }*/
    /*
    public void query()
    {
    	Statement stmt = null;
    	stmt.exec
    	try {
			connection.nativeSQL(createtable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
}