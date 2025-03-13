/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;

/**
 *
 * @author Admin
 */
public class Accounts {

    private String acc_id;
    private String email;
    private String password;
    private int user_id;
    private int role;

    public Accounts(String acc_id, String email, String password, int user_id, int role) {
        this.acc_id = acc_id;
        this.email = email;
        this.password = password;
        this.user_id = user_id;
        this.role = role;
    }

    public Accounts(String email, String password, int user_id, int role) {
        this.email = email;
        this.password = password;
        this.user_id = user_id;
        this.role = role;
    }

    public String getAcc_id() {
        return acc_id;
    }

    public int getRole() {
        return role;
    }

    public Accounts() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

}
