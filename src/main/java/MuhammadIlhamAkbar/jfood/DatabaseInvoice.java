package MuhammadIlhamAkbar.jfood;

import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseInvoice Class is used to precessing database of invoice
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseInvoice {


    private static ArrayList<Invoice> INVOICE_DATABASE = new ArrayList<>();
    private static int lastId = 0;

    public DatabaseInvoice()
    {
        // initialise instance variables
    }

    /**
     * This is getInvoiceDatabase method, that is used to get data of all invoice in database
     * @return INVOICE_DATABASE, return invoice object in array list
     */
    public static ArrayList<Invoice> getInvoiceDatabase()
    {
        return INVOICE_DATABASE;
    }

    /**
     * This is getLastId method, that is used to get last id of invoice in database
     * @return lasId, return invoice id
     */
    public static int getLastId()
    {
        return lastId;
    }

    /**
     * This is getInvoiceById method, that is used to get data of invoice in database
     * @params id, this is parameter to select invoice by id
     * @return invoice, return invoice object that is selected
     */
    public static Invoice getInvoiceById(int id) throws InvoiceNotFoundException
    {
        for (Invoice invoice : INVOICE_DATABASE)
        {
            if (invoice.getId() == id)
            {
                return invoice;
            }
        }
        throw new InvoiceNotFoundException(id);
        //return null;
    }

    /**
     * This is getInvoiceByCustomer method, that is used to get data of invoice in database
     * @params customerId, this is parameter to select invoice by customer id
     * @return invoices, return invoice object in array list that is selected
     */
    public static ArrayList<Invoice> getInvoiceByCustomer (int customerId)
    {
        ArrayList<Invoice> i = new ArrayList<>();
        for (Invoice invoice : INVOICE_DATABASE){
            if(invoice.getCustomer().getId() == customerId){
                i.add(invoice);
            }
        }
        return i;
    }

    /**
     * This is addInvoice method, that is used to add invoice in database
     * @params invoice, this is parameter to insert invoice object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean addInvoice(Invoice invoice) throws OngoingInvoiceAlreadyExistsException
    {
        for (Invoice i : INVOICE_DATABASE)
        {
            if(i.getCustomer().getEmail().equals(invoice.getCustomer().getEmail()) && i.getInvoiceStatus().equals(invoice.getInvoiceStatus()))
            {
                throw new OngoingInvoiceAlreadyExistsException(invoice);
            }
        }
        INVOICE_DATABASE.add(invoice);
        lastId = invoice.getId();
        return true;
    }

    /**
     * This is changeInvoiceStatus method, that is used to change invoice status in database
     * @params id, this is parameter to select invoice by invoice id
     * @params invoiceStatus, this is parameter to update value of status (new value)
     * @return true, if update is succeeded
     * @return false, if update is not succeeded
     */
    public static boolean changeInvoiceStatus (int id, InvoiceStatus invoiceStatus)
    {
        for (Invoice i : INVOICE_DATABASE)
        {
            if(i.getId() == id && i.getInvoiceStatus().equals(invoiceStatus.ONGOING))
            {
                i.setInvoiceStatus(invoiceStatus);
                return true;
            }
        }
        return false;
    }

    /**
     * This is removeInvoice method, that is used to remove data of invoice in database
     * @params id, this parameter to select invoice by invoice id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeInvoice(int id) throws InvoiceNotFoundException
    {
        for (Invoice i : INVOICE_DATABASE)
        {
            if(i.getId() == id)
            {
                INVOICE_DATABASE.remove(i);
                return true;
            }
        }
        throw new InvoiceNotFoundException(id);
    }
}
