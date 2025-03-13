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
import model.Accounts;
import model.User;

/**
 *
 * @author Admin
 */
public class AccountDao extends DBContext {

    public List<Accounts> getAll() {
        List<Accounts> list = new ArrayList<>();

        String sql = "select * from accounts";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String acc_id = rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                int user_id = rs.getInt(4);
                int role_id = rs.getInt(5);
                list.add(new Accounts(acc_id, email, password, user_id, role_id));

            }

        } catch (SQLException e) {

        }
        return list;
    }

    public Accounts getACCbyEmail(String id) {

        String sql = "select * from accounts where email=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String acc_id = rs.getString(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                int user_id = rs.getInt(4);
                int role_id = rs.getInt(5);

                Accounts c = new Accounts(acc_id, email, password, user_id, role_id);
                return c;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insert(Accounts c) {
        String sqlIS = "INSERT INTO [dbo].[accounts]\n"
                + "           ([email]\n"
                + "           ,[password]\n"
                + "           ,[user_id]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlIS);
            pre.setString(1, c.getEmail());
            pre.setString(2, c.getPassword());
            pre.setInt(3, c.getUser_id());
            pre.setInt(4, c.getRole());

            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Accounts c) {
        String sql = "UPDATE [dbo].[accounts]\n"
                + "   SET [email] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[user_id] = ?\n"
                + "      ,[role_id] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, c.getEmail());
            pre.setString(2, c.getPassword());
            pre.setInt(3, c.getUser_id());
            pre.setInt(4, c.getRole());
            pre.setString(5, c.getAcc_id());

            pre.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void delete(String c) {
        String sqlDE = "delete from accounts where id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlDE);
            pre.setString(1, c);
            pre.executeUpdate();

        } catch (Exception e) {
        }
    }
}
