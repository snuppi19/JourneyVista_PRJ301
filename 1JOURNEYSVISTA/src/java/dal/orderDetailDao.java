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
import model.order_detail;
import model.orders;

/**
 *
 * @author Admin
 */
public class orderDetailDao extends DBContext {

    public List<order_detail> getAll() {
        List<order_detail> list = new ArrayList<>();

        String sql = "select * from orders_detail";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int orders_id = rs.getInt("orders_id");
                int vehicles_id = rs.getInt("vehicles_id");
                float price = rs.getFloat("price");
                int numberVehicles = rs.getInt("number_of_vehicles");
                float total_money = rs.getFloat("total_money");
                String type = rs.getString("type");
                int number_pp = rs.getInt("number_people");
                list.add(new order_detail(id, orders_id, vehicles_id, price, numberVehicles, total_money, type, number_pp));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public order_detail getOrdersByID(int ordersID) {

        String sql = "select * from orders_detail WHERE orders_id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ordersID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int orders_id = rs.getInt("orders_id");
                int vehicles_id = rs.getInt("vehicles_id");
                float price = rs.getFloat("price");
                int numberVehicles = rs.getInt("number_of_vehicles");
                float total_money = rs.getFloat("total_money");
                String type = rs.getString("type");
                int number_pp = rs.getInt("number_people");
                order_detail c = new order_detail(id, orders_id, vehicles_id, price, numberVehicles, total_money, type, number_pp);

                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(order_detail c) {
        String sqlIS = "INSERT INTO [dbo].[orders_detail]\n"
                + "           ([orders_id]\n"
                + "           ,[vehicles_id]\n"
                + "           ,[price]\n"
                + "           ,[number_of_vehicles]\n"
                + "           ,[total_money]\n"
                + "           ,[type]\n"
                + "           ,[number_people])\n"
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
            pre.setInt(1, c.getOrder_id());
            pre.setInt(2, c.getVehicles_id());
            pre.setFloat(3, c.getMoney());
            pre.setInt(4, c.getNumber_of_vehicles());
            pre.setFloat(5, c.getTotal_money());
            pre.setString(6, c.getType());
            pre.setInt(7, c.getNumber_people());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(order_detail c) {
        String sql = "UPDATE [dbo].[orders_detail]\n"
                + "   SET [orders_id] = ?\n"
                + "      ,[vehicles_id] = ?\n"
                + "      ,[price] =?\n"
                + "      ,[number_of_vehicles] = ?\n"
                + "      ,[total_money] = ?\n"
                + "      ,[type] =?\n"
                + "      ,[number_people] =?\n"
                + " WHERE id=?";//13
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, c.getOrder_id());
            pre.setInt(2, c.getVehicles_id());
            pre.setFloat(3, c.getMoney());
            pre.setInt(4, c.getNumber_of_vehicles());
            pre.setFloat(5, c.getTotal_money());
            pre.setString(6, c.getType());
            pre.setInt(7, c.getNumber_people());
            pre.setInt(8, c.getId());

            pre.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void delete(int c) {
        String sqlDE = "delete from orders_detail where id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlDE);
            pre.setInt(1, c);
            pre.executeUpdate();

        } catch (Exception e) {
        }
    }
}
