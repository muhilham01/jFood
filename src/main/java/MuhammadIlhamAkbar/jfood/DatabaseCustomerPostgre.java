package MuhammadIlhamAkbar.jfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseCostumerPostgre Class is used to precessing database of customer
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseCustomerPostgre extends DatabaseConnectionPostgre {

    /**
     * This is insertCustomer method, that is used to add customer in database
     * @params customer, this is parameter to insert customer object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static Boolean insertCustomer(Customer customer) throws EmailAlreadyExistsException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        for (Customer i : getCustomerDatabase())
        {
            if(i.getEmail().equals(customer.getEmail()))
            {
                throw new EmailAlreadyExistsException (customer);
                // return false;
            }
        }
        try {
            statement = c.prepareStatement("INSERT INTO customer (name, email, password) values (?,?,?) RETURNING id;");
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getEmail());
            statement.setString(3,customer.getPassword());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return true;
    }

    /**
     * This is getLastCustomerId method, that is used to get last id of customer in database
     * @return id, return costumer id
     */
    public static int getLastCustomerId()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = c.prepareStatement("SELECT id FROM customer;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return id;
    }

    /**
     * This is getCustomer method, that is used to get data of customer in database
     * @params customerEmail, this is first parameter to select customer data by email
     * @params customerPassword, this is second parameter to select customer data by password
     * @return customer, return costumer object that is selected
     */
    public static Customer getCustomer(String customerEmail, String customerPassword)
    {
        Connection c = connection();
        PreparedStatement statement;
        int id = 0;
        String name = null;
        String email = null;
        String password = null;
        Customer customer = null;
        try {
            statement = c.prepareStatement("SELECT id, name, email, password, joindate from customer where email=? and password=?;");
            statement.setString(1, customerEmail);
            statement.setString(2, customerPassword);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                name = rs.getString("name");
                email = rs.getString("email");
                password = rs.getString("password");
            }
            statement.close();
            c.close();
            customer = new Customer(id, name, email, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return customer;
    }

    /**
     * This is removeCustomer method, that is used to remove data of customer in database
     * @params customerId, this is parameter to select customer by id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeCustomer(int customerId) throws CustomerNotFoundException
    {
        boolean  success= true;
        Connection c = connection();
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM customer WHERE id=?;";
            statement = c.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.execute();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return success;
    }

    /**
     * This is getCustomerById method, that is used to get data of customer in database
     * @params customerId, this parameter to select customer by id
     * @return customer, return costumer object that is selected
     */
    public static Customer getCustomerById(int customerId) throws CustomerNotFoundException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        String name = null;
        String email = null;
        String password = null;
        Date date = null;
        String str_date = null;
        Customer customer = null;
        try {
            statement = c.prepareStatement("SELECT * FROM customer WHERE id=?;");
            statement.setInt(1,customerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                name = rs.getString("name");
                email = rs.getString("email");
                password = rs.getString("password");
                date =  rs.getDate("joinDate");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                customer = new Customer(id, name, email, password, calendar);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        if(customer == null){
            throw new CustomerNotFoundException(customerId);
        }
        return customer;
    }

    /**
     * This is getCustomerDatabase method, that is used to get data of all customer in database
     * @return customers, return costumer object in array list
     */
    public static ArrayList<Customer> getCustomerDatabase()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Customer customer = null;
        ArrayList<Customer> customers = new ArrayList<>();
        int id = 0;
        String customerName = null;
        String customerEmail = null;
        String customerPassword = null;
        Date date = null;
        String str_date = null;

        try {
            statement = c.prepareStatement("SELECT * FROM customer;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                customerName = rs.getString("name");
                customerEmail = rs.getString("email");
                customerPassword = rs.getString("password");
                //customerJoinDate = rs.getString("joindate");
                date =  rs.getDate("joinDate");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                customer = new Customer (id, customerName, customerEmail, customerPassword,calendar);
                customers.add(customer);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return customers;
    }
}
