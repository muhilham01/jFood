package MuhammadIlhamAkbar.jfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseFoodPostgre Class is used to precessing database of food
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseFoodPostgre extends DatabaseConnectionPostgre {

    /**
     * This is insertFood method, that is used to add food in database
     * @params food, this is parameter to insert food object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static Boolean insertFood(Food food)
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 1;
        //inserting value
        try {
            statement = c.prepareStatement("INSERT INTO food (food_name, food_seller, food_price, food_category) values (?,?,?,?) RETURNING id;");
            statement.setString(1,food.getName());
            statement.setInt(2,food.getSeller().getId());
            statement.setInt(3,food.getPrice());
            statement.setString(4,food.getCategory().toString());
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
     * This is getLastFoodId method, that is used to get last id of food in database
     * @return id, return food id
     */
    public static int getLastFoodId()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = c.prepareStatement("SELECT id FROM food;");
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
     * This is getFoodById method, that is used to get data of food in database
     * @params foodId, this is parameter to select food by id
     * @return food, return food object that is selected
     */
    public static Food getFoodById(int foodId) throws FoodNotFoundException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Food food = null;
        int id = 0;
        String foodName = null;
        Seller seller = null; // who is seller
        int price = 0; // Food price
        FoodCategory foodCategory = null; // Food category
        try {
            statement = c.prepareStatement("SELECT * FROM food WHERE id=?;");
            statement.setInt(1,foodId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foodName = rs.getString("food_name");
                seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Beverages")){
                    foodCategory = FoodCategory.BEVERAGES;
                }else if (category.equals("Coffee")){
                    foodCategory = FoodCategory.COFFEE;
                } else if (category.equals("Western")){
                    foodCategory = FoodCategory.WESTERN;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.SNACKS;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.RICE;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.NOODLES;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.BAKERY;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.JAPANESE;
                }
                food = new Food(id, foodName, seller, price, foodCategory);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        if(food == null){
            throw new FoodNotFoundException(foodId);
        }
        return food;
    }

    /**
     * This is getFoodBySellerId method, that is used to get data of food in database
     * @params sellerId, this is parameter to select food by seller id
     * @return foods, return food in array list that is selected
     */
    public static ArrayList<Food> getFoodBySellerId(int sellerId){
        Connection c = connection();
        PreparedStatement statement = null;
        Food food = null;
        ArrayList<Food> foods = new ArrayList<>();
        int id = 0;
        String foodName = null;
        Seller seller = null; // who is seller
        int price = 0; // Food price
        FoodCategory foodCategory = null; // Food category
        try{
            statement = c.prepareStatement("SELECT * FROM food WHERE food_seller =?;");
            statement.setInt(1,sellerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foodName = rs.getString("food_name");
                seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Beverages")){
                    foodCategory = FoodCategory.BEVERAGES;
                }else if (category.equals("Coffee")){
                    foodCategory = FoodCategory.COFFEE;
                } else if (category.equals("Western")){
                    foodCategory = FoodCategory.WESTERN;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.SNACKS;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.RICE;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.NOODLES;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.BAKERY;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.JAPANESE;
                }
                food = new Food(id, foodName, seller, price, foodCategory);
                foods.add(food);
            }
            statement.close();
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return foods;
    }

    /**
     * This is getFoodByCategory method, that is used to get data of food in database
     * @params searchFoodCategory, this is parameter to select food by food category
     * @return foods, return food in array list that is selected
     */
    public static ArrayList<Food> getFoodByCategory (FoodCategory searchFoodCategory)
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Food food = null;
        ArrayList<Food> foods = new ArrayList<>();
        int id = 0;
        String foodName = null;
        Seller seller = null; // who is seller
        int price = 0; // Food price
        FoodCategory foodCategory = null; // Food category
        try{
            statement = c.prepareStatement("SELECT * FROM food WHERE food_category =?;");
            statement.setString(1,searchFoodCategory.toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foodName = rs.getString("food_name");
                seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Beverages")){
                    foodCategory = FoodCategory.BEVERAGES;
                }else if (category.equals("Coffee")){
                    foodCategory = FoodCategory.COFFEE;
                } else if (category.equals("Western")){
                    foodCategory = FoodCategory.WESTERN;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.SNACKS;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.RICE;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.NOODLES;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.BAKERY;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.JAPANESE;
                }
                food = new Food(id, foodName, seller, price, foodCategory);
                foods.add(food);
            }
            statement.close();
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return foods;
    }

    /**
     * This is getFoodDatabase method, that is used to get data of all food in database
     * @return foods, return food object in array list
     */
    public static ArrayList<Food> getFoodDatabase()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Food food = null;
        ArrayList<Food> foods = new ArrayList<>();
        int id = 0;
        String foodName = null;
        Seller seller = null; // who is seller
        int price = 0; // Food price
        FoodCategory foodCategory = null; // Food category
        try{
            statement = c.prepareStatement("SELECT * FROM food;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                foodName = rs.getString("food_name");
                seller = DatabaseSellerPostgre.getSellerById(rs.getInt("food_seller"));
                price = rs.getInt("food_price");
                String category = rs.getString("food_category");
                if (category.equals("Beverages")){
                    foodCategory = FoodCategory.BEVERAGES;
                }else if (category.equals("Coffee")){
                    foodCategory = FoodCategory.COFFEE;
                } else if (category.equals("Western")){
                    foodCategory = FoodCategory.WESTERN;
                } else if (category.equals("Snacks")){
                    foodCategory = FoodCategory.SNACKS;
                } else if (category.equals("Rice")){
                    foodCategory = FoodCategory.RICE;
                } else if (category.equals("Noodles")){
                    foodCategory = FoodCategory.NOODLES;
                } else if (category.equals("Bakery")){
                    foodCategory = FoodCategory.BAKERY;
                } else if (category.equals("Japanese")){
                    foodCategory = FoodCategory.JAPANESE;
                }
                food = new Food(id, foodName, seller, price, foodCategory);
                foods.add(food);
            }
            statement.close();
            c.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return foods;
    }

}
