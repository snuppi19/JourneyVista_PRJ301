/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usercontroller;

import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.User;

/**
 *
 * @author Admin
 */
public class UserOrdercosan extends HttpServlet {

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
            out.println("<title>Servlet UserOrdercosan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserOrdercosan at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userID"));
        UserDao dao = new UserDao();
        User a = dao.getUserByID(userId);
        String order_start = request.getParameter("departureCity");
        String order_end = request.getParameter("arrivalCity");
        String plane = request.getParameter("airline");

        if (plane.equals("Vietnam Airline")) {
            plane = "vietnamAir";
        } else if (plane.equals("BamBoo Airway")) {
            plane = "Bamboo";
        } else if (plane.equals("Viet Jet")) {
            plane = "Vietjet";
        } else {
            plane = "Etihah";
        }
        String type = request.getParameter("classType");
        if (type.equals("Thuong Gia")) {
            type = "thuonggia";
        } else if (type.equals("Hang Nhat")) {
            type = "hangnhat";
        } else {
            type = "phothong";
        }
        String note = request.getParameter("flightNumber");
        String price = request.getParameter("price");
        String date_end = "không Khứ Hồi";
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_start = currentDate.format(formatter);
        boolean check = true;
        if (a.isIs_active() == false) {
            request.setAttribute("errU", "sorry your account didn't active anymore !!!");
            request.getRequestDispatcher("maybay.jsp").forward(request, response);
        }
        if (check == true) {
            session.setAttribute("nguoidung", a);
            session.setAttribute("note", type);
            request.setAttribute("plane", plane);
            session.setAttribute("address_start", order_start);
            session.setAttribute("address_end", order_end);
            session.setAttribute("date_start", date_start);
            session.setAttribute("date_end", date_end);
            session.setAttribute("songuoilon", 1);
            request.setAttribute("total", price);

            request.getRequestDispatcher("orderMaybay.jsp").forward(request, response);
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
        processRequest(request, response);
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
