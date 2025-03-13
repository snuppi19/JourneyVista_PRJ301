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
public class orders {
    private int id;
    private int user_id;
    private String fullname;
    private String email;
    private String phone_number;
    private String address_start;
    private String note;
    private Date order_date;
    private String status;
    private float total_money;
    private int vehicles_id;
    private String address_end;
    private Date order_end;
    private boolean order_isActive;

    public orders(int user_id, String fullname, String email, String phone_number, String address_start, String note, Date order_date, String status, float total_money, int vehicles_id, String address_end, Date order_end, boolean order_isActive) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
        this.address_start = address_start;
        this.note = note;
        this.order_date = order_date;
        this.status = status;
        this.total_money = total_money;
        this.vehicles_id = vehicles_id;
        this.address_end = address_end;
        this.order_end = order_end;
        this.order_isActive = order_isActive;
    }

    public orders(int id, int user_id, String fullname, String email, String phone_number, String address_start, String note, Date order_date, String status, float total_money, int vehicles_id, String address_end, Date order_end, boolean order_isActive) {
        this.id = id;
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
        this.address_start = address_start;
        this.note = note;
        this.order_date = order_date;
        this.status = status;
        this.total_money = total_money;
        this.vehicles_id = vehicles_id;
        this.address_end = address_end;
        this.order_end = order_end;
        this.order_isActive = order_isActive;
    }

    public orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress_start() {
        return address_start;
    }

    public void setAddress_start(String address_start) {
        this.address_start = address_start;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotal_money() {
        return total_money;
    }

    public void setTotal_money(float total_money) {
        this.total_money = total_money;
    }

    public int getVehicles_id() {
        return vehicles_id;
    }

    public void setVehicles_id(int vehicles_id) {
        this.vehicles_id = vehicles_id;
    }

    public String getAddress_end() {
        return address_end;
    }

    public void setAddress_end(String address_end) {
        this.address_end = address_end;
    }

    public Date getOrder_end() {
        return order_end;
    }

    public void setOrder_end(Date order_end) {
        this.order_end = order_end;
    }

    public boolean isOrder_isActive() {
        return order_isActive;
    }

    public void setOrder_isActive(boolean order_isActive) {
        this.order_isActive = order_isActive;
    }
    
    
    
    
}
