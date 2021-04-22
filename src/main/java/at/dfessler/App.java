package at.dfessler;

import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Produkte");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
