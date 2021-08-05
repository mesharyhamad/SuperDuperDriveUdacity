package com.udacity.jwdnd.course1.cloudstorage.models;

public class CredentialForm {
    private  String credentialid;
    private  String url;
    private String userName;
    private String password;

    public void setCredentialid(String credentialid) {
        this.credentialid = credentialid;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredentialid() {
        return credentialid;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
}
