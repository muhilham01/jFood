package MuhammadIlhamAkbar.jfood;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseCostumer Class is used to precessing database of customer
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class DatabaseCustomer {
    // instance variables - replace the example below with your own
    // private static String[] listFood;
    private static ArrayList<Customer> CUSTOMER_DATABASE = new ArrayList<>();
    private static int lastId = 0;

    /**
     * Constructor for objects of class DatabaseCustomer
     */
    public DatabaseCustomer() {
        // initialise instance variables
    }

    /**
     * This is getCustomerDatabase method, that is used to get data of all customer in database
     * @return CUSTOMER_DATABASE, return costumer object in array list
     */
    public static ArrayList<Customer> getCustomerDatabase() {
        return CUSTOMER_DATABASE;
    }

    /**
     * This is getLastCustomerId method, that is used to get last id of customer in database
     * @return lasId, return costumer id
     */
    public static int getLastId() {
        return lastId;
    }

    /**
     * This is getCustomerById method, that is used to get data of customer in database
     * @params id, this parameter to select customer by id
     * @return customer, return costumer object that is selected
     */
    public static Customer getCustomerById(int id) throws CustomerNotFoundException
    {
        for (Customer customers : CUSTOMER_DATABASE)
        {
            if (customers.getId() == id)
            {
                return customers;
            }
        }
        throw new CustomerNotFoundException(id);
        //return null;
    }

    /**
     * This is addCustomer method, that is used to add customer in database
     * @params customer, this is parameter to insert customer object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean addCustomer(Customer customer) throws EmailAlreadyExistsException
    {
        for (Customer i : CUSTOMER_DATABASE)
        {
            if(i.getEmail().equals(customer.getEmail()))
            {
                throw new EmailAlreadyExistsException (customer);
               // return false;
            }
        }
        CUSTOMER_DATABASE.add(customer);
        lastId = customer.getId();
        return true;
    }

    /**
     * This is removeCustomer method, that is used to remove data of customer in database
     * @params customerId, this parameter to select customer by id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeCustomer(int id) throws CustomerNotFoundException
    {
        // put your code here
        for(Customer customer : CUSTOMER_DATABASE)
        {
            if(customer.getId() == id)
            {
                CUSTOMER_DATABASE.remove(customer);
                return true;
            }
        }
        throw new CustomerNotFoundException(id);
        //return false;
    }

    /**
     * This is getCustomerLogin method, that is used to get data of customer in database
     * @params email, this is first parameter to select customer data by email
     * @params password, this is second parameter to select customer data by password
     * @return customer, return costumer object that is selected
     */
    public static Customer getCustomerLogin(String email, String password) {
        for (Customer customer : CUSTOMER_DATABASE) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer;
            }
        }

        return null;
    }


}
