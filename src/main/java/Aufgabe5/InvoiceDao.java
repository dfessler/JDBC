package Aufgabe5;

import java.util.List;

public interface InvoiceDao
{
    public List<Invoice> getAllInvoice();
    public Invoice getInvoice(int id);
    public void updateInvoice(Invoice invoice);
    public void deleteInvoice(Invoice invoice);
    public void insertInvoice(Invoice invoice);
}
