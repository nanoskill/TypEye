package admin;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;


public class MysqlMgr {
    private static String DATABASE_URL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";

    private Connection connection;
    private Properties properties;
    private Statement statement;
    
    public MysqlMgr()
    {
    	if(DATABASE_URL == "")
    	{
    		File file = new File("src/config/mysql_config.ini"); 
    		Scanner scanner = null;
    		String config = "";
    		try {
				scanner = new Scanner(file);
				while(scanner.hasNextLine()) { 
					String temp = scanner.nextLine();
						if(temp.length()!=0 && temp.charAt(0) != '#')
							config = config + temp; 
	            } 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally { 
	            scanner.close(); 
	        } 
    		  
    		String configs[] = config.split(",");
    		USERNAME = configs[0];
    		PASSWORD = configs[1];
    		DATABASE_URL = configs[2];
    	}
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

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
    
    public ResultSet query(String query) throws SQLException{
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    
    public int insert(String insertQuery) throws SQLException {
        statement = connection.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
    }
    
    public boolean tableExist(String tableName) throws SQLException {
        boolean tExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) { 
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }
}