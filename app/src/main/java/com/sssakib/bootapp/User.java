package com.sssakib.bootapp;


import com.google.gson.annotations.SerializedName;

public class User {



    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("password")
    private String password;
    @SerializedName("id")
    private long id;
    @SerializedName("image")
    private String image;

    public User(String name, String mobile, String email, String address, String password, long id, String image) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.password = password;
        this.id = id;
        this.image = image;
    }
    public User(String name, String mobile, String email, String address, String password,String image) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.password = password;
        this.image=image;

    }




    public User( String mobile, String password ) {
        this.mobile = mobile;
        this.password = password;

    }


    public User(String email, String address, String password,String image) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.image= image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

