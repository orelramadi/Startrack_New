package com.upstore.myapplication;

public class Businesses {
    private int Thumbnail;
    private String Business_name;
    private String Business_description;
    private String Business_phone;
    private String Business_cost_star;
    private String Business_cost_paypal;


    public Businesses (int thumbnail, String name, String description, String phone, String cost_star, String paypal ){

        Thumbnail = thumbnail;
        Business_name = name;
        Business_description = description;
        Business_phone = phone;
        Business_cost_star = cost_star;
        Business_cost_paypal = paypal;

    }


    public String getBusiness_name(){
        return Business_name;
    }

    public String getBusiness_description(){
        return Business_description;
    }

    public String getBusiness_phone(){
        return Business_phone;
    }

    public String getBusiness_cost_star(){
        return Business_cost_star;
    }

    public String getBusiness_cost_paypal(){
        return Business_cost_paypal;
    }

    public int getThumbnail(){
        return Thumbnail;
    }

}
