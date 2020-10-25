package com.startrack.myapplication;

public class User {

    public String name, email, password, phone, stars;

    public User(){

    }


    public User(String name, String email, String password, String phone, String stars){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.stars = stars;

    }
}
