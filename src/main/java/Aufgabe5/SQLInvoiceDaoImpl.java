package Aufgabe5;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLInvoiceDaoImpl implements InvoiceDao
{
    private String url;
    private String user;
    private String pw;
    private Connection con;

    public SQLInvoiceDaoImpl()
    {
        url = "jdbc:mysql://127.0.0.1:3306/test";
        user = "root2";
        pw = "1234";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pw);

        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public List<Invoice> getAllInvoice()
    {
        List<Invoice> invoices = new ArrayList<>();

        try
        {
            String sql = "SELECT * from Invoice";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt(1);
                Date date = rs.getDate(2);
                String description = rs.getString(3);
                Double value = rs.getDouble(4);
                Boolean paid = rs.getBoolean(5);
                invoices.add(new Invoice(id,date,description,value,paid));

            }

        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public Invoice getInvoice(int id)
    {
        Invoice invoice = null;
        try
        {
            String sql = "SELECT * from Invoice where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Date date = rs.getDate(2);
                String description = rs.getString(3);
                Double value = rs.getDouble(4);
                Boolean paid = rs.getBoolean(5);
                invoice = new Invoice(id,date,description,value,paid);
            }

        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return invoice;

    }

    @Override
    public void updateInvoice(Invoice invoice)
    {
        try
        {
            String sql = "Update Invoice set date = ?, description = ?, value = ?, paid = ? where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(invoice.getDate().getTime());

            ps.setDate(1, sqlDate);
            ps.setString(2, invoice.getDescription());
            ps.setDouble(3, invoice.getValue());
            ps.setBoolean(4, invoice.isPaid());
            ps.setInt(5, invoice.getId());
            ps.executeUpdate();
            System.out.println("Invoice ID: " + invoice.getId() + " updated in database!");

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInvoice(Invoice invoice)
    {
        try
        {
            String sql = "Delete from Invoice where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoice.getId());
            ps.executeUpdate();
            System.out.println("Invoice ID: " + invoice.getId() + " deleted from database!");


        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertInvoice(Invoice invoice)
    {
        try
        {
            String sql = "insert into Invoice (date, description, value, paid) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            java.sql.Date sqlDate = new java.sql.Date(invoice.getDate().getTime());

            ps.setDate(1,sqlDate );
            ps.setString(2,invoice.getDescription());
            ps.setDouble(3, invoice.getValue());
            ps.setBoolean(4,invoice.isPaid());
            ps.executeUpdate();


        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
