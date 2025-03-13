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
import model.Acskh;
import model.Ucskh;
import model.User;

/**
 *
 * @author Admin
 */
public class HelpDaouser extends DBContext {

    public List<Ucskh> getAll() {
        List<Ucskh> list = new ArrayList<>();

        String sql = "select * from Ucskh";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt(2);
                int admin_id = rs.getInt(3);

                String des = rs.getString(4);

                list.add(new Ucskh(id, admin_id, user_id, des));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public List<Ucskh> getAllbyuser_id(int User_id) {
        List<Ucskh> list = new ArrayList<>();

        String sql = "select * from Ucskh where user_id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, User_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt(2);
                int admin_id = rs.getInt(3);

                String des = rs.getString(4);

                list.add(new Ucskh(id, admin_id, user_id, des));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public Ucskh getUcskhByU_ID(String mess) {

        String sql = "select * from Ucskh WHERE description=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, mess);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt(2);
                int admin_id = rs.getInt(3);
                String des = rs.getString(4);

                Ucskh c = new Ucskh(id, admin_id, user_id, des);
                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public Ucskh getUcskhByID(int mess) {

        String sql = "select * from Ucskh WHERE id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, mess);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt(2);
                int admin_id = rs.getInt(3);
                String des = rs.getString(4);

                Ucskh c = new Ucskh(id, admin_id, user_id, des);
                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void insert(Ucskh c) {
        String sqlIS = "INSERT INTO [dbo].[Ucskh]\n"
                + "           ([user_id]\n"
                + "           ,[admin_id]\n"
                + "           ,[description])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlIS);
            pre.setInt(1, c.getUser_id());
            pre.setInt(2, c.getAdmin_id());
            pre.setString(3, c.getDescription());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Ucskh c) {
        String sql = "UPDATE [dbo].[Ucskh]\n"
                + "   SET [user_id] = ?\n"
                + "      ,[admin_id] = ?\n"
                + "      ,[description] = ?\n"
                + " WHERE id=?";//13
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, c.getUser_id());
            pre.setInt(2, c.getAdmin_id());
            pre.setString(3, c.getDescription());
            pre.setInt(4, c.getId());

            pre.executeUpdate();
        } catch (SQLException e) {
        }

    }
}
