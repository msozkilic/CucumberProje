package _JDBC.Gün1;

import _JDBC.Gün2.JDBCParent;
import org.testng.annotations.Test;

import java.sql.*;

public class Soru extends JDBCParent {
    //todo city tablosundaki tüm kayitlari next ile yapiniz
    @Test
    public void test() throws SQLException {
        ResultSet rs=statement.executeQuery("select * from city");

        int cnt=0;
        while(rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));


            cnt++;
        }
        System.out.println(cnt);



    }
}
