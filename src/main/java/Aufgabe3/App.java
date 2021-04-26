package Aufgabe3;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Scanner;


public class App
{
    static Connection con;

    public static void main(String[] args) throws ParseException {

        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root2";
        String pw = "1234";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pw);

        } catch(Exception e)
        {
            e.printStackTrace();
        }

        int insert = 0;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Wählen Sie Aus:");
            System.out.println("1) Show Invoices");
            System.out.println("2) Insert Invoice");
            System.out.println("3) Update Invoice");
            System.out.println("4) Delete Invoice");
            System.out.println("5) Exit");
            insert = scan.nextInt();

            if (insert == 1) {
                showInvoices();
            }
            if (insert == 2) {
                String date, description;
                double value;
                boolean paid;
                System.out.println("Date (Format yyyy-mm-dd):");
                date = scan.next();
                System.out.println("Description:");
                description = scan.next();
                System.out.println("Value:");
                value = scan.nextDouble();
                System.out.println("Paid (true/false):");
                paid = scan.nextBoolean();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                insertInvoice(dateFormat.parse(date), description, value, paid);
            }

            if (insert == 3) {
                int id;
                String date, description;
                double value;
                boolean paid;
                System.out.println("ID:");
                id = scan.nextInt();
                System.out.println("Date (Format yyyy-mm-dd):");
                date = scan.next();
                System.out.println("Description:");
                description = scan.next();
                System.out.println("Value:");
                value = scan.nextDouble();
                System.out.println("Paid (true/false):");
                paid = scan.nextBoolean();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                updateInvoice(id, dateFormat.parse(date), description, value, paid);
            }

            if (insert == 4) {
                int id;
                System.out.println("ID:");
                id = scan.nextInt();
                deleteInvoice(id);
            }

            System.out.println();
        } while (insert !=5 );

        try
        {
            con.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }






    }

    public static void showInvoices() {
        try
        {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Invoice");

            // 4. Process the result set
            while (rs.next()) {

                System.out.println(rs.getInt(1) + " " + rs.getDate(2) + " " + rs.getString(3) + " " + rs.getDouble(4) + " " + rs.getBoolean(5));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertInvoice(Date date, String description, double value, Boolean paid) {

        try {

            String sql = "insert into Invoice (date, description, value, paid) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            ps.setDate(1, sqlDate);
            ps.setString(2, description);
            ps.setDouble(3, value);
            ps.setBoolean(4, paid);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateInvoice(int id, Date date, String description, double value, Boolean paid) {
        try {

            String sql = "Update Invoice set date = ?, description = ?, value = ?, paid = ? where id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            ps.setDate(1, sqlDate);
            ps.setString(2,description);
            ps.setDouble(3,value);
            ps.setBoolean(4,paid);
            ps.setInt(5,id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void deleteInvoice(int id)
    {
        try {

            String sql = "Delete from Invoice where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}






