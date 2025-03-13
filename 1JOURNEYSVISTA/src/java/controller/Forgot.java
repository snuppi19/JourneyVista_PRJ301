/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Accounts;
import model.User;

/**
 *
 * @author Admin
 */
public class Forgot extends HttpServlet {

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
            out.println("<title>Servlet Forgot</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Forgot at " + request.getContextPath() + "</h1>");
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

        AccountDao dao = new AccountDao();
        List<Accounts> list_acc = dao.getAll();

        String email = request.getParameter("email");
        boolean check = false;
        for (Accounts a : list_acc) {
            if (email.equals(a.getEmail())) {
                check = true;
                session.setAttribute("email", email);
                response.sendRedirect("forgot2.jsp");
            }
        }
        if (check == false) {
            String err = "Email not exsit!";
            session.setAttribute("email", email);
            session.setAttribute("error", err);
            request.getRequestDispatcher("forgot.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        AccountDao dao = new AccountDao();
        UserDao Udao = new UserDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Accounts oldACC = dao.getACCbyEmail(email);
        User oldUser = Udao.getUserByID(oldACC.getUser_id());

        Accounts newACC = new Accounts(oldACC.getAcc_id(), email, password, oldACC.getUser_id(), oldACC.getRole());
        User a=new User(oldUser.getId(),oldUser.getFullname(), oldUser.getPhone_number(), oldUser.isGender(), oldUser.getDob(), oldUser.getAddress(), password, oldUser.getEmail(), oldUser.getCreated_at(), oldUser.getUpdated_at(), oldUser.getFb_account(), oldUser.getGg_acount(), oldUser.isIs_active());
        
        System.out.println(a.getPassword());
        System.out.println(password);
        Udao.update(a);
        dao.update(newACC);
        response.sendRedirect("Login.jsp");

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
