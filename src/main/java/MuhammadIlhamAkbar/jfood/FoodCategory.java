package MuhammadIlhamAkbar.jfood;
/**
 * <h1>Enumeration class of FoodCategory -
 * this is list of food category
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public enum FoodCategory
{
    // below is list of category food
    BEVERAGES("Beverages"),
    COFFEE("Coffee"),
    WESTERN("Western"),
    SNACKS("Snacks"),
    RICE("Rice"),
    NOODLES("Noodles"),
    BAKERY("Bakery"),
    JAPANESE("Japanase");
    
    private String category;

    /**
     * This is FoodtCategory method that is used to set category value, that category is instance variable
     * @param category, set category value in string
     */
    private FoodCategory (String category)
    {
        this.category = category;
    }

    /**
     * This is toString method that is used to print status in string
     */
    public String toString()
    {
        return category;
    }

}
