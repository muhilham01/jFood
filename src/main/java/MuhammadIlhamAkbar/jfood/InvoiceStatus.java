package MuhammadIlhamAkbar.jfood;
/**
 * <h1>Enumeration class of InvoiceStatus -
 * this is list of status invoice
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public enum InvoiceStatus
{
    // below is list of invoice status
    ONGOING ("Ongoing"),
    FINISHED ("Finished"),
    CANCELLED ("Cancelled");
    
    private String status;

    /**
     * This is InvoiceStatus method that is used to set status value, that status is instance variable
     * @param status, set status value in string
     */
    private  InvoiceStatus (String status)
    {
        this.status = status;
    }

    /**
     * This is toString method that is used to print status in string
     */
    public String toString()
    {
        return status;
    }
}
