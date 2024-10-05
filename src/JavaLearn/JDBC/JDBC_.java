package JavaLearn.JDBC;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC_ {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Driver driver = new Driver();
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","root");
        Connection connect = driver.connect("jdbc:mysql://localhost:3306/db1", properties);

        Statement statement = connect.createStatement();
//        String sql="insert into tb1 values(1,1,'jdbc',null)";
        String sql="select id from tb1";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1));
        }
        statement.close();
        connect.close();
    }
}
