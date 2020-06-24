package MuhammadIlhamAkbar.jfood.controller;

import MuhammadIlhamAkbar.jfood.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This PromoController Class is used to access JFood database at promo table
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

@CrossOrigin(origins = "*", allowedHeaders = "")
@RestController
public class PromoController
{
    /**
     * This is getAllPromo method, that is used to access and get data of all promo in database
     * @return promos, return promo object in array list
     */
    @RequestMapping(value = "/promo", method = RequestMethod.GET)
    public ArrayList<Promo> getAllPromo()
    {
        ArrayList<Promo> promos;
        try{
            promos = DatabasePromoPostgre.getPromoDatabase();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return promos;
    }

    /**
     * This is getPromoByCode method, that is used to get data of promo in database
     * @params code, this is parameter to select promo by code
     * @return promoCode, return promo object that is selected
     */
    @RequestMapping(value = "/promo/{code}", method = RequestMethod.GET)
    public Promo getPromoByCode(@PathVariable String code)
    {
        Promo promoCode = null;
        try{
            promoCode = DatabasePromoPostgre.getPromoByCode(code);
        } catch (PromoNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
        return promoCode;
    }

    /**
     * This is addPromo method, that is used to access and add promo in database
     * @params code, this is parameter of promo code
     * @params discount, this is parameter of promo discount
     * @params minPrice, this is parameter of minimal price
     * @params active, this is parameter of promo active or inactive
     * @return food, return food in array list that is selected
     */
    @RequestMapping(value = "/promo", method = RequestMethod.POST)
    public Promo addPromo(@RequestParam(value = "code") String code,
                          @RequestParam(value = "discount") int discount,
                          @RequestParam(value = "minPrice") int minPrice,
                          @RequestParam(value = "active") boolean active)
    {
        Promo promo = new Promo(DatabasePromoPostgre.getLastPromoId()+1, code, discount, minPrice, active);
        try {
            DatabasePromoPostgre.insertPromo(promo);
        }catch (PromoCodeAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return promo;

    }

}

