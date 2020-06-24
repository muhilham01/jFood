package MuhammadIlhamAkbar.jfood;
import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This Costumer Class is used to precessing costumer data
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class Customer
{
    // Instance variables in Costumer Class
    private int id; // Customer id
    private String name; // Customer name
    private String email; // Customer email
    private String password; // Customer password 
    private Calendar joinDate;  // Customer joinDate

    /**
     * This is constructor for object of class Customer 
     * <p>
     * Updates or changes id, name, email, password, and joindate when create objects
     * @param id - first parameter value of constructor, which becomes Costumer identifier
     * @param name - second parameter value of constructor, which becomes costumer name
     * @param email - third parameter value of constructor, which becomes costumer email 
     * @param password - fourth parameter value of constructor, which becomes costumer password
     * @param joinDate - fifth parameter value of constructor, shows costumer join date
     */
    public Customer(int id, String name, String email, String password, Calendar joinDate)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
        setPassword(password);
        setEmail(email);
    }

    /**
     * This is constructor for object of class Customer
     * <p>
     * Updates or changes id, name, email, password, year, month, and dayOfMonth when create objects
     * @param id - first parameter value of constructor, which becomes Costumer identifier
     * @param name - second parameter value of constructor, which becomes costumer name
     * @param email - third parameter value of constructor, which becomes costumer email
     * @param password - fourth parameter value of constructor, which becomes costumer password
     * @param year - fifth parameter value of constructor, shows costumer join year
     * @param month - sixth parameter value of constructor, shows costumer join month
     * @param dayOfMonth - seventh parameter value of constructor, shows costumer join day
     */
    public Customer(int id, String name, String email, String password, int year, int month, int dayOfMonth)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = new GregorianCalendar(year,month-1,dayOfMonth);
        setPassword(password);
        setEmail(email);
        
    }

    /**
     * This is constructor for object of class Customer
     * <p>
     * Updates or changes id, name, email, and password when create objects
     * @param id - first parameter value of constructor, which becomes Costumer identifier
     * @param name - second parameter value of constructor, which becomes costumer name
     * @param email - third parameter value of constructor, which becomes costumer email
     * @param password - fourth parameter value of constructor, which becomes costumer password
     */
    public Customer (int id, String name, String email, String password)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = Calendar.getInstance();
        setPassword(password);
        setEmail(email);
    }


    /**
     * This is getId method that is used to return id value
     * @return id, return value of customer id
     */
    public int getId()
    {
        return id;
    }
    
     /**
     * This is getName method is that used to return name value
     * @return name, return costumer's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * This is getEMail method that is used to return email value
     * @return email, return costumer's email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * This is getPassword method that is used to return password value
     * @return password, return value of costumer password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * This is getJoinDtae method that is used to return joinDate value
     * @return joinDate, return value of custoemr join date
     */
    public Calendar getJoinDate()
    {
        return joinDate;
    }

    
    /**
     * This is setId method that is used to set id value, that id is instance variable
     * @param id, set value of id in integer
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * This is setName method that is used to set name value, that name is instance variable
     * @param name, set value of name in string
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * This is setEmail method that is used to set email value, that email is instance variable
     * @param email, set value of email in string
     */
    public void setEmail(String email)
    {
        String pattern =  "^[a-zA-Z0-9_+&*-]+(?:\\."+  
                            "[a-zA-Z0-9_+&*-]+)*@" +  
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +  
                            "A-Z]{2,7}$";
        Pattern a = Pattern.compile(pattern);
        Matcher b = a.matcher(email);
        if (!b.find()) {
            this.email = "";
        } else {
            this.email = email;
        }
    }
    
    /**
     * This is setPassword method that is used to set password value, that password is instance variable
     * @param password, set value of password in string
     */
    public void setPassword(String password)
    {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern a = Pattern.compile(pattern);
        Matcher b = a.matcher(password);
        if(!b.find()){
            this.password = "";
        }else{
            this.password = password;
        }
    }
    
    /**
     * This is setJoinDate method that is used to set password value, that password is instance variable
     * @param joinDate, set customer join date in string
     */
    public void setJoinDate(Calendar joinDate)
    {
        this.joinDate = joinDate;
    }

    /**
     * This is setJoinDate method that is used to set password value, that password is instance variable
     * @param year, set year value in integer
     * @param month, set month value in integer
     * @param dayOfMonth, set day value in integer
     */
    public void setJoinDate (int year, int month, int dayOfMonth)
    {
        this.joinDate = new GregorianCalendar (year, month-1, dayOfMonth);
    }

    /**
     * This is toString method that is used to print information about customer.
     */
    public String toString()
    {
        String print;
        if(joinDate!=null)
        {
            
            Date date = joinDate.getTime();             
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String date1 = format1.format(date);  
            print  = "Customer:\n"+
                   "ID = "+id+"\n"+
                   "Nama = "+name+"\n"+
                   "Email = "+email+"\n"+
                   "Password = "+password+"\n"+
                   "Join Date = "+date1+"\n";
        }
        else
        {    
            print = "Customer:\n"+
                   "ID = "+id+"\n"+
                   "Nama = "+name+"\n"+
                   "Email = "+email+"\n"+
                   "Password = "+password+"\n";
        }
        //System.out.println(print);
        return print;
    }

}
