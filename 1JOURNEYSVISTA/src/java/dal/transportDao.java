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
import model.transports;

/**
 *
 * @author Admin
 */
public class transportDao extends DBContext {

    public List<transports> getAll() {
        List<transports> list = new ArrayList<>();

        String sql = "select * from transports";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("name");

                list.add(new transports(id, fullname));

            }

        } catch (SQLException e) {

        }
        return list;
    }

    public transports getTransportByName(String name) {

        String sql = "select * from transports WHERE name=?";
        //thuc thi cau truy van 

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone_number");

                transports c = new transports(id, name);
                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

   
}
