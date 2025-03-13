/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usercontroller;

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
public class FavouriteList extends HttpServlet {

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
            out.println("<title>Servlet FavouriteList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FavouriteList at " + request.getContextPath() + "</h1>");
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
        String userId = request.getParameter("id");

        Cookie[] cookies = request.getCookies();

        String orderIdsString = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (userId.equals(cookie.getName())) {
                    orderIdsString = cookie.getValue();
                    break;
                }
            }
        }

        vehiclesDao vhcDao = new vehiclesDao();
        List<Vehicles> vlist = vhcDao.getAll();
        orderDetailDao deDao = new orderDetailDao();
        List<order_detail> dList = deDao.getAll();

        orderDao dao = new orderDao();
        List<String> ckList = new ArrayList<>();
        List<orders> fvList = new ArrayList<>();
        if (orderIdsString != null) {
            String[] orderIdArray = orderIdsString.split("_");

            for (String orderId : orderIdArray) {
                System.out.println("Order ID: " + orderId);
                ckList.add(orderId);
            }
        }
        for (String str : ckList) {
            fvList.add(dao.getAllbyStrid(str));
        }

        session.setAttribute("fvList", fvList);
        session.setAttribute("userID", userId);
        session.setAttribute("vList", vlist);
        session.setAttribute("deList", dList);
        request.getRequestDispatcher("favouriteList.jsp").forward(request, response);

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
        int detailID = Integer.parseInt(request.getParameter("detailID"));
        vehiclesDao vDao = new vehiclesDao();
        orderDao orDao = new orderDao();
        orderDetailDao deDao = new orderDetailDao();
        orders ord = orDao.getOrdersByID(detailID);
        order_detail ordetail = deDao.getOrdersByID(ord.getId());
        Vehicles vhc = vDao.getVehiclesByID(ord.getVehicles_id());

        request.setAttribute("vhc", vhc);
        request.setAttribute("orde", ordetail);
        request.setAttribute("ord", ord);
        request.getRequestDispatcher("favouriteList.jsp").forward(request, response);
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
