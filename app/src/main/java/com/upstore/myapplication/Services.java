package com.upstore.myapplication;

import java.io.Serializable;

public class Services implements Serializable {

    private String services;

    public Services() {
    }

    private Services(String services) {

        this.services = services;

    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}