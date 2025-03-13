/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.HelpDao;
import dal.HelpDaouser;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Acskh;
import model.Ucskh;
import model.User;

/**
 *
 * @author Admin
 */
public class HelpADMINlist extends HttpServlet {

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
            out.println("<title>Servlet HelpADMINlist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelpADMINlist at " + request.getContextPath() + "</h1>");
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
        HelpDao dao = new HelpDao();
        HelpDaouser Udao = new HelpDaouser();
        UserDao USERdAO = new UserDao();
        List<User> userlist = USERdAO.getAll();
        List<Acskh> Alist = dao.getAll();
        System.out.println(Alist);
        List<Ucskh> Ulist = Udao.getAll();
        System.out.println(Ulist);

        HttpSession session=request.getSession();
        session.setAttribute("userList", userlist);
        session.setAttribute("Alist", Alist);
        session.setAttribute("Ulist", Ulist);
        request.getRequestDispatcher("AdminHelp.jsp").forward(request, response);

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

        int questionID = Integer.parseInt(request.getParameter("questionID"));
        HelpDaouser dao = new HelpDaouser();
        Ucskh ucskh = dao.getUcskhByID(questionID);

        String mess = ucskh.getDescription();
        request.setAttribute("questionID", questionID);
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("replyhelp.jsp").forward(request, response);

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
