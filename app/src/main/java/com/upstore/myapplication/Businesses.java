package com.upstore.myapplication;

import java.io.Serializable;

public class Businesses implements Serializable {

    private String name, email, phone, description, description_short, banner, service1, service2, service3, logo;
    private long cost_stars, cost_ils;

    public Businesses(){}

    private Businesses(String name, String email, String phone, String description, String description_short, String banner, String service1,String service2, String service3, String logo, long cost_stars, long cost_ils){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.description_short = description_short;
        this.banner = banner;
        this.service1 = service1;
        this.service2 = service2;
        this.service3 = service3;
        this.cost_stars = cost_stars;
        this.cost_ils = cost_ils;
        this.logo = logo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_short() {
        return description_short;
    }

    public void setDescription_short(String description_short) {
        this.description_short = description_short;
    }

    public long getCost_stars() {
        return cost_stars;
    }

    public void setCost_stars(long cost_stars) {
        this.cost_stars = cost_stars;
    }

    public long getCost_ils() {
        return cost_ils;
    }

    public void setCost_ils(long cost_ils) {
        this.cost_ils = cost_ils;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getService1() {
        return service1;
    }

    public void setService1(String service1) {
        this.service1 = service1;
    }

    public String getService2() {
        return service2;
    }

    public void setService2(String service2) {
        this.service2 = service2;
    }

    public String getService3() {
        return service3;
    }

    public void setService3(String service3) {
        this.service3 = service3;
    }
}

