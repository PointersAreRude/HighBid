package Model;

public abstract class Person{
    private String _personFirstName;
    private String _personLastName;
    private String _personEmail;
    private String _personAddress;
    private String _phoneNumber;
    
    public Person(String firstName, String lastName, String Email, String Address, String phoneNum){
        this._personFirstName = firstName;
        this._personLastName = lastName;
        this._personEmail = Email;
        this._personAddress = Address;
        this._phoneNumber = phoneNum;
    }
    
    public void setFirstName(String name){
        this._personFirstName = name;
    }
    
    public String getFirstName(){
        return _personFirstName;
    }
    
    public void setLastName(String lName){
        this._personLastName = lName;
    }
    
    public String getLastName(){
        return _personLastName;
    }
    
    public void setEmail(String email){
        this._personEmail = email;
    }
    
    public String getEmail(){
        return _personEmail;
    }
    
    public void setAddress(String address){
        this._personAddress = address;
    }
    
    public String getAddress(){
        return _personAddress;
    }
    
    public void setPhone(String phone) {
    	this._phoneNumber = phone;
    }
    
    public String getPhone() {
    	return _phoneNumber;
    }
    
}
