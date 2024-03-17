package com.example.vk.springboot.thymeleafdemo.model;

import java.util.List;

public class Student {

    private String firstName;

    private String lastName;

    private String country;

    private String favLang;

    private List<String> favSys;

    public List<String> getFavSys() {
        return favSys;
    }

    public void setFavSys(List<String> favSys) {
        this.favSys = favSys;
    }

    public String getFavLang() {
        return favLang;
    }

    public void setFavLang(String favLang) {
        this.favLang = favLang;
    }

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
