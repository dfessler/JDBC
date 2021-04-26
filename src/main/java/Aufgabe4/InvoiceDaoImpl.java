package Aufgabe4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao
{
    //Database
    List<Invoice> invoices;
    DateFormat dateFormat;
    Date date1, date2;

    public InvoiceDaoImpl() throws ParseException
    {
        invoices = new ArrayList<Invoice>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date1 = dateFormat.parse("2020-09-09");
        date2 = dateFormat.parse("2021-04-25");

        Invoice invoice1 = new Invoice(0,new java.sql.Date(date1.getTime()), "PC", 900.90, true);
        Invoice invoice2 = new Invoice(1,new java.sql.Date(date2.getTime()), "Handy", 1200.99, false);

        invoices.add(invoice1);
        invoices.add(invoice2);

    }

    @Override
    public List<Invoice> getAllInvoice()
    {
        return invoices;
    }

    @Override
    public Invoice getInvoice(int id)
    {
        return invoices.get(id);
    }

    @Override
    public void updateInvoice(Invoice invoice)
    {
        invoices.get(invoice.getId()).setDate(invoice.getDate());
        invoices.get(invoice.getId()).setDescription(invoice.getDescription());
        invoices.get(invoice.getId()).setValue(invoice.getValue());
        invoices.get(invoice.getId()).setPaid(invoice.isPaid());
        System.out.println("Invoice ID: " + invoice.getId() + " updated in database!");

    }

    @Override
    public void deleteInvoice(Invoice invoice)
    {
        invoices.remove(invoice.getId());
        System.out.println("Invoice ID: " + invoice.getId() + " deleted from database!");
    }
}
