package MuhammadIlhamAkbar.jfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseInvoicePostgre Class is used to precessing database of invoice
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseInvoicePostgre extends DatabaseConnectionPostgre
{
    /**
     * This is insertCashInvoice method, that is used to add invoice in database
     * @params invoice, this is parameter to insert invoice object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean insertCashInvoice(CashInvoice invoice) throws OngoingInvoiceAlreadyExistsException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        for (Invoice i : getInvoiceDatabase())
        {
            if(i.getCustomer().getEmail().equals(invoice.getCustomer().getEmail()) && i.getInvoiceStatus().equals(invoice.getInvoiceStatus()))
            {
                throw new OngoingInvoiceAlreadyExistsException(invoice);
            }
        }
        //inserting value
        try {
            statement = c.prepareStatement("INSERT INTO invoice (total_price, customer_id, invoice_status, delivery_fee, promo_code, payment_type) values (?,?,?,?,?,?) RETURNING id;");
            statement.setInt(1,invoice.getTotalPrice());
            statement.setInt(2,invoice.getCustomer().getId());
            statement.setString(3,invoice.getInvoiceStatus().toString());
            statement.setInt(4,invoice.getDeliveryFee());
            statement.setString(5,null);
            statement.setString(6,invoice.getPaymentType().toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            statement.close();
            c.close();
            insertInvoiceFood(id, invoice.getFoods());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return true;
    }

    /**
     * This is insertCashlessInvoice method, that is used to add invoice in database
     * @params invoice, this is parameter to insert invoice object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean insertCashlessInvoice(CashlessInvoice invoice) throws OngoingInvoiceAlreadyExistsException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        for (Invoice i : getInvoiceDatabase())
        {
            if(i.getCustomer().getEmail().equals(invoice.getCustomer().getEmail()) && i.getInvoiceStatus().equals(invoice.getInvoiceStatus()))
            {
                throw new OngoingInvoiceAlreadyExistsException(invoice);
            }
        }
        //inserting value
        try {
            statement = c.prepareStatement("INSERT INTO invoice (total_price, customer_id, invoice_status, delivery_fee, promo_code, payment_type) values (?,?,?,?,?,?) RETURNING id;");
            statement.setInt(1,invoice.getTotalPrice());
            statement.setInt(2,invoice.getCustomer().getId());
            statement.setString(3,invoice.getInvoiceStatus().toString());
            statement.setInt(4,0);
            if( invoice.getPromo() == null){
                statement.setString(5,null);
            }else{
                statement.setString(5,invoice.getPromo().getCode());
            }
            statement.setString(6,invoice.getPaymentType().toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            statement.close();
            c.close();
            insertInvoiceFood(id, invoice.getFoods());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return true;
    }

    /**
     * This is insertCashlessInvoice method, that is used to add invoice in database
     * @params idInvoice, this is parameter to insert invoice id into database at table food list
     * @params foods, this is parameter to insert foods in array list into database at table food list
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean insertInvoiceFood(int idInvoice, ArrayList<Food> foods)
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        for (Food food : foods)
        {
            try{
                statement = c.prepareStatement("INSERT INTO invoice_food (invoice_id, food_id) values (?,?) RETURNING id;");
                statement.setInt(1,idInvoice);
                statement.setInt(2,food.getId());
                ResultSet rs = statement.executeQuery();
                statement.close();
                c.close();
            }catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
            }

        }
        return true;
    }

    /**
     * This is getInvoiceDatabase method, that is used to get data of all invoice in database
     * @return invoices, return invoice object in array list
     */
    public static ArrayList<Invoice> getInvoiceDatabase()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int total_price = 0;
        Customer customer = null;
        String invoice_status = null;
        int delivery_fee = 0;
        Promo promo = null;
        Date date = null;
        String str_date = null;
        String payment_type = null;
        int id = 0;
        InvoiceStatus invoiceStatus = null;
        PaymentType paymentType = null;
        Invoice invoice = null;
        ArrayList<Food> foods = null;
        ArrayList<Invoice> invoices = new ArrayList<>();
        try{
            statement = c.prepareStatement("SELECT * FROM invoice;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foods = getInvoiceFood(id);
                total_price = rs.getInt("total_price");
                customer = DatabaseCustomerPostgre.getCustomerById(rs.getInt("customer_id"));
                delivery_fee = rs.getInt("delivery_fee");
                promo = DatabasePromoPostgre.getPromoByCode(rs.getString("promo_code"));
                str_date =  rs.getString("date");
                DateFormat formatterToDate = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
                date = formatterToDate.parse(str_date);
                invoice_status = rs.getString("invoice_status");
                if (invoice_status.equals("Ongoing")){
                    invoiceStatus = InvoiceStatus.ONGOING;
                }else if (invoice_status.equals("Finished")){
                    invoiceStatus = InvoiceStatus.FINISHED;
                }else if (invoice_status.equals("Cancelled")){
                    invoiceStatus = InvoiceStatus.CANCELLED;
                }
                payment_type = rs.getString("payment_type");
                if (payment_type.equals("Cash")){
                    paymentType = PaymentType.CASH;
                    invoice = new CashInvoice(id, foods, customer, delivery_fee, invoiceStatus, date, total_price);
                }else if (payment_type.equals("Cashless")){
                    paymentType = PaymentType.CASHLESS;
                    invoice = new CashlessInvoice(id, foods, customer, promo, invoiceStatus, date, total_price);
                }
                invoices.add(invoice);
            }
            statement.close();
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return invoices;
    }

    /**
     * This is getInvoiceFood method, that is used to get data of food in invoice_food table
     * @params idInvoice, this is parameter to select food by food id in invoice_food table
     * @return foods, return food in array list that is selected
     */
    public static ArrayList<Food> getInvoiceFood(int idInvoice)
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        int invoice_id = 0;
        Food food = null;
        int kuantitas = 0;
        ArrayList<Food> foods_temp = new ArrayList<>();
        try {
            statement = c.prepareStatement("SELECT * FROM invoice_food WHERE invoice_id=?;");
            statement.setInt(1,idInvoice);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                food  = DatabaseFoodPostgre.getFoodById(rs.getInt("food_id"));
                foods_temp.add(food);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return foods_temp;
    }

    /**
     * This is getLastInvoiceId method, that is used to get last id of invoice in database
     * @return id, return invoice id
     */
    public static int getLastInvoiceId()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = c.prepareStatement("SELECT id FROM invoice;");
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
     * This is getInvoiceById method, that is used to get data of invoice in database
     * @params invoiceId, this is parameter to select invoice by id
     * @return invoice, return invoice object that is selected
     */
    public static Invoice getInvoiceById(int invoiceId)  throws InvoiceNotFoundException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int total_price = 0;
        Customer customer = null;
        String invoice_status = null;
        int delivery_fee = 0;
        Promo promo = null;
        Date date = null;
        String str_date = null;
        String payment_type = null;
        int id = 0;
        InvoiceStatus invoiceStatus = null;
        PaymentType paymentType = null;
        Invoice invoice = null;
        ArrayList<Food> foods = null;

        try{
            statement = c.prepareStatement("SELECT * FROM invoice WHERE id=?;");
            statement.setInt(1,invoiceId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foods = getInvoiceFood(id);
                total_price = rs.getInt("total_price");
                customer = DatabaseCustomerPostgre.getCustomerById(rs.getInt("customer_id"));
                delivery_fee = rs.getInt("delivery_fee");
                promo = DatabasePromoPostgre.getPromoByCode(rs.getString("promo_code"));
                str_date =  rs.getString("date");
                DateFormat formatterToDate = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
                date = formatterToDate.parse(str_date);
                invoice_status = rs.getString("invoice_status");
                if (invoice_status.equals("Ongoing")){
                    invoiceStatus = InvoiceStatus.ONGOING;
                }else if (invoice_status.equals("Finished")){
                    invoiceStatus = InvoiceStatus.FINISHED;
                }else if (invoice_status.equals("Cancelled")){
                    invoiceStatus = InvoiceStatus.CANCELLED;
                }
                payment_type = rs.getString("payment_type");
                if (payment_type.equals("Cash")){
                    paymentType = PaymentType.CASH;
                    invoice = new CashInvoice(id, foods, customer, delivery_fee, invoiceStatus, date, total_price);
                }else if (payment_type.equals("Cashless")){
                    paymentType = PaymentType.CASHLESS;
                    invoice = new CashlessInvoice(id, foods, customer, promo, invoiceStatus, date, total_price);
                }
            }
            statement.close();
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        if(invoice == null){
            throw new InvoiceNotFoundException(invoiceId);
        }
        return invoice;
    }

    /**
     * This is getInvoiceByCustomer method, that is used to get data of invoice in database
     * @params customerId, this is parameter to select invoice by customer id
     * @return invoices, return invoice object in array list that is selected
     */
    public static ArrayList<Invoice> getInvoiceByCustomer(int customerId)
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int total_price = 0;
        Customer customer = null;
        String invoice_status = null;
        int delivery_fee = 0;
        Promo promo = null;
        Date date = null;
        String str_date = null;
        String payment_type = null;
        int id = 0;
        InvoiceStatus invoiceStatus = null;
        PaymentType paymentType = null;
        Invoice invoice = null;
        ArrayList<Food> foods = null;
        ArrayList<Invoice> invoices = new ArrayList<>();
        try{
            statement = c.prepareStatement("SELECT * FROM invoice WHERE customer_id = ?;");
            statement.setInt(1,customerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foods = getInvoiceFood(id);
                total_price = rs.getInt("total_price");
                customer = DatabaseCustomerPostgre.getCustomerById(rs.getInt("customer_id"));
                delivery_fee = rs.getInt("delivery_fee");
                promo = DatabasePromoPostgre.getPromoByCode(rs.getString("promo_code"));
                str_date =  rs.getString("date");
                DateFormat formatterToDate = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
                date = formatterToDate.parse(str_date);
                invoice_status = rs.getString("invoice_status");
                if (invoice_status.equals("Ongoing")){
                    invoiceStatus = InvoiceStatus.ONGOING;
                }else if (invoice_status.equals("Finished")){
                    invoiceStatus = InvoiceStatus.FINISHED;
                }else if (invoice_status.equals("Cancelled")){
                    invoiceStatus = InvoiceStatus.CANCELLED;
                }
                payment_type = rs.getString("payment_type");
                if (payment_type.equals("Cash")){
                    paymentType = PaymentType.CASH;
                    invoice = new CashInvoice(id, foods, customer, delivery_fee, invoiceStatus, date, total_price);
                }else if (payment_type.equals("Cashless")){
                    paymentType = PaymentType.CASHLESS;
                    invoice = new CashlessInvoice(id, foods, customer, promo, invoiceStatus, date, total_price);
                }
                invoices.add(invoice);
            }
            statement.close();
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return invoices;
    }

    /**
     * This is removeInvoice method, that is used to remove data of invoice in database
     * @params invoiceId, this parameter to select invoice by invoice id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeInvoice(int invoiceId) throws InvoiceNotFoundException
    {
        boolean  success= true;
        Connection c = connection();
        PreparedStatement statement = null;
        String sql = null;
        try {
            sql = "DELETE FROM invoice WHERE id=?;";
            statement = c.prepareStatement(sql);
            statement.setInt(1, invoiceId);
            statement.execute();
            statement.close();
            c.close();
            removeInvoiceFood(invoiceId);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        if(sql == null){
            throw new InvoiceNotFoundException(invoiceId);
        }
        return success;
    }

    /**
     * This is removeInvoice method, that is used to remove data of food invoice in table invoice_food
     * @params invoiceId, this parameter to select food invoice by invoice id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeInvoiceFood(int invoiceId)
    {
        boolean  success= true;
        Connection c = connection();
        PreparedStatement statement = null;
        String sql = null;
        try {
            sql = "DELETE FROM invoice_food WHERE invoice_id=?;";
            statement = c.prepareStatement(sql);
            statement.setInt(1, invoiceId);
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
     * This is changeInvoiceStatus method, that is used to change invoice status in database
     * @params invoiceId, this is parameter to select invoice by invoice id
     * @params invoiceStatus, this is parameter to update value of status (new value)
     * @return true, if update is succeeded
     * @return false, if update is not succeeded
     */
    public static boolean changeInvoiceStatus(int invoiceId, InvoiceStatus invoiceStatus)
    {
        boolean  success= true;
        Connection c = connection();
        PreparedStatement statement = null;
        String sql = null;
        try {
            sql = "UPDATE invoice SET invoice_status = ? WHERE id = ?;";
            statement = c.prepareStatement(sql);
            statement.setString(1, invoiceStatus.toString());
            statement.setInt(2, invoiceId);
            statement.execute();
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return success;
    }
}
