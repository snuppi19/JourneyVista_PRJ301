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
import model.Vehicles;
import model.orders;

/**
 *
 * @author Admin
 */
public class orderDao extends DBContext {

    public List<orders> getAll() {
        List<orders> list = new ArrayList<>();

        String sql = "select * from orders";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address_start = rs.getString("address");
                String note = rs.getString("note");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");
                float total_money = rs.getFloat("total_money");
                int vehicles_id = rs.getInt("vehicles_id");
                String address_end = rs.getString("address_end");
                Date order_end = rs.getDate("order_end");
                boolean order_isActive = rs.getBoolean("active");

                list.add(new orders(id, user_id, fullname, email, phone_number, address_start, note, order_date, status, total_money, vehicles_id, address_end, order_end, order_isActive));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public List<orders> getAllbyid(String userID) {
        List<orders> list = new ArrayList<>();

        String sql = "select * from orders WHERE user_id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, userID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address_start = rs.getString("address");
                String note = rs.getString("note");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");
                float total_money = rs.getFloat("total_money");
                int vehicles_id = rs.getInt("vehicles_id");
                String address_end = rs.getString("address_end");
                Date order_end = rs.getDate("order_end");
                boolean order_isActive = rs.getBoolean("active");

                list.add(new orders(id, user_id, fullname, email, phone_number, address_start, note, order_date, status, total_money, vehicles_id, address_end, order_end, order_isActive));
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public orders getOrdersByuserID(int userID) {

        String sql = "select * from orders WHERE user_id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, userID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address_start = rs.getString("address");
                String note = rs.getString("note");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");
                float total_money = rs.getFloat("total_money");
                int vehicles_id = rs.getInt("vehicles_id");
                String address_end = rs.getString("address_end");
                Date order_end = rs.getDate("order_end");
                boolean order_isActive = rs.getBoolean("active");

                orders c = new orders(id, user_id, fullname, email, phone_number, address_start, note, order_date, status, total_money, vehicles_id, address_end, order_end, order_isActive);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public orders getAllbyStrid(String userID) {

        String sql = "select * from orders WHERE id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, userID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address_start = rs.getString("address");
                String note = rs.getString("note");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");
                float total_money = rs.getFloat("total_money");
                int vehicles_id = rs.getInt("vehicles_id");
                String address_end = rs.getString("address_end");
                Date order_end = rs.getDate("order_end");
                boolean order_isActive = rs.getBoolean("active");

                orders c = new orders(id, user_id, fullname, email, phone_number, address_start, note, order_date, status, total_money, vehicles_id, address_end, order_end, order_isActive);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public orders getOrdersByID(int userID) {

        String sql = "select * from orders WHERE id=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, userID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address_start = rs.getString("address");
                String note = rs.getString("note");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");
                float total_money = rs.getFloat("total_money");
                int vehicles_id = rs.getInt("vehicles_id");
                String address_end = rs.getString("address_end");
                Date order_end = rs.getDate("order_end");
                boolean order_isActive = rs.getBoolean("active");

                orders c = new orders(id, user_id, fullname, email, phone_number, address_start, note, order_date, status, total_money, vehicles_id, address_end, order_end, order_isActive);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public orders getOrdersByID_start_end_money(int userID, String start, String end, float money) {

        String sql = "select * from orders WHERE user_id= ? and address= ? and address_end= ? and total_money= ?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, userID);
            pre.setString(2, start);
            pre.setString(3, end);
            pre.setFloat(4, money);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address_start = rs.getString("address");
                String note = rs.getString("note");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");
                float total_money = rs.getFloat("total_money");
                int vehicles_id = rs.getInt("vehicles_id");
                String address_end = rs.getString("address_end");
                Date order_end = rs.getDate("order_end");
                boolean order_isActive = rs.getBoolean("active");

                orders c = new orders(id, user_id, fullname, email, phone_number, address_start, note, order_date, status, total_money, vehicles_id, address_end, order_end, order_isActive);

                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void insert(orders c) {
        String sqlIS = "INSERT INTO [dbo].[orders]\n"
                + "           ([user_id]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[phone_number]\n"
                + "           ,[address]\n"
                + "           ,[note]\n"
                + "           ,[order_date]\n"
                + "           ,[status]\n"
                + "           ,[total_money]\n"
                + "           ,[vehicles_id]\n"
                + "           ,[address_end]\n"
                + "           ,[order_end]\n"
                + "           ,[active])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlIS);
            pre.setInt(1, c.getUser_id());
            pre.setString(2, c.getFullname());
            pre.setString(3, c.getEmail());
            pre.setString(4, c.getPhone_number());
            pre.setString(5, c.getAddress_start());
            pre.setString(6, c.getNote());
            pre.setDate(7, (Date) c.getOrder_date());
            pre.setString(8, c.getStatus());
            pre.setFloat(9, c.getTotal_money());
            pre.setInt(10, c.getVehicles_id());
            pre.setString(11, c.getAddress_end());
            pre.setDate(12, (Date) c.getOrder_end());
            pre.setBoolean(13, c.isOrder_isActive());

            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(orders c) {
        String sql = "UPDATE [dbo].[orders]\n"
                + "   SET [user_id] = ?\n"
                + "      ,[fullname] = ?\n"
                + "      ,[email] =?\n"
                + "      ,[phone_number] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[note] = ?\n"
                + "      ,[order_date] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[total_money] =?\n"
                + "      ,[vehicles_id] =?\n"
                + "      ,[address_end] = ?\n"
                + "      ,[order_end] = ?\n"
                + "      ,[active] =?\n"
                + " WHERE id=?";//13
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, c.getUser_id());
            pre.setString(2, c.getFullname());
            pre.setString(3, c.getEmail());
            pre.setString(4, c.getPhone_number());
            pre.setString(5, c.getAddress_start());
            pre.setString(6, c.getNote());
            pre.setDate(7, (Date) c.getOrder_date());
            pre.setString(8, c.getStatus());
            pre.setFloat(9, c.getTotal_money());
            pre.setInt(10, c.getVehicles_id());
            pre.setString(11, c.getAddress_end());
            pre.setDate(12, (Date) c.getOrder_end());
            pre.setBoolean(13, c.isOrder_isActive());
            pre.setInt(14, c.getId());

            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int c) {
        String sqlDE = "delete from orders where id=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sqlDE);
            pre.setInt(1, c);
            pre.executeUpdate();

        } catch (Exception e) {
        }
    }

}
