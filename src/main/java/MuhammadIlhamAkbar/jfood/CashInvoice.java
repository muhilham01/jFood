package MuhammadIlhamAkbar.jfood;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This CashInvoice Class is used to precessing invoice data if customer use cash for purchases.
 * Cashinvoice is subclass of an Invoice class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class CashInvoice extends Invoice
{
    // instance variables - replace the example below with your own
    private static PaymentType PAYMENT_TYPE = PaymentType.CASH;
    private int deliveryFee;

    /**
     * Constructor for objects of class CashInvoice.
     * this method is used to make cash invoice.
     * @param id, this is the first parameter of this method that shows invoice's id.
     * @param foods, this is the second parameter of this method that shows list of food.
     * @param customer,  this is the third parameter of this method that shwos about invoice's customer.
     * super keyword for passing parameter that required by Invoice class
     */
    public CashInvoice(int id, ArrayList<Food> foods, Customer customer)
    {
        // initialise instance variables
        super (id, foods, customer);
    }


    /**
     * Constructor for objects of class CashInvoice.
     * this method is used to make cash invoice.
     * @param id, this is the first parameter of this method that shows invoice's id.
     * @param foods, this is the second parameter of this method that shows list of food.
     * @param customer,  this is the third parameter of this method that shwos about invoice's customer.
     * @param deliveryFee, this is the fourth parameter of this method that shows price of delivery fee.
     * super keyword for passing parameter that required by Invoice class
     */
    public CashInvoice(int id, ArrayList<Food> foods, Customer customer,int deliveryFee)
    {
        super (id, foods, customer);
        this.deliveryFee = deliveryFee;
    }

    /**
     * Constructor for objects of class CashInvoice.
     * this method is used to make cash invoice.
     * @param id, this is the first parameter of this method that shows invoice's id.
     * @param foods, this is the second parameter of this method that shows list of food.
     * @param customer,  this is the third parameter of this method that shwos about invoice's customer.
     * @param deliveryFee, this is the fourth parameter of this method that shows price of delivery fee.
     * @param invoiceStatus, this is the fifth parameter of this method that shows invoice's id.
     * @param date, this is the sixth parameter of this method that shows date of invoice created.
     * @param totalPrice, this is the seventh parameter of this method that shows total price.
     * super keyword for passing parameter that required by Invoice class
     */
    public CashInvoice(int id, ArrayList<Food> foods, Customer customer, int deliveryFee, InvoiceStatus invoiceStatus, Date date, int totalPrice)
    {
        // initialise instance variables
        super (id, foods, customer, invoiceStatus, date, totalPrice);
        this.deliveryFee = deliveryFee;
    }

    /**
     * This is getPaymentType method that used to return type of payment.
     * @return PAYMENT_TYPE, return type of payment.
     */
    public PaymentType getPaymentType()
    {
         return PAYMENT_TYPE;
    }

    /**
     * This is getDeliveryFee method that used to return value/price of delivery fee.
     * @return deliveryFee, return value of deliveryFee.
     */
    public int getDeliveryFee()
    {
        return deliveryFee;
    }

    /**
     * This is setDeliveryFee method that used to set value/price of delivery fee.
     * @param deliveryFee, to set value of deliveryFee.
     */
    public void setDeliveryFee(int deliveryFee)
    {
        this.deliveryFee = deliveryFee;
    }

    /**
     * This is setTotalPrice method that used to calculate price of food that purchased.
     */
     public void setTotalPrice()
    {
        int foodPrice=0;
        for(int i = 0; i < super.getFoods().size(); i++){
            foodPrice+=super.getFoods().get(i).getPrice();
        }
        if(deliveryFee>0)
        {
            super.totalPrice=foodPrice+deliveryFee;
        } else {
            super.totalPrice=foodPrice;
        }
    }

    /**
     * This is toString method that used to print value of instance variable.
     */
    public String toString() {
        String food = " ";
        for (int i = 0; i < getFoods().size(); i++){
            food = food + getFoods().get(i).getName() + " ";
        }

        String b = "======INVOICE======\n" +
                    "FOOD :" + food;
        if(super.getDate() != null){
            b = b+ "\nDate :" + super.getDate().get(Calendar.DAY_OF_MONTH) + "-" + super.getDate().get(Calendar.MONTH) + "-"+ super.getDate().get(Calendar.YEAR) ;

        }
        b = b+"\nCustomer :" + super.getCustomer().getName();
        if(deliveryFee != 0){
            b = b+"\nDelivery Fee :"+ deliveryFee;
        }
        b = b+ "\nTotal price :" + getTotalPrice()+
                "\nStatus :" + super.getInvoiceStatus() +
                "\nPayment Type :" + PAYMENT_TYPE;
        return b;
    }
    
}
