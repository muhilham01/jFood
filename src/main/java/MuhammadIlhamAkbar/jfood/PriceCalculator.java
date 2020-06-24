package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This PriceCalculator Class is used to calculate price in thread mode
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class PriceCalculator implements Runnable{
    private Invoice invoice;

    /**
     * This is constructor for object of class Invoice
     * <p>
     * Updates or changes value of invoice
     * @param invoice - first parameter value of constructor
     */
    PriceCalculator(Invoice invoice)
    {
        this.invoice = invoice;
    }

    /**
     * This is run method that used to run calculate price
     */
    public void run()
    {
        System.out.println("calculating invoice id: " + invoice.getId());
        invoice.setTotalPrice();
        System.out.println("finish calculating invoice id: " + invoice.getId());
    }

}
