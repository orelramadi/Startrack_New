package com.upstore.myapplication.Model;

public class UserModel {


    public String name, email, password, phone, stars;

    public UserModel(){

    }


    public UserModel(String name, String email, String password, String phone, String stars){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.stars = stars;
    }
}
