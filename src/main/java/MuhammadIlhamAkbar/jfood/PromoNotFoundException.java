package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This PromoNotFoundException  Class is used to handle error input if promo not found
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class PromoNotFoundException extends Exception
{
    private int promo_error;

    /**
     * This is constructor for object of class PromoNotFoundException
     * <p>
     * Updates or changes value of promo_error when create objects
     * @param promo_input - first parameter value of constructor
     */
    public PromoNotFoundException  (int promo_input)
    {
        super("Promo ID:");
        this.promo_error = promo_input;
    }

    /**
     * This is getMesage method that is used to print error message
     * @return about error message
     */
    public String getMessage()
    {
        return super.getMessage() + promo_error + " not found";
    }

}
