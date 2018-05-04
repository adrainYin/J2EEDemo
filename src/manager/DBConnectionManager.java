package manager;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionManager {
    private String dbURL;
    private String user;
    private String password;
    private Connection connection;

    public DBConnectionManager(String dbURL , String user , String password){
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection(){
        return this.connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
