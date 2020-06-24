package MuhammadIlhamAkbar.jfood.controller;

import MuhammadIlhamAkbar.jfood.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This FoodController Class is used to access JFood database at food table
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

@CrossOrigin(origins = "*", allowedHeaders = "")
@RestController
public class FoodController
{
    /**
     * This is getAllFood method, that is used to access and get data of all food in database
     * @return foods, return food object in array list
     */
    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ArrayList<Food> getAllFood()
    {
        ArrayList<Food> foods;
        try {
            foods = DatabaseFoodPostgre.getFoodDatabase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foods;
    }

    /**
     * This is getFoodById method, that is used to access and get data of food in database
     * @params id, this is parameter to select food by id
     * @return food, return food object that is selected
     */
    @RequestMapping(value = "/food/{id}", method = RequestMethod.GET)
    public Food getFoodById(@PathVariable int id)
    {
        Food food = null;
        try {
            food = DatabaseFoodPostgre.getFoodById(id);
        } catch (FoodNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return food;
    }

    /**
     * This is getFoodBySellerId method, that is used to access and get data of food in database
     * @params sellerId, this is parameter to select food by seller id
     * @return foods, return food in array list that is selected
     */
    @RequestMapping(value = "/food/seller/{sellerId}", method = RequestMethod.GET)
    public ArrayList<Food> getFoodBySeller(@PathVariable int sellerId)
    {
        ArrayList<Food> foods;
        try {
            foods = DatabaseFoodPostgre.getFoodBySellerId(sellerId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foods;
    }

    /**
     * This is getFoodByCategory method, that is used to access and get data of food in database
     * @params category, this is parameter to select food by category
     * @return food, return food in array list that is selected
     */
    @RequestMapping(value = "/food/category/{category}", method = RequestMethod.GET)
    public ArrayList<Food> getFoodByCategory(@PathVariable FoodCategory category)
    {
        ArrayList<Food> food = null;
        try
        {
            food = DatabaseFoodPostgre.getFoodByCategory(category);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return food;
    }

    /**
     * This is addFood method, that is used to access and add food in database
     * @params food, this is parameter to insert food object into database
     * @return food, return food in array list that is selected
     */
    @RequestMapping(value = "/food",method = RequestMethod.POST)
    public Food addFood(@RequestParam(value = "name") String name,
                        @RequestParam(value = "price")int price,
                        @RequestParam(value = "category")FoodCategory category,
                        @RequestParam(value = "sellerId")int sellerId )
    {
        Food food = null;

        try {
            food = new Food(DatabaseFoodPostgre.getLastFoodId() + 1, name, DatabaseSellerPostgre.getSellerById(sellerId), price, category);
        }catch(SellerNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            DatabaseFoodPostgre.insertFood(food);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return food;

    }
}
