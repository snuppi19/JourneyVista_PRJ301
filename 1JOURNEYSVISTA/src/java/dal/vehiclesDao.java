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
import model.Vehicles;

/**
 *
 * @author Admin
 */
public class vehiclesDao extends DBContext {

    public List<Vehicles> getAll() {
        List<Vehicles> list = new ArrayList<>();

        String sql = "select * from vehicles";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("name");
                float price = rs.getFloat(3);
                String thumbnail = rs.getString(4);
                String description = rs.getString(5);
                Date created_at = rs.getDate(6);
                Date updated_at = rs.getDate(7);
                int transports_id = rs.getInt(8);

                list.add(new Vehicles(id, fullname, price, thumbnail, description, created_at, updated_at, transports_id));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public Vehicles getVehiclesByID(int ve_id) {

        String sql = "select * from Vehicles WHERE ID=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ve_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("name");
                float price = rs.getFloat(3);
                String thumbnail = rs.getString(4);
                String description = rs.getString(5);
                Date created_at = rs.getDate(6);
                Date updated_at = rs.getDate(7);
                int transports_id = rs.getInt(8);

                Vehicles c = new Vehicles(id, fullname, price, thumbnail, description, created_at, updated_at, transports_id);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }
    public Vehicles getVehiclesByName(String ve_id) {

        String sql = "select * from Vehicles WHERE name=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, ve_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("name");
                float price = rs.getFloat(3);
                String thumbnail = rs.getString(4);
                String description = rs.getString(5);
                Date created_at = rs.getDate(6);
                Date updated_at = rs.getDate(7);
                int transports_id = rs.getInt(8);

                Vehicles c = new Vehicles(id, fullname, price, thumbnail, description, created_at, updated_at, transports_id);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void insert(Vehicles c) {
        String sqlIS = "INSERT INTO [dbo].[vehicles]\n"
                + "           ([name]\n"
                + "           ,[price]\n"
                + "           ,[thumbnail]\n"
                + "           ,[description]\n"
                + "           ,[created_at]\n"
                + "           ,[updated_at]\n"
                + "           ,[transports_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlIS);
            pre.setString(1, c.getName());
            pre.setFloat(2, c.getPrice());
            pre.setString(3, c.getThumbnail());
            pre.setString(4, c.getDescription());

            pre.setDate(5, (Date) c.getCreated_at());
            pre.setDate(6, (Date) c.getUpdated_at());
            pre.setInt(7, c.getTransports_id());

            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Vehicles c) {
        String sql = "UPDATE [dbo].[vehicles]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] =?\n"
                + "      ,[thumbnail] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[created_at] = ?\n"
                + "      ,[updated_at] = ?\n"
                + "      ,[transports_id] = ?\n"
                + " WHERE id=?";//13
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, c.getName());
            pre.setFloat(2, c.getPrice());
            pre.setString(3, c.getThumbnail());
            pre.setString(4, c.getDescription());

            pre.setDate(5, (Date) c.getCreated_at());
            pre.setDate(6, (Date) c.getUpdated_at());
            pre.setInt(7, c.getTransports_id());
            pre.setInt(8, c.getId());
            pre.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void delete(int c) {
        String sqlDE = "delete from vehicles where id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlDE);
            pre.setInt(1, c);
            pre.executeUpdate();

        } catch (Exception e) {
        }
    }

}
