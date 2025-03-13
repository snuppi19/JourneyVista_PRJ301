/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Acskh {

    private int id;
    private int U_id;
    private int user_id;
    private int admin_id;
    private String description;

    public Acskh() {
    }

    public Acskh(int id,int U_id, int user_id, int admin_id, String description) {
        this.id = id;
        this.U_id = U_id;
        this.user_id = user_id;
        this.admin_id = admin_id;
        this.description = description;
    }

    public Acskh(int U_id,int user_id, int admin_id, String description) {
        this.user_id = user_id;
        this.U_id = U_id;
        this.admin_id = admin_id;
        this.description = description;
    }

    public int getU_id() {
        return U_id;
    }

    public void setU_id(int U_id) {
        this.U_id = U_id;
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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Acskh{" + "id=" + id + ", U_id=" + U_id + ", user_id=" + user_id + ", admin_id=" + admin_id + ", description=" + description + '}';
    }
    
    
    
    
}
