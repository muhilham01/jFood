package MuhammadIlhamAkbar.jfood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabasePromoPostgre Class is used to precessing database of promo
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabasePromoPostgre extends DatabaseConnectionPostgre
{
    /**
     * This is insertPromo method, that is used to add promo in database
     * @params promo, this is parameter to insert promo object into database
     * @return true, if add is succeeded
     * @return false, if add is not succeeded
     */
    public static boolean insertPromo(Promo promo) throws PromoCodeAlreadyExistsException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        for (Promo i : getPromoDatabase())
        {
            if(i.getCode().equals(promo.getCode()))
            {
                throw new PromoCodeAlreadyExistsException(promo);
            }
        }
        //inserting value
        try {
            statement = c.prepareStatement("INSERT INTO promo (promo_code, promo_discount, promo_minPrice, promo_active) values (?,?,?,?) RETURNING id;");
            statement.setString(1,promo.getCode());
            statement.setInt(2,promo.getDiscount());
            statement.setInt(3,promo.getMinPrice());
            statement.setString(4, (Boolean.toString(promo.getActive())));
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
     * This is getPromoByCode method, that is used to get data of promo in database
     * @params code, this is parameter to select promo by code
     * @return promo, return promo object that is selected
     */
    public static Promo getPromoByCode(String code) throws PromoNotFoundException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Promo promo = null;
        int id = 0;
        String promoCode = null;
        int promoDiscount = 0;
        int promoMinPrice = 0;
        Boolean promoActive = null;
        try {
            statement = c.prepareStatement("SELECT * FROM promo WHERE promo_code=?;");
            statement.setString(1,code);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                promoCode = rs.getString("promo_code");
                promoDiscount = rs.getInt("promo_discount");
                promoMinPrice = rs.getInt("promo_minprice");
                promoActive = Boolean.parseBoolean(rs.getString("promo_active"));
                promo = new Promo (id, promoCode, promoDiscount, promoMinPrice, promoActive);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return promo;
    }

    /**
     * This is getPromoById method, that is used to get data of promo in database
     * @params promoId, this is parameter to select promo by id
     * @return promo, return promo object that is selected
     */
    public static Promo getPromoById(int promoId) throws PromoNotFoundException
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Promo promo = null;
        int id = 0;
        String promoCode = null;
        int promoDiscount = 0;
        int promoMinPrice = 0;
        Boolean promoActive = null;
        try {
            statement = c.prepareStatement("SELECT * FROM promo WHERE id=?;");
            statement.setInt(1,promoId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                promoCode = rs.getString("promo_code");
                promoDiscount = rs.getInt("promo_discount");
                promoMinPrice = rs.getInt("promo_minprice");
                promoActive = Boolean.parseBoolean(rs.getString("promo_active"));
                promo = new Promo (id, promoCode, promoDiscount, promoMinPrice, promoActive);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return promo;
    }

    /**
     * This is getLastPromoId method, that is used to get last id of promo in database
     * @return id, return promo id
     */
    public static int getLastPromoId()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = c.prepareStatement("SELECT id FROM promo;");
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
     * This is getPromoDatabase method, that is used to get data of all promo in database
     * @return promos, return promo object in array list
     */
    public static ArrayList<Promo> getPromoDatabase()
    {
        Connection c = connection();
        PreparedStatement statement = null;
        Promo promo = null;
        ArrayList<Promo> promos = new ArrayList<>();
        int id = 0;
        String promoCode = null;
        int promoDiscount = 0;
        int promoMinPrice = 0;
        Boolean promoActive = null;
        try {
            statement = c.prepareStatement("SELECT * FROM promo;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                promoCode = rs.getString("promo_code");
                promoDiscount = rs.getInt("promo_discount");
                promoMinPrice = rs.getInt("promo_minprice");
                promoActive = Boolean.parseBoolean(rs.getString("promo_active"));
                promo = new Promo (id, promoCode, promoDiscount, promoMinPrice, promoActive);
                promos.add(promo);
            }
            statement.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return promos;
    }

}
