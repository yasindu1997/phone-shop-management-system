package lk.acpt.demofx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton design pattern
public class DBConnection {
    private static DBConnection dbConnection;

    private final Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        //Driver class loaded to the ram
        Class.forName("com.mysql.cj.jdbc.Driver");

        //create a connection with the given database server and database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_4","root","acpt");
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }

    public  Connection getConnection(){
        return connection;
    }
}
