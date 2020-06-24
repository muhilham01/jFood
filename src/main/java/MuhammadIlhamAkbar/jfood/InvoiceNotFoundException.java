package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This InvoiceNotFoundException Class is used to handle error input if invoice not found
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class InvoiceNotFoundException extends Exception
{
    private int invoice_error;

    /**
     * This is constructor for object of class InvoiceNotFoundException
     * <p>
     * Updates or changes value of invoice_error when create objects
     * @param invoice_input - first parameter value of constructor
     */
    public InvoiceNotFoundException  (int invoice_input)
    {
        super("Invoice ID:");
        this.invoice_error = invoice_input;
    }

    /**
     * This is getMesage method that is used to print error message
     * @return about error message
     */
    public String getMessage()
    {
        return super.getMessage() + invoice_error + " not found";
    }
}
