package Model;

/**
 * @author Mark Ditianquin
 * @version5/23/2015
 * 
 * This is a Person abstract class
 */
public abstract class Person{
	/**
	 * First Name of the person
	 */
    private String _personFirstName;
    /**
     * Last Name of the person
     */
    private String _personLastName;
    /**
     * E-mail of the person
     */
    private String _personEmail;
    /**
     * Address of the person
     */
    private String _personAddress;
    /**
     * phone number of the person
     */
    private String _phoneNumber;
    
    /**
     * @param firstName the first name of person
     * @param lastName the last name of person
     * @param Email the e-mail of person
     * @param Address the address of the person
     * @param phoneNum the phone number of the person
     */
    public Person(String firstName, String lastName, String Email, String Address, String phoneNum){
        this._personFirstName = firstName;
        this._personLastName = lastName;
        this._personEmail = Email;
        this._personAddress = Address;
        this._phoneNumber = phoneNum;
    }
    
    /**
     * @param name first name of the person
     */
    public void setFirstName(String name){
        this._personFirstName = name;
    }
    
    /**
     * @return the first name of the person
     */
    public String getFirstName(){
        return _personFirstName;
    }
    /**
     * @param lName the last name of the person
     */
    public void setLastName(String lName){
        this._personLastName = lName;
    }
    /**
     * 
     * @return last name of the person
     */
    public String getLastName(){
        return _personLastName;
    }
    /**
     * 
     * @param email of the person
     */
    public void setEmail(String email){
        this._personEmail = email;
    }
    
    /**
     * 
     * @return e-mail of the person
     */
    public String getEmail(){
        return _personEmail;
    }
    
    /**
     * 
     * @param address of the person
     */
    public void setAddress(String address){
        this._personAddress = address;
    }
    
    /**
     * 
     * @return address of the person
     */
    public String getAddress(){
        return _personAddress;
    }
    
    /**
     * 
     * @param phone of the person
     */
    public void setPhone(String phone) {
    	this._phoneNumber = phone;
    }
    
    /**
     * 
     * @return phone of the person
     */
    public String getPhone() {
    	return _phoneNumber;
    }
    
}
