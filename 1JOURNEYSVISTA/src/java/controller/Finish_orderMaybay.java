/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDao;
import dal.orderDao;
import dal.orderDetailDao;
import dal.vehiclesDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Vehicles;
import model.order_detail;
import model.orders;

/**
 *
 * @author Admin
 */
public class Finish_orderMaybay extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Finish_orderMaybay</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Finish_orderMaybay at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            boolean check = true;
            java.sql.Date sqlDateStart = null;
            java.sql.Date sqlDateEnd = null;
            vehiclesDao vhDao = new vehiclesDao();

            String date_end = "";
            String typee = "";
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDao dao = new UserDao();
            User a = dao.getUserByID(userID);
            String address_start = request.getParameter("address_start");
            String address_end = request.getParameter("address_end");

            String date_start = request.getParameter("date_start");
            String date_endcheck = request.getParameter("date_end");
//            if (date_endcheck == "không Khứ Hồi") {
//                date_end = null;
//            } else {
//                date_end = date_endcheck;
//            }

            String note = request.getParameter("note");
            note = formatString(note);
            String plane = request.getParameter("plane");

            Vehicles vhc = vhDao.getVehiclesByName(plane);

            int songuoilon = Integer.parseInt(request.getParameter("nguoilon"));

            float total_money = Float.parseFloat(request.getParameter("total_money"));
            String status = "process";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi date_start từ String sang java.sql.Date
            java.util.Date utilDateStart = sdf.parse(date_start);
            sqlDateStart = new java.sql.Date(utilDateStart.getTime());

            // Kiểm tra xem người dùng có chọn không khứ hồi không
            if (date_endcheck.equals("không Khứ Hồi")) {
                // Gán giá trị mặc định nếu không có khứ hồi (ví dụ 1970-01-01)
                sqlDateEnd = java.sql.Date.valueOf("9999-12-31");
                typee = "One-way";
            } else {
                // Chuyển đổi date_end từ String sang java.sql.Date nếu có khứ hồi
                java.util.Date utilDateEnd = sdf.parse(date_endcheck);
                sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());
                typee = "Round-trip";
            }

            orders newOrd = new orders(userID, a.getFullname(),
                    a.getEmail(), a.getPhone_number(), address_start, note, sqlDateStart, status, total_money, vhc.getId(), address_end, sqlDateEnd, true);

            orderDao orDao = new orderDao();
//            orDao.insert(newOrd);
            System.out.println(userID);
            System.out.println(address_start);
            System.out.println(address_end);
            System.out.println(total_money);
            orders oldOrd = orDao.getOrdersByID_start_end_money(userID, address_start, address_end, total_money);

            List<orders> oList = new ArrayList<>();
            oList = orDao.getAll();
            if (oldOrd != null) {
                for (orders o : oList) {
                    if (o.getUser_id() == oldOrd.getUser_id() && o.getAddress_start().equalsIgnoreCase(oldOrd.getAddress_start()) && o.getAddress_end().equalsIgnoreCase(oldOrd.getAddress_end()) && o.getTotal_money() == oldOrd.getTotal_money()) {
                        request.setAttribute("same", "User has the same order in list, cancel order to create a new trip");
                        check = false;
                        request.getRequestDispatcher("maybay.jsp").forward(request, response);
                        break;
                    }
                }
            }
            if (check == true) {

                orDao.insert(newOrd);
                orders oldOrd2 = orDao.getOrdersByID_start_end_money(userID, address_start, address_end, total_money);
                int number_of_vehicles = 1;
                order_detail orderDetail = new order_detail(oldOrd2.getId(), vhc.getId(), total_money, number_of_vehicles,
                        total_money, typee, songuoilon);

                orderDetailDao detailDao = new orderDetailDao();
                detailDao.insert(orderDetail);
/////check cookies
                String cb = request.getParameter("cb");
                if (cb != null) {

                    List<Integer> ordLIST = new ArrayList<>();
                    ordLIST.add(newOrd.getId());

                    String userId = String.valueOf(newOrd.getUser_id());
                    StringBuilder orderIds = new StringBuilder();

                    Cookie[] cookies = request.getCookies();
                    boolean found = false;

                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (userId.equals(cookie.getName())) {

                                String existingIds = cookie.getValue();
                                orderIds.append(existingIds).append("_");
                                found = true;
                                break;
                            }
                        }
                    }

                        orderIds.append(oldOrd2.getId());
                    

                    Cookie orderCookie = new Cookie(userId, orderIds.toString());
                    orderCookie.setMaxAge(30 * 24 * 60 * 60); // 30 ngày
                    orderCookie.setPath("/");

                    response.addCookie(orderCookie);
                }

                response.sendRedirect("finishmaybay.jsp");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String formatString(String input) {

        String trimmedInput = input.trim().replaceAll("\\s+", " ");

        String[] words = trimmedInput.split(" ");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return result.toString().trim();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
