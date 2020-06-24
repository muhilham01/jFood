package MuhammadIlhamAkbar.jfood;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This Promo Class used to precessing data of promo
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class Promo
{
    // instance variables
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    /**
     * This is constructor for object of class Invoice
     * <p>
     * Updates or changes id, code, discount, minPrice, active
     * @param id - first parameter value of constructor, which becomes promo identifier
     * @param code - second parameter value of constructor, whic becomes code of promo
     * @param discount - third parameter value of constructor, which becomes discount value
     * @param minPrice - fourth parameter value of constructor, which becomes minimal value, so promo is activate
     * @param active - fifth parameter value of constructor, total price value at that invoice
     */
    public Promo(int id, String code, int discount, int minPrice, boolean active)
    {
        // initialise instance variables
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.minPrice = minPrice;
        this.active = active;
    }

    /**
     * This is getId method that is used to return value of id
     * @return id, return value of promo id
     */
    public int getId()
    {
        // put your code here
        return id;
    }

    /**
     * This is getCode method that is used to return value of promo code
     * @return code, return value of promo code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * This is getDiscount method that is used to return value of promo code
     * @return code, return value of promo code
     */
    public int getDiscount()
    {
        return discount;
    }

    /**
     * This is getMinPrice method that is used to return value of minimal price
     * @return minPrice, return value of minimal price
     */
    public int getMinPrice()
    {
        return minPrice;
    }

    /**
     * This is getActive method that is used to return value of minimal active
     * @return active, return value of active
     */
    public boolean getActive()
    {
        return active; 
    }

    /**
     * This is setId method that is used to set value of id, that id is instance variable
     * @param id , set id value in integer
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * This is setCode method that is used to set value of code , that code is instance variable
     * @param code , set code value in String
     */
    public void setCode (String code)
    {
        this.code = code;
    }

    /**
     * This is setDiscount method that is used to set value of discount, that discount is instance variable
     * @param discount , set id value in integer
     */
    public void setDiscount(int discount)
    {
        this.discount =discount;
    }

    /**
     * This is setMinPrice method that is used to set value of minimal price
     * @param minPrice , set id value in integer
     */
    public void setMinPrice(int minPrice)
    {
        this.minPrice = minPrice;
    }

    /**
     * This is setMinPrice method that is used to set promo is active or not
     * @param active , set active value in boolean
     */
    public void setActive (boolean active)
    {
        this.active = active;
    }

    /**
     * This is toString method that is used to print data about food object in String
     */
    public String toString()
    {
        return "PROMO\nId: "+id+"\nCode: "+code+"\nDiscount: "+discount+"\nMinPrice: "+minPrice+"\nActive Status: "+active;    
    }
    
    
}
