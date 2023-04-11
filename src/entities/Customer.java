package entities;

public class Customer {
    Integer id;
    String name;
    String address;
    String city;
    String state; 
    String zip;


    //get and set for id
    public Integer getId(){
        return id;
    }
    public void setId(Integer passedId){
        this.id = passedId;
    }

    //get and set for name
    public String getName(){
        return name;
    }
    public void setName(String passedName){
        this.name = passedName;
    }

    //get and set for address
    public String getAddress(){
        return address;
    }
    public void setAddress(String passedAddress){
        this.address = passedAddress;
    }

    //get and set for city
    public String getCity(){
        return city;
    }
    public void setCity(String passedCity){
        this.city = passedCity;
    }

    //get and set for state
    public String getState(){
        return state;
    }
    public void setState(String passedState){
        this.state = passedState;
    }

    //get and set for zip
    public String getZip(){
        return zip;
    }
    public void setZip(String passedZip){
        this.zip = passedZip;
    }

    //override for toString function
    @Override
    public String toString() {
        return "customer [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
    }
}
