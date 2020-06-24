package MuhammadIlhamAkbar.jfood;

import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseFood Class is used to precessing database of food
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseFood
{
    // Instance variables in Seller Class
   // private static String[] listFood;
    private static ArrayList<Food> FOOD_DATABASE = new ArrayList<>();
    private static int lastId = 0;

    /**
     * This is constructor for object of class DatabaseFood
     */
    public DatabaseFood()
    {
        // initialise instance variables
    }

    /**
     * This is getFoodDatabase method, that is used to get data of all food in database
     * @return FOOD_DATABASE, return food object in array list
     */
    public static ArrayList<Food> getFoodDatabase()
    {
        return FOOD_DATABASE;
    }

    /**
     * This is getLastId method, that is used to get last id of food in database
     * @return lastid, return food id
     */
    public static int getLastId()
    {
        return lastId;
    }

    /**
     * This is getFoodById method, that is used to get data of food in database
     * @params id, this is parameter to select food by id
     * @return food, return food object that is selected
     */
    public static Food getFoodById(int id) throws FoodNotFoundException
    {
        for (Food food : FOOD_DATABASE)
        {
            if (food.getId() == id)
            {
                return food;
            }
        }
        throw new FoodNotFoundException(id);
    }

    /**
     * This is getFoodBySellerId method, that is used to get data of food in database
     * @params sellerId, this is parameter to select food by seller id
     * @return i, return food in array list that is selected
     */
    public static ArrayList<Food> getFoodBySeller (int sellerId) //throws SellerNotFoundException
    {
        ArrayList<Food> i = new ArrayList<>();
        for (Food food: FOOD_DATABASE) {
            if (food.getSeller().getId() == sellerId) {
                i.add(food);
            }
        }
        return i;
    }

    /**
     * This is getFoodByCategiry method, that is used to get data of food in database
     * @params category, this is parameter to select food by food category
     * @return i, return food in array list that is selected
     */
    public static ArrayList<Food> getFoodByCategory(FoodCategory category)
    {
        ArrayList<Food> i = new ArrayList<>();
        for (Food food: FOOD_DATABASE) {
            if (food.getCategory() == category) {
                i.add(food);
            }
        }
        return i;
    }

    /**
     * This is addFood method, that is used to add food in database
     * @params food, this is parameter to insert food object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean addFood(Food food)
    {
        // put your code here
        FOOD_DATABASE.add(food);
        lastId = food.getId();
        return true;
    }

    /**
     * This is removeFood method, that is used to remove data of food in database
     * @params id, this is parameter to select food by id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeFood(int id) throws FoodNotFoundException
    {
        for(Food food : FOOD_DATABASE)
        {
            if(food.getId() == id)
            {
                FOOD_DATABASE.remove(food);
                return true;
            }
        }
        throw new FoodNotFoundException(id);
        //return false;
    }

    
}
