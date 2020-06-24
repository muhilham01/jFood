package MuhammadIlhamAkbar.jfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseSellerPostgre Class is used to precessing database of seller
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseSellerPostgre extends DatabaseConnectionPostgre {
    /**
     * This is insertSeller method, that is used to add seller in database
     * @params seller, this is parameter to insert seller object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean insertSeller(Seller seller)
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        //inserting value
        try {
            statement = c.prepareStatement("INSERT INTO seller (seller_name, seller_email, seller_phonenumber, seller_location) values (?,?,?,?) RETURNING id;");
            statement.setString(1,seller.getName());
            statement.setString(2,seller.getEmail());
            statement.setString(3,seller.getPhoneNumber());
            statement.setString(4,seller.getLocation().getCity());
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
     * This is getLastSellerId method, that is used to get last id of seller in database
     * @return id, return seller id
     */
    public static int getLastSellerId()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = c.prepareStatement("SELECT id FROM seller;");
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
     * This is getSellerById method, that is used to get data of seller in database
     * @params sellerId, this is parameter to select seller by id
     * @return seller, return seller object that is selected
     */
    public static Seller getSellerById(int sellerId) throws SellerNotFoundException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Seller seller = null;
        int id = 0;
        String sellerName = null;
        String sellerEmail = null;
        String phoneNumber = null;
        Location location = null;
        try {
            statement = c.prepareStatement("SELECT * FROM seller WHERE id=?;");
            statement.setInt(1,sellerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                sellerName = rs.getString("seller_name");
                sellerEmail = rs.getString("seller_email");
                phoneNumber = rs.getString("seller_phonenumber");
                location = DatabaseLocationPostgre.getLocationByCity(rs.getString("seller_location"));
                seller = new Seller (id, sellerName, sellerEmail, phoneNumber, location);
            }
            statement.close();
            c.close();
            //throw new SellerNotFoundException(id)
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        if(seller == null){
            throw new SellerNotFoundException(sellerId);
        }
        return seller;
    }

    /**
     * This is getSellerDatabase method, that is used to get data of all seller in database
     * @return sellers, return seller object in array list
     */
    public static ArrayList<Seller> getSellerDatabase()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Seller seller = null;
        ArrayList<Seller> sellers = new ArrayList<>();
        int id = 0;
        String sellerName = null;
        String sellerEmail = null;
        String phoneNumber = null;
        Location location = null;
        try {
            statement = c.prepareStatement("SELECT * FROM seller;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                sellerName = rs.getString("seller_name");
                sellerEmail = rs.getString("seller_email");
                phoneNumber = rs.getString("seller_phonenumber");
                location = DatabaseLocationPostgre.getLocationByCity(rs.getString("seller_location"));
                seller = new Seller (id, sellerName, sellerEmail, phoneNumber, location);
                sellers.add(seller);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return sellers;
    }

}
