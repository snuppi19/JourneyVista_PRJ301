/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDao extends DBContext {

    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        String sql = "select * from users";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone_number");
                String address = rs.getString(4);
                String password = rs.getString(5);
                Date created_at = rs.getDate(6);
                Date updated_at = rs.getDate(7);
                boolean is_active = rs.getBoolean(8);
                Date dob = rs.getDate(9);
                int fb_acc = rs.getInt(10);
                int gg_acc = rs.getInt(11);
                boolean gender = rs.getBoolean("gender");
                String email = rs.getString(14);

                list.add(new User(id, fullname, phone, gender, dob, address, password, email, created_at, updated_at, fb_acc, gg_acc, is_active));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public User getUserByID(int user_id) {

        String sql = "select * from users WHERE ID=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, user_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone_number");
                String address = rs.getString(4);
                String password = rs.getString(5);
                Date created_at = rs.getDate(6);
                Date updated_at = rs.getDate(7);
                boolean is_active = rs.getBoolean(8);
                Date dob = rs.getDate(9);
                int fb_acc = rs.getInt(10);
                int gg_acc = rs.getInt(11);
                boolean gender = rs.getBoolean(13);
                String email = rs.getString(14);

                User c = new User(id, fullname, phone, gender, dob, address, password, email, created_at, updated_at, fb_acc, gg_acc, is_active);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void insert(User c) {
        String sqlIS = "INSERT INTO [dbo].[users]\n"
                + "           ([fullname]\n"
                + "           ,[phone_number]\n"
                + "           ,[address]\n"
                + "           ,[password]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[is_active]\n"
                + "           ,[dob]\n"
                + "           ,[facebook_acount_id]\n"
                + "           ,[google_acount_id]\n"
                + "           ,[role_id]\n"
                + "           ,[gender]\n"
                + "           ,[email])\n"
                + "     VALUES\n"
                + "           (?\n"//name
                + "           ,?\n"//phone
                + "           ,?\n"//add
                + "           ,?\n"//pass
                + "           ,?\n"//cre
                + "           ,?\n"//up
                + "           ,?\n"//is
                + "           ,?\n"//dob
                + "           ,0\n"//fb
                + "           ,?\n"//gg
                + "           ,2\n"//role
                + "           ,?\n"//gender
                + "           ,?)";//email

        try {
            PreparedStatement pre = connection.prepareStatement(sqlIS);
            pre.setString(1, c.getFullname());
            pre.setString(2, c.getPhone_number());
            pre.setString(3, c.getAddress());
            pre.setString(4, c.getPassword());

            pre.setDate(5, (Date) c.getCreated_at());
            pre.setDate(6, (Date) c.getUpdated_at());
            pre.setBoolean(7, c.isIs_active());
            pre.setDate(8, (Date) c.getDob());
            pre.setInt(9, c.getGg_acount());
            pre.setBoolean(10, c.isGender());
            pre.setString(11, c.getEmail());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(User c) {
        String sql = "UPDATE [dbo].[users]\n"
                + "   SET [fullname] = ?\n"//1
                + "      ,[phone_number] = ?\n"//2
                + "      ,[address] = ?\n"//3
                + "      ,[password] = ?\n"//4
                + "      ,[created_at] = ?\n"//5
                + "      ,[updated_at] = ?\n"//6
                + "      ,[is_active] = ?\n"//7
                + "      ,[dob] = ?\n"//8
                + "      ,[facebook_acount_id] = ?\n"//9
                + "      ,[google_acount_id] = ?\n"//10
                + "      ,[gender] = ?\n"//11
                + "      ,[email] = ?\n"//12
                + " WHERE id=?";//13
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, c.getFullname());
            pre.setString(2, c.getPhone_number());
            pre.setString(3, c.getAddress());
            pre.setString(4, c.getPassword());

            pre.setDate(5, (Date) c.getCreated_at());
            pre.setDate(6, (Date) c.getUpdated_at());
            if (c.isIs_active() == true) {
                pre.setInt(7, 1);
            } else {
                pre.setInt(7, 0);
            }
            pre.setDate(8, (Date) c.getDob());
            pre.setInt(9, c.getFb_account());
            pre.setInt(10, c.getGg_acount());
            pre.setBoolean(11, c.isGender());

            pre.setString(12, c.getEmail());
            pre.setInt(13, c.getId());

            pre.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    
     public void delete(int c) {
        String sqlDE = "delete from users where id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlDE);
            pre.setInt(1, c);
            pre.executeUpdate();

        } catch (Exception e) {
        }
    }
}
