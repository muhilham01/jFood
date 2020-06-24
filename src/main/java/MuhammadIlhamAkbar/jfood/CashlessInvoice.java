package MuhammadIlhamAkbar.jfood;

import java.util.*;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This CashlessInvoice Class is used to precessing invoice data if customer use cashless for purchases.
 * Cashlessinvoice is subclass of an Invoice class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class CashlessInvoice extends Invoice
{
    // instance variables - replace the example below with your own
    private static PaymentType PAYMENT_TYPE = PaymentType.CASHLESS;
    private Promo promo;

    /**
     * Constructor for objects of class CashlessInvoice.
     * this method is used to make cashless invoice.
     * @param id, this is the first parameter of this method that shows invoice's id.
     * @param foods, this is the second parameter of this method that shows list of food.
     * @param customer,  this is the third parameter of this method that shwos about invoice's customer.
     * super keyword for passing parameter that required by Invoice class
     */
    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer)
    {
        super(id, foods, customer);
        
    }

    /**
     * Constructor for objects of class CashInvoice.
     * this method is used to make cash invoice.
     * @param id, this is the first parameter of this method that shows invoice's id.
     * @param foods, this is the second parameter of this method that shows list of food.
     * @param customer,  this is the third parameter of this method that shwos about invoice's customer.
     * @param promo this is the fourth parameter of this method that shows about promo.
     * super keyword for passing parameter that required by Invoice class
     */
    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer, Promo promo)
    {
        super(id, foods, customer);
        this.promo = promo;
        
    }

    /**
     * Constructor for objects of class CashInvoice.
     * this method is used to make cash invoice.
     * @param id, this is the first parameter of this method that shows invoice's id.
     * @param foods, this is the second parameter of this method that shows list of food.
     * @param customer,  this is the third parameter of this method that shwos about invoice's customer.
     * @param promo this is the fourth parameter of this method that shows about promo.
     * @param invoiceStatus, this is the fifth parameter of this method that shows invoice's id.
     * @param date, this is the sixth parameter of this method that shows date of invoice created.
     * @param totalPrice, this is the seventh parameter of this method that shows total price.
     * super keyword for passing parameter that required by Invoice class
     */
    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer, Promo promo, InvoiceStatus invoiceStatus, Date date, int totalPrice)
    {
        super(id, foods, customer, invoiceStatus, date, totalPrice);
        this.promo = promo;
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
     * This is getPromo method that used to return about promo.
     * @return promo, return about promo.
     */
    public Promo getPromo()
    {
        return promo;
    }

    /**
     * This is setPromo method that used to set promo that selected.
     * @param promo, to set value of deliveryFee.
     */
    public void setPromo (Promo promo)
    {
        this.promo = promo;
    }

    /**
     * This is setTotalPrice method that used to calculate price of food that purchased.
     */
    public void setTotalPrice()
    {
        int totalFoodPrice =0;
        for(int i=0;i<getFoods().size();i++){
            totalFoodPrice = totalFoodPrice+ getFoods().get(i).getPrice() ;
        }

        if( promo != null){
            if (promo.getActive() == true && totalFoodPrice >promo.getMinPrice()){
                super.totalPrice = (totalFoodPrice - promo.getDiscount() );
            }
            else{
                super.totalPrice = totalFoodPrice;
            }

        }
        else{
            super.totalPrice = totalFoodPrice;
        }
    }

    /**
     * This is toString method that used to print value of instance variable.
     */
    public String toString()
    {
        String food = " ";
        for(int i = 0; i < getFoods().size(); i++){
            food = food + getFoods().get(i).getName() + " ";
        }
        String b = "======INVOICE======\n" +
                    "FOOD :" + food;
        if(super.getDate() != null){
            b = b + "\nDate :" + super.getDate().get(Calendar.DAY_OF_MONTH) + "-" + super.getDate().get(Calendar.MONTH) + "-"+ super.getDate().get(Calendar.YEAR);
        }
        b = b + "\nCustomer :" + super.getCustomer().getName();

        if(promo != null){
            b = b+"\nPromo :" + promo.getCode();
        }
        b = b+ "\nTotal price :" + getTotalPrice()+
                "\nStatus :" + super.getInvoiceStatus() +
                "\nPayment Type :" + PAYMENT_TYPE;
        return b;
    }
    
    
}
