package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {

    private  Integer credentialid;
    private  String url;
    private String username;
    private String key;
    private Integer userid;
    private String password;

    public Credential(Integer credentialid,String url, String username,  String password,String key,Integer userid) {
        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.password = password;
        this.userid = userid;
        this.key = key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getKey() {
        return key;
    }

    public Integer getUserid() {
        return userid;
    }

    public Integer getCredentialid() {
        return credentialid;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
