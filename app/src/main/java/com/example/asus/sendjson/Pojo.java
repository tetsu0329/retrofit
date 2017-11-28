package com.example.asus.sendjson;

/**
 * Created by Asus on 11/27/2017.
 */

public class Pojo {
    String name;
    String email;
    String mobile;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Pojo(String name, String email, String mobile) {

        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}
