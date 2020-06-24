package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This PromoCodeAlreadyExistsException Class is used to handle error input
 * if promo code already exist in database
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class PromoCodeAlreadyExistsException extends Exception
{
    private Promo promo_error;

    /**
     * This is constructor for object of class PromoCodeAlreadyExistsException
     * <p>
     * Updates or changes value of promo_error when create objects
     * @param promo_input - first parameter value of constructor
     */
    public PromoCodeAlreadyExistsException (Promo promo_input)
    {
        super("Promo Code:");
        this.promo_error = promo_input;
    }

    /**
     * This is getMesage method that is used to print error message
     * @return about error message
     */
    public String getMessage() {
        return super.getMessage() + promo_error.getCode() + " already exist.";
    }
}
