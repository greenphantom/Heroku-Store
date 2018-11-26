package com.store.model;

public class Customer {

    int id;
    String firstName;
    String lastName;
    String userName;
    String eMail;
    public Customer(){

    }

    public Customer(String fname, String lname, String username, String email ){
        firstName=fname;
        lastName=lname;
        userName=username;
        eMail=email;

    }

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public void seteMail(String eMail){
        this.eMail = eMail;
    }
    public String geteMail(){
        return eMail;
    }
}
