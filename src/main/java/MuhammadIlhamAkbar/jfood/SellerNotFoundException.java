package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This SellerNotFoundException Class is used to handle error input if seller not found
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class SellerNotFoundException extends Exception
{
    private int seller_error;

    /**
     * This is constructor for object of class SellerNotFoundException
     * <p>
     * Updates or changes value of seller_error when create objects
     * @param seller_input - first parameter value of constructor
     */
    public SellerNotFoundException (int seller_input)
    {
        super("Seller ID:");
        this.seller_error = seller_input;
    }

    /**
     * This is getMesage method that is used to print error message
     * @return about error message
     */
    public String getMessage()
    {
        return super.getMessage() + seller_error + " not found";
    }
}
