package Aufgabe3;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Scanner;


public class App {

    public static void main(String[] args) throws ParseException {

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






    }

    public static void showInvoices() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root2", "1234");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Invoice");

            // 4. Process the result set
            while (rs.next()) {

                System.out.println(rs.getInt(1) + " " + rs.getDate(2) + " " + rs.getString(3) + " " + rs.getDouble(4) + " " + rs.getBoolean(5));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertInvoice(Date date, String description, double value, Boolean paid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root2", "1234");

            // 3. Execute statement


            String sql = "insert into Invoice (date, description, value, paid) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String lastCrawlDate = dateFormat.format(date);
            Date utilDate = dateFormat.parse(lastCrawlDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            ps.setDate(1, sqlDate);
            ps.setString(2, description);
            ps.setDouble(3, value);
            ps.setBoolean(4, paid);
            ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateInvoice(int id, Date date, String description, double value, Boolean paid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root2", "1234");

            String sql = "Update Invoice set date = ?, description = ?, value = ?, paid = ? where id = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String lastCrawlDate = dateFormat.format(date);
            Date utilDate = dateFormat.parse(lastCrawlDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
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
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Get Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root2", "1234");

            String sql = "Delete from Invoice where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}






