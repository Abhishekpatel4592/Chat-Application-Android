package com.example.chatapplication;

public class User {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String country;
    private String city;
    private String state;

    User(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String mFirstName) {
        this.firstName = mFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String mLastName) {
        this.lastName = mLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String mPhone) {
        this.phone = mPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mEmail) {
        this.email = mEmail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String mCountry) {
        this.country = mCountry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String mCity) {
        this.city = mCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String mState) {
        this.state = mState;
    }
}