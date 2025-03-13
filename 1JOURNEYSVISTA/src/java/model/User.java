/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class User {

    private int id;
    private String fullname;
    private String phone_number;
    private boolean gender;
    private Date dob;
    private String address;
    private String password;
    private String email;
    private Date created_at;
    private Date updated_at;
    private int fb_account;
    private int gg_acount;
    private boolean is_active;

    
    public User() {
    }

    public User(int id, String fullname, String phone_number, boolean gender, Date dob, String address, String password, String email, Date created_at, Date updated_at, int fb_account, int gg_acount, boolean is_active) {
        this.id = id;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.fb_account = fb_account;
        this.gg_acount = gg_acount;
        this.is_active = is_active;
    }

    public User(String fullname, String phone_number, boolean gender, Date dob, String address, String password, String email, Date created_at, Date updated_at, int fb_account, int gg_acount, boolean is_active) {
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.fb_account = fb_account;
        this.gg_acount = gg_acount;
        this.is_active = is_active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

   

    
    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getFb_account() {
        return fb_account;
    }

    public void setFb_account(int fb_account) {
        this.fb_account = fb_account;
    }

    public int getGg_acount() {
        return gg_acount;
    }

    public void setGg_acount(int gg_acount) {
        this.gg_acount = gg_acount;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullname=" + fullname + ", phone_number=" + phone_number + ", gender=" + gender + ", dob=" + dob + ", address=" + address + ", password=" + password + ", email=" + email + ", created_at=" + created_at + ", updated_at=" + updated_at + ", fb_account=" + fb_account + ", gg_acount=" + gg_acount + ", is_active=" + is_active + '}';
    }

    
}
