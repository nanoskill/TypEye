import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class MysqlMgr {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/typeye";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;
    private Properties properties;
    private Statement statement;

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