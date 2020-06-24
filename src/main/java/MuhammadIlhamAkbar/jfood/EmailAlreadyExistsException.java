package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This EmailAlreadyExistsException Class is used to handle error input if email already exist in database
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class EmailAlreadyExistsException extends Exception
{
    private Customer customer_error;

    /**
     * This is constructor for object of class EmailAlreadyExistsException
     * <p>
     * Updates or changes value of customer_error when create objects
     * @param customer_input - first parameter value of constructor
     */
    public EmailAlreadyExistsException (Customer customer_input)
    {
        super("Customer Email :");
        this.customer_error= customer_input;
    }

    /**
     * This is getMesage method that is used to print error message
     * @return about error message
     */
    public String getMessage()
    {
        return super.getMessage() + customer_error.getEmail() + " already exist.";
    }
}
