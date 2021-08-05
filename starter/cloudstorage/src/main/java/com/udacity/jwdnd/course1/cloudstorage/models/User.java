package com.udacity.jwdnd.course1.cloudstorage.models;

public class User {
     int userid;
     String username;
     String salt;
     String password;
     String firstname;
     String lastname;

    public User( String username, String salt, String password, String firstname, String lastname) {
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
