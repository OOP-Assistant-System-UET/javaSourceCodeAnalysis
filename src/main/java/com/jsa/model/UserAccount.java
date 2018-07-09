package com.jsa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="account")
public class UserAccount implements Serializable {
    private String userName;
    private String password;
    private int userID;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, int userID) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userID")
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
