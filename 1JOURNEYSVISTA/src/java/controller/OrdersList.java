/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.orderDao;
import dal.orderDetailDao;
import dal.vehiclesDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Vehicles;
import model.order_detail;
import model.orders;

/**
 *
 * @author Admin
 */
public class OrdersList extends HttpServlet {

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
            out.println("<title>Servlet OrdersList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrdersList at " + request.getContextPath() + "</h1>");
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
        float earning = 0;
        int process = 0;
        int ticket=0;
        orderDetailDao dDao= new orderDetailDao();
        vehiclesDao vhcDao = new vehiclesDao();
        List<Vehicles> vlist = vhcDao.getAll();
        orderDao orDao = new orderDao();
        List<orders> olist = new ArrayList<>();
        olist = orDao.getAll();
        for (orders o : olist) {
            if (o.isOrder_isActive() == false) {
                earning += o.getTotal_money();
            } else {
                process += 1;
            }
            
          order_detail newDe=dDao.getOrdersByID(o.getId());
            System.out.println(newDe.getNumber_people());
          ticket+=newDe.getNumber_people();
            System.out.println("---");
            System.out.println(ticket);
            
        }
        
        int number = olist.size();
        session.setAttribute("process", process);
        session.setAttribute("ticket", ticket);
        session.setAttribute("earning", earning);
        session.setAttribute("ordCount", number);
        session.setAttribute("oList", olist);
        session.setAttribute("vList", vlist);
        request.getRequestDispatcher("admin.jsp").forward(request, response);

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

        int ordID = Integer.parseInt(request.getParameter("ordID"));
        vehiclesDao vDao = new vehiclesDao();
        orderDao orDao = new orderDao();
        orderDetailDao deDao = new orderDetailDao();
        orders slectORD = orDao.getOrdersByID(ordID);
        order_detail ordetail = deDao.getOrdersByID(slectORD.getId());
        Vehicles vhc = vDao.getVehiclesByID(slectORD.getVehicles_id());
        request.setAttribute("slORD", slectORD);
        request.setAttribute("vhc", vhc);
        request.setAttribute("orde", ordetail);
        request.getRequestDispatcher("admin.jsp").forward(request, response);

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
