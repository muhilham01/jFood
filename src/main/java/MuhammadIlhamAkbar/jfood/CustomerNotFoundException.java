package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This CustomerNotFoundException Class is used to handle error input if coustomer not found
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class CustomerNotFoundException extends Exception
{
    private int customer_error;

    /**
     * This is constructor for object of class CustomerNotFoundException
     * <p>
     * Updates or changes value of customer_error when create objects
     * @param customer_input - first parameter value of constructor
     */
    public CustomerNotFoundException (int customer_input)
    {
        super("Customer ID:");
        this.customer_error = customer_input;
    }

    /**
     * This is getMesage method that is used to print error message
     * @return about error message
     */
    public String getMessage()
    {
        return super.getMessage() + customer_error + " not found";
    }
}
