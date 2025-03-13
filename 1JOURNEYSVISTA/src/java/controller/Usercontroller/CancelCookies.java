/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class CancelCookies extends HttpServlet {

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
            out.println("<title>Servlet CancelCookies</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelCookies at " + request.getContextPath() + "</h1>");
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
        String ordid = request.getParameter("id");
        int user_id =  Integer.parseInt(request.getParameter("extraParam"));
        
        System.out.println(ordid);
        System.out.println(user_id);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
          
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ordid)) {
                   
                    String currentValue = cookie.getValue();
                   
                    String[] values = currentValue.split("_");

                  
                    StringBuilder newValue = new StringBuilder();
                    for (String value : values) {
                        if (!value.equals(String.valueOf(user_id))) {
                            if (newValue.length() > 0) {
                                newValue.append("_");
                            }
                            newValue.append(value);
                        }
                    }

                    
                    if (newValue.length() == 0) {
                       
                        cookie.setMaxAge(0); 
                        cookie.setPath("/"); 
                        response.addCookie(cookie); 
                    } else {
                      
                        cookie.setValue(newValue.toString());
                        cookie.setPath("/");  
                        response.addCookie(cookie); 
                    }
                    break;
                }
            }
        }
        response.sendRedirect("Userdashboard.jsp");
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
