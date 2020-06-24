package MuhammadIlhamAkbar.jfood.controller;
import java.util.ArrayList;

import MuhammadIlhamAkbar.jfood.*;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This InvoiceController Class is used to access JFood database at invoice and invoice_food table
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

@CrossOrigin(origins = "*", allowedHeaders = "")
@RestController
public class InvoiceController
{
    /**
     * This is getAllInvoice method, that is used to access and get data of all invoice in database
     * @return invoices, return invoice object in array list
     */
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public ArrayList<Invoice> getAllInvoice()
    {
        ArrayList<Invoice> invoices;
        try {
            invoices = DatabaseInvoicePostgre.getInvoiceDatabase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return invoices;
    }

    /**
     * This is getInvoiceById method, that is used to access and get data of food in database
     * @params id, this is parameter to select invoice by id
     * @return invoice, return invoice object that is selected
     */
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable int id)
    {
        Invoice invoice= null;
        try {
            invoice = DatabaseInvoicePostgre.getInvoiceById(id);
        } catch (InvoiceNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return invoice;
    }

    /**
     * This is getInvoiceByCustomer method, that is used to access and get data of invoice in database
     * @params customerId, this is parameter to select invoice by customer id
     * @return invoice, return invoice object that is selected
     */
    @RequestMapping(value = "invoice/customer/{customerId}", method = RequestMethod.GET)
    public ArrayList<Invoice> getInvoiceByCustomer(@PathVariable int customerId)
    {
        ArrayList<Invoice> invoice= DatabaseInvoicePostgre.getInvoiceByCustomer(customerId);
        return invoice;
    }

    /**
     * This is changeInvoiceStatus method, that is used to change invoice status in database
     * @params id, this is parameter to select invoice by invoice id
     * @params status, this is parameter to update value of status (new value)
     * @return invoice, return invoice object that is selected
     */
    @RequestMapping(value = "invoice/invoiceStatus/{id}", method = RequestMethod.PUT)
    public Invoice changeInvoiceStatus(@PathVariable int id,
                                       @RequestParam(value = "status") InvoiceStatus status) throws InvoiceNotFoundException
    {
        System.out.println(id);
        Invoice invoice = null;
        DatabaseInvoicePostgre.changeInvoiceStatus(id,status);
        try{
            invoice = DatabaseInvoicePostgre.getInvoiceById(id);
            //return invoice;
        }catch (InvoiceNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
        return invoice;

    }

    /**
     * This is removeInvoice method, that is used to access and remove data of invoice in table invoice
     * @params id, this parameter to select food invoice by invoice id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    @RequestMapping(value = "invoice/{id}", method = RequestMethod.DELETE)
    public Boolean removeInvoice(@PathVariable int id)
    {

        try{
            if(DatabaseInvoicePostgre.removeInvoice(id))
            {
                return true;
            }
        } catch (InvoiceNotFoundException e){
            System.out.println(e.getMessage());
            //return null;
        }
        return false;
    }

    /**
     * This is addCashInvoice method, that is used to add cash invoice in database
     * @params foodIdList, this is parameter of food id in list
     * @params customerId, this is parameter of customer id
     * @params deliveryFee, this is parameter of deliveryFee
     * @return invoice, return invoice object that is selected
     */
    @RequestMapping(value = "invoice/createCashInvoice", method = RequestMethod.POST)
    public Invoice addCashInvoice( @RequestParam(value="foodIdList") ArrayList<Integer> foodIdList,
                                   @RequestParam(value="customerId") int customerId,
                                   @RequestParam(value="deliveryFee", defaultValue = "0") int deliveryFee)
    {
        ArrayList<Food> foodList = new ArrayList<>();
        for (int food : foodIdList) {
            try {
                foodList.add(DatabaseFoodPostgre.getFoodById(food));
            } catch (FoodNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            CashInvoice invoice = new CashInvoice(DatabaseInvoicePostgre.getLastInvoiceId()+1, foodList, DatabaseCustomerPostgre.getCustomerById(customerId), deliveryFee);
            invoice.setTotalPrice();
            DatabaseInvoicePostgre.insertCashInvoice(invoice);
            return invoice;
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (OngoingInvoiceAlreadyExistsException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * This is addCashlessInvoice method, that is used to add cash invoice in database
     * @params foodIdList, this is parameter of food id in list
     * @params customerId, this is parameter of customer id
     * @params promoCode, this is parameter of promo code
     * @return invoice, return invoice object that is selected
     */
    @RequestMapping(value = "invoice/createCashlessInvoice", method = RequestMethod.POST)
    public Invoice addCashlessInvoice( @RequestParam(value="foodIdList") ArrayList<Integer> foodIdList,
                                       @RequestParam(value="customerId") int customerId,
                                       @RequestParam(value="promoCode", defaultValue = " ") String promoCode)
    {
        ArrayList<Food> foodList = new ArrayList<>();
        for (int food : foodIdList) {
            try {
                foodList.add(DatabaseFoodPostgre.getFoodById(food));
            } catch (FoodNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            CashlessInvoice invoice = new CashlessInvoice(DatabaseInvoicePostgre.getLastInvoiceId() + 1, foodList, DatabaseCustomerPostgre.getCustomerById(customerId),  DatabasePromoPostgre.getPromoByCode(promoCode));
            invoice.setTotalPrice();
            DatabaseInvoicePostgre.insertCashlessInvoice(invoice);
            return invoice;
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (OngoingInvoiceAlreadyExistsException e){
            System.out.println(e.getMessage());
            return null;
        }
        catch (PromoNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }

    }


}