/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Ucskh {
    private int id;
    private int admin_id;
    private int user_id;
    private String description;

    public Ucskh() {
    }

    public Ucskh(int id, int admin_id, int user_id, String description) {
        this.id = id;
        this.admin_id = admin_id;
        this.user_id = user_id;
        this.description = description;
    }

    public Ucskh(int admin_id, int user_id, String description) {
        this.admin_id = admin_id;
        this.user_id = user_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ucskh{" + "id=" + id + ", admin_id=" + admin_id + ", user_id=" + user_id + ", description=" + description + '}';
    }
    
    
    
    
}
