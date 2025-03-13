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
import model.User;
import model.Vehicles;

/**
 *
 * @author Admin
 */
public class HelpDao extends DBContext {

    public List<Acskh> getAll() {
        List<Acskh> list = new ArrayList<>();

        String sql = "select * from Acskh";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int u_id = rs.getInt("U_id");

                int admin_id = rs.getInt(3);
                int user_id = rs.getInt(4);
                String des = rs.getString(5);

                list.add(new Acskh(id, u_id, user_id, admin_id, des));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public Acskh getAcskhByuser_ID(int ve_id) {

        String sql = "select * from Acskh WHERE U_id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ve_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int u_id = rs.getInt("U_id");
                int admin_id = rs.getInt(3);
                int user_id = rs.getInt(4);
                String des = rs.getString(5);

                Acskh c = new Acskh(id, u_id, user_id, admin_id, des);
                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void insert(Acskh c) {
        String sqlIS = "INSERT INTO [dbo].[Acskh]\n"
                + "           ([U_id]\n"
                + "           ,[admin_id]\n"
                + "           ,[user_id]\n"
                + "           ,[description])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlIS);
            pre.setInt(1, c.getU_id());
            pre.setInt(2, c.getAdmin_id());
            pre.setInt(3, c.getUser_id());
            pre.setString(4, c.getDescription());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Acskh c) {
        String sql = "UPDATE [dbo].[Acskh]\n"
                + "   SET [U_id] = ?\n"
                + "      ,[admin_id] = ?\n"
                + "      ,[user_id] =?\n"
                + "      ,[description] = ?\n"
                + " WHERE id=?";//13
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, c.getU_id());
            pre.setInt(2, c.getAdmin_id());
            pre.setInt(3, c.getUser_id());

            pre.setString(4, c.getDescription());
            pre.setInt(5, c.getId());
            pre.executeUpdate();
        } catch (SQLException e) {
        }
    }

}
