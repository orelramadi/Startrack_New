package com.upstore.myapplication;

public class Business {

    public String name, email, password, phone, description, description_short, cost_stars ,stars;

    public Business(){

    }


    public Business(String name, String email, String password, String phone,String description,String description_short, String cost_stars, String stars) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.description = description;
        this.description_short = description_short;
        this.cost_stars = cost_stars;
        this.stars = stars;
    }
}
