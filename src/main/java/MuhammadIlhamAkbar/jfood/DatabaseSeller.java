package MuhammadIlhamAkbar.jfood;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseSeller Class is used to precessing database of seller
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class DatabaseSeller
{
    private static ArrayList<Seller> SELLER_DATABASE = new ArrayList<Seller>();
    private static int lastId = 0;

    /**
     * Constructor for objects of class DatabaseSeller
     */
    public DatabaseSeller()
    {
       
    }

    /**
     * This is getSellerDatabase method, that is used to get data of all seller in database
     * @return sellers, return seller object in array list
     */
    public static ArrayList<Seller> getSellerDatabase(){
        return SELLER_DATABASE;
    }

    /**
     * This is getId method, that is used to get last id of seller in database
     * @return lastId, return seller id
     */
    public static int getLastId()
    {
        return lastId;
    }

    /**
     * This is getSellerById method, that is used to get data of seller in database
     * @params id, this is parameter to select seller by id
     * @return seller, return seller object that is selected
     */
    public static Seller getSellerById(int id) throws SellerNotFoundException
    {
        for (Seller sellers : SELLER_DATABASE)
        {
            if (sellers.getId() == id)
            {
                return sellers;
            }
        }
        throw new SellerNotFoundException(id);
    }

    /**
     * This is insertSeller method, that is used to add seller in database
     * @params seller, this is parameter to insert seller object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean addSeller(Seller seller)
    {
        SELLER_DATABASE.add(seller);
        lastId = seller.getId();
        return true;
    }

    /**
     * This is removeSeller method, that is used to remove data of seller in database
     * @params id, this is parameter to select seller by id
     * @return true, if remove is succeeded
     * @return false, if remove is not succeeded
     */
    public static boolean removeSeller(int id) throws SellerNotFoundException
    {
        for (Seller sellers : SELLER_DATABASE)
        {
            if (sellers.getId() == id)
            {
                SELLER_DATABASE.remove(sellers);
                return true;
            }
        }
        throw new SellerNotFoundException(id);
    }

}
