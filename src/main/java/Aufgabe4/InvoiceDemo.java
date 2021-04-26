package Aufgabe4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InvoiceDemo
{
    public static void main(String[] args) throws ParseException
    {
        InvoiceDao invoiceDao = new InvoiceDaoImpl();

        int insert = 0;
        do
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Wählen Sie Aus:");
            System.out.println("1) Show All Invoices");
            System.out.println("2) Get Invoice");
            System.out.println("3) Update Invoice");
            System.out.println("4) Delete Invoice");
            System.out.println("5) Exit");
            insert = scan.nextInt();

            if (insert == 1)
            {
                for (Invoice invoice : invoiceDao.getAllInvoice())
                {
                    System.out.println("Invoice ID: " + invoice.getId() + " | Date: " + invoice.getDate() + " | Description: " + invoice.getDescription() + " | Value: " + invoice.getValue() + " | Paid: " + invoice.isPaid());
                }

                System.out.println();
            }

            if (insert == 2)
            {
                int id;
                System.out.println("Which ID?");
                id = scan.nextInt();

                Invoice invoice2 = invoiceDao.getInvoice(id);
                System.out.println("Invoice ID: " + invoice2.getId() + " / Date: " + invoice2.getDate() + " / Description: " + invoice2.getDescription() + " / Value: " + invoice2.getValue() + " / Paid: " + invoice2.isPaid());
                System.out.println();
            }

            if (insert == 3)
            {
                int id;
                String date, description;
                double value;
                boolean paid;

                System.out.println("Update ID:");
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
                Invoice invoice1 = invoiceDao.getInvoice(id);
                invoice1.setDate(dateFormat.parse(date));
                invoice1.setDescription(description);
                invoice1.setValue(value);
                invoice1.setPaid(paid);
                invoiceDao.updateInvoice(invoice1);
                System.out.println();
            }

            if (insert == 4)
            {
                int id;
                System.out.println("ID:");
                id = scan.nextInt();
                Invoice invoice3 = invoiceDao.getInvoice(id);
                invoiceDao.deleteInvoice(invoice3);
                System.out.println();
            }


        } while (insert !=5 );


    }
}
