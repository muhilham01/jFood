package MuhammadIlhamAkbar.jfood;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This Invoice Class used to precessing Invoice data
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public abstract class Invoice
{
    private int id;
    ArrayList<Food> foods = new ArrayList();
    private Calendar date;
    private Date dateInsert;
    protected int totalPrice;
    private Customer customer;
    private InvoiceStatus invoiceStatus;

    /**
     * This is constructor for object of class Invoice
     * <p>
     * Updates or changes id, idFood, date, date, customer, totalPrice
     * @param id - first parameter value of constructor, which becomes invoice identifier
     * @param foods - second parameter value of constructor that type is arraylist
     * @param customer - fourth parameter value of constructor, who received the invoice
     */
    public Invoice(int id, ArrayList<Food> foods, Customer customer)
    {
        this.id = id;
        this.foods = foods;
        this.customer = customer;
        this.invoiceStatus = InvoiceStatus.ONGOING;
        this.date = Calendar.getInstance();
        
    }

    /**
     * This is constructor for object of class Invoice
     * <p>
     * Updates or changes id, idFood, date, date, customer, totalPrice
     * @param id - first parameter value of constructor, which becomes invoice identifier
     * @param foods - second parameter value of constructor that type is arraylist
     * @param date - third parameter value of constructor, which becomes invoice date
     * @param customer - fourth parameter value of constructor, who received the invoice
     * @param totalPrice - fifth paramter value of constructor, total price value at that invoice
     */
    public Invoice(int id, ArrayList<Food> foods, Customer customer, InvoiceStatus invoiceStatus, Date date, int totalPrice)
    {
        this.id = id;
        this.foods = foods;
        this.customer = customer;
        this.invoiceStatus = invoiceStatus;
        this.dateInsert = date;
        this.totalPrice = totalPrice;

    }

    /**
     * This is getId method that used to return id value
     * @return id, return invoice id value
     */
    public int getId() {
        return id;
    }
    
    /**
     * This is getFoods method that used to return foods in arraylist
     * @return foods, return value of foods list
     */
    public ArrayList<Food> getFoods()
    {
        return foods;
    }
    
    /**
     * This is getDate method that used to return value of date
     * @return date, return value of date
     */
    public Calendar getDate()
    {
        return date;
    }

    /**
     * This is getDateInsert method that used to return value of date when insert data
     * @return dateInsert, return value of date when insert data
     */
    public Date getDateInsert()
    {
        return dateInsert;
    }
    
    /**
     * This is getTotalPrice method that used to return totalPrice value
     * @return totalPrice, return value of total price
     */
    public int getTotalPrice()
    {
        return totalPrice;
    }
    
    /**
     * This is getCustomer method that used to return data of customer
     * @return customer, return value of costumer that data type is object customer
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * This is PaymentType method in abstract form
     */
    public abstract PaymentType getPaymentType();

    /**
     * This is getInvoiceStatus method that used to value of invoice status
     * @return invoiceStatus, return value of invoice status
     */
    public InvoiceStatus getInvoiceStatus() 
    {
        return invoiceStatus;
    }

    /**
     * This is setId method that used to set id value, that id is instance variable
     * @param id, set id in integer
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * This is setFood method that used to set value of foods in array list, that foods is instance variable
     * @param foods, set foods in array list
     */
    public void setFood(ArrayList<Food> foods) {
        this.foods = foods;
    }
    
    /**
     * This is setDate method that used to set date value, that date is instance variable
     * @param date, set date in string
     */
    public void setDate(Calendar date)
    {
        this.date = date;
    }

    /**
     * This is setDate method that used to set date value in year, month, and day.
     * @param year, set year in integer
     * @param month, set month in integer
     * @param year, set day in integer
     */
    public void setDate (int year, int month, int dayOfMonth)
    {
        this.date = new GregorianCalendar (year, month-1, dayOfMonth);
    }

    /**
     * This is setTotalPrice method in abstract form
     */
    public abstract void setTotalPrice();

    /**
     * This is setCustomer method that used to set customer value, that customer is instance variable
     * @param customer, set customer that data type is object customer
     */
    public void setCustomer (Customer customer)
    {
        this.customer = customer;
    }

    /**
     * This is setInvoiceStatus method that used to set value of status invoice
     * @param invoiceStatus, set invoiceStatus that data type is enum of InvoiceStatus
     */
    public void setInvoiceStatus(InvoiceStatus invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * This is setTotalPrice method in abstract form
     */
    public abstract String toString();
    
}
