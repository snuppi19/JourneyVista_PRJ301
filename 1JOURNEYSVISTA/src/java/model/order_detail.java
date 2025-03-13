/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class order_detail {
    private int id;
    private int order_id;
    private int vehicles_id;
    private float money;
    private int number_of_vehicles;
    private float total_money;
    private String type;
    private int number_people;

    public order_detail() {
    }

    public order_detail(int order_id, int vehicles_id, float money, int number_of_vehicles, float total_money, String type, int number_people) {
        this.order_id = order_id;
        this.vehicles_id = vehicles_id;
        this.money = money;
        this.number_of_vehicles = number_of_vehicles;
        this.total_money = total_money;
        this.type = type;
        this.number_people = number_people;
    }

    public order_detail(int id, int order_id, int vehicles_id, float money, int number_of_vehicles, float total_money, String type, int number_people) {
        this.id = id;
        this.order_id = order_id;
        this.vehicles_id = vehicles_id;
        this.money = money;
        this.number_of_vehicles = number_of_vehicles;
        this.total_money = total_money;
        this.type = type;
        this.number_people = number_people;
    }

    public int getNumber_people() {
        return number_people;
    }

    public void setNumber_people(int number_people) {
        this.number_people = number_people;
    }

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getVehicles_id() {
        return vehicles_id;
    }

    public void setVehicles_id(int vehicles_id) {
        this.vehicles_id = vehicles_id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getNumber_of_vehicles() {
        return number_of_vehicles;
    }

    public void setNumber_of_vehicles(int number_of_vehicles) {
        this.number_of_vehicles = number_of_vehicles;
    }

    public float getTotal_money() {
        return total_money;
    }

    public void setTotal_money(float total_money) {
        this.total_money = total_money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
