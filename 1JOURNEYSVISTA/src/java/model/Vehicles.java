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
public class Vehicles {

    private int id;
    private String name;
    private float price;
    private String thumbnail;
    private String description;
    private Date created_at;
    private Date updated_at;
    private int transports_id;

    public Vehicles() {
    }

    public Vehicles(String name, float price, String thumbnail, String description, Date created_at, Date updated_at, int transports_id) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.transports_id = transports_id;
    }

    public Vehicles(int id, String name, float price, String thumbnail, String description, Date created_at, Date updated_at, int transports_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.transports_id = transports_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getTransports_id() {
        return transports_id;
    }

    public void setTransports_id(int transports_id) {
        this.transports_id = transports_id;
    }
    

}
