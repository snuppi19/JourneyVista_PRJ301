/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Admin
 */
public class Ordermaybay extends HttpServlet {

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
            out.println("<title>Servlet Order</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Order at " + request.getContextPath() + "</h1>");
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
        try {

            boolean check = true;
            String date_end = "";
            HttpSession session = request.getSession();

            int userID = Integer.parseInt(request.getParameter("userid"));

            UserDao dao = new UserDao();
            User a = dao.getUserByID(userID);

            String address_start = request.getParameter("address_start");
            String address_end = request.getParameter("address_end");

            if (address_start.equals(address_end)) {
                check = false;
                request.setAttribute("errP", "Can not choose same place");
                request.getRequestDispatcher("maybay.jsp").forward(request, response);
            }

            if (a.isIs_active() == false) {
                check = false;
                request.setAttribute("errU", "sorry your account didn't active anymore !!!");
                request.getRequestDispatcher("maybay.jsp").forward(request, response);
            }

            String date_start = request.getParameter("date_start");
            String date_endcheck = request.getParameter("date_return");
            String note = request.getParameter("type");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(date_start);
            
            Date currentDate = new Date();
            currentDate = sdf.parse(sdf.format(currentDate));
            if (startDate.before(currentDate)) {
                check = false;
                request.setAttribute("errDateStart", "Start date cannot be before today");
                request.getRequestDispatcher("maybay.jsp").forward(request, response);
            }
            if (date_endcheck == null) {
                date_end = "không Khứ Hồi";
            } else {
                Date endDate = sdf.parse(date_endcheck);
                if (endDate.before(startDate)) {
                    check = false;
                    request.setAttribute("errDate", "Return date cannot be before start date");
                    request.getRequestDispatcher("maybay.jsp").forward(request, response);
                } else {
                    date_end = date_endcheck;
                }

            }
            if (check == true) {
                session.setAttribute("user", userID);
                session.setAttribute("address_start", address_start);
                session.setAttribute("address_end", address_end);
                session.setAttribute("date_start", date_start);
                session.setAttribute("date_end", date_end);
                session.setAttribute("note", note);

                request.getRequestDispatcher("orderMaybay.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            HttpSession session = request.getSession();

            int userID = Integer.parseInt(request.getParameter("user"));

            UserDao dao = new UserDao();
            User a = dao.getUserByID(userID);
            String address_start = request.getParameter("address_start");
            String address_end = request.getParameter("address_end");

            String date_start = request.getParameter("date_start");
            String date_end = request.getParameter("date_end");
            String note = request.getParameter("note");
            String plane = request.getParameter("plane");
            int songuoilon = Integer.parseInt(request.getParameter("nguoilon"));

            if (songuoilon < 1) {
                check = false;
                request.setAttribute("songuoilon", songuoilon);

                request.setAttribute("error", "can it nhat 1 hanh khach");
                request.getRequestDispatcher("orderMaybay.jsp").forward(request, response);
            }

            if (check == true) {
                session.setAttribute("nguoidung", a);
                session.setAttribute("user", userID);
                session.setAttribute("address_start", address_start);
                session.setAttribute("address_end", address_end);
                session.setAttribute("date_start", date_start);
                session.setAttribute("date_end", date_end);
                session.setAttribute("note", note);
                request.setAttribute("plane", plane);
                session.setAttribute("songuoilon", songuoilon);

                float total_money = 0;
                switch (plane) {
                    case "vietnamAir":
                        switch (address_start) {
                            case "Hanoi":
                                switch (address_end) {
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "Ho-Chi-Minh":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }

                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ HCM có thể được thêm tương tự
                                }
                                break;
                            case "Da-Nang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Đà Nẵng có thể được thêm tương tự
                                }
                                break;
                            case "Nha-Trang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Nha Trang có thể được thêm tương tự
                                }
                                break;
                        }
                        break;
                    case "Bamboo":
                        switch (address_start) {
                            case "Hanoi":
                                switch (address_end) {
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "Ho-Chi-Minh":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }

                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ HCM có thể được thêm tương tự
                                }
                                break;
                            case "Da-Nang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Đà Nẵng có thể được thêm tương tự
                                }
                                break;
                            case "Nha-Trang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Nha Trang có thể được thêm tương tự
                                }
                                break;
                        }
                        break;
                    case "Vietjet":
                        switch (address_start) {
                            case "Hanoi":
                                switch (address_end) {
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "Ho-Chi-Minh":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }

                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ HCM có thể được thêm tương tự
                                }
                                break;
                            case "Da-Nang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Đà Nẵng có thể được thêm tương tự
                                }
                                break;
                            case "Nha-Trang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Nha Trang có thể được thêm tương tự
                                }
                                break;
                        }
                        break;
                    case "Etihah":
                        switch (address_start) {
                            case "Hanoi":
                                switch (address_end) {
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case "Ho-Chi-Minh":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 4100 * songuoilon; // Giá vé phổ thông từ Hà Nội tới HCM
                                                break;
                                            case "hangnhat":
                                                total_money = 5500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới HCM
                                                break;
                                            case "thuonggia":
                                                total_money = 7333 * songuoilon; // Giá vé thương gia từ Hà Nội tới HCM
                                                break;
                                        }

                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ HCM có thể được thêm tương tự
                                }
                                break;
                            case "Da-Nang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    case "Nha-Trang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Đà Nẵng
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Đà Nẵng
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Đà Nẵng có thể được thêm tương tự
                                }
                                break;
                            case "Nha-Trang":
                                switch (address_end) {
                                    case "Hanoi":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2900 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 4000 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 5333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Ho-Chi-Minh":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2500 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3500 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4333 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    case "Da-Nang":
                                        switch (note) {
                                            case "phothong":
                                                total_money = 2300 * songuoilon; // Giá vé phổ thông từ Hà Nội tới Nha Trang
                                                break;
                                            case "hangnhat":
                                                total_money = 3200 * songuoilon; // Giá vé hạng nhất từ Hà Nội tới Nha Trang
                                                break;
                                            case "thuonggia":
                                                total_money = 4000 * songuoilon; // Giá vé thương gia từ Hà Nội tới Nha Trang
                                                break;
                                        }
                                        break;
                                    // Các điểm đến khác từ Nha Trang có thể được thêm tương tự
                                }
                                break;
                        }
                        break;
                    default:
                        throw new AssertionError();
                }

                request.setAttribute("total", total_money);

                request.getRequestDispatcher("orderMaybay.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
