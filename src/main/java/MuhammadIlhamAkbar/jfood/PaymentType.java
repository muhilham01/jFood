package MuhammadIlhamAkbar.jfood;
/**
 * <h1>Enumeration class of PaymentType -
 * this is list of payment type
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public enum PaymentType
{
    // below is list of payment type
    CASHLESS ("Cashless"), 
    CASH ("Cash");
    
    private String payment;

    /**
     * This is PaymentType method that is used to set payment value, that payment is instance variable
     * @param payment, set status value in string
     */
    private PaymentType (String payment)
    {
        this.payment = payment;
    }

    /**
     * This is toString method that is used to print payment in string
     */
    public String toString()
    {
        return payment;
    }
}
