package finalproject.db;
import java.sql.*;
import java.util.ArrayList;

import finalproject.entities.Person;
/**
 * @author Barry
 * */
public class DBInterface {

    /* implementing or using this class isn't strictly required, but
     * you might want to abstract some of the interactions with and queries
     * to the database to a separate class.
     */

    Connection conn;

    public DBInterface() {

    }
    public Connection getConn() {
        return this.conn;
    }

    public void setConnection(String dbFileName) throws SQLException {
        try{
            this.conn = DriverManager.getConnection(dbFileName);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
