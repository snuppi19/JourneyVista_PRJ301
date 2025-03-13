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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accounts;
import model.User;

/**
 *
 * @author Admin
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cb = request.getParameter("cb");
        System.out.println(cb);
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountDao dao = new AccountDao();
        UserDao uDao = new UserDao();// lay user tu user id trong account

        List<Accounts> list_acc = dao.getAll();
        boolean check = false;
        for (Accounts a : list_acc) {
            if (email.equals(a.getEmail()) && password.equals(a.getPassword())) {
                check = true;

                if (cb != null) {
                    System.out.println(cb);
                    Cookie cku = new Cookie("user", email);
                    Cookie ckp = new Cookie("pass", password);
                    

                    cku.setMaxAge(30);
                    ckp.setMaxAge(30);
//                    ckcb.setMaxAge(30);
                    cku.setPath("/");
                    ckp.setPath("/");
//                    ckcb.setPath("/");
                    response.addCookie(cku);
                    response.addCookie(ckp);
//                    response.addCookie(ckcb);
                } else {
                    Cookie cku = new Cookie("user", email);
                    Cookie ckp = new Cookie("pass", password);
                    cku.setMaxAge(0);
                    ckp.setMaxAge(0);
                }

                User user = uDao.getUserByID(a.getUser_id());

                session.setAttribute("dataUser", user);
                response.sendRedirect("maybay.jsp");
            }
        }
        if (check == false) {
            String err = "Email or Password incorrect!";
            session.setAttribute("email", email);
            session.setAttribute("pass", password);
            request.setAttribute("error", err);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
            boolean gender = true;
            HttpSession session = request.getSession();
            UserDao dao = new UserDao();
            AccountDao accDao = new AccountDao();

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");
            String genderCheck = request.getParameter("gender");
            fullname = formatString(fullname);
            address = formatString(address);
            List<User> listU = new ArrayList<>();
            listU = dao.getAll();
            for (User user : listU) {
                if (email.equals(user.getEmail())) {
                    check = false;
                    session.setAttribute("email", email);
                    session.setAttribute("pass", password);
                    session.setAttribute("fullname", fullname);
                    session.setAttribute("phone", phone);
                    session.setAttribute("address", address);
                    request.setAttribute("errorE", "Email da ton tai");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
            if (!isAgeValid(dob, 18)) {
                check = false;
                System.out.println("<18");
                session.setAttribute("email", email);
                session.setAttribute("pass", password);
                session.setAttribute("fullname", fullname);
                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                request.setAttribute("err", "Nguoi dung chua du 18 tuoi");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

            if (check == true) {

                if (genderCheck.equals("female")) {
                    gender = false;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthdate = sdf.parse(dob);
                java.sql.Date sqlBirthdate = new java.sql.Date(birthdate.getTime());
                LocalDateTime now = LocalDateTime.now();
                Date created_at = new Date();
                Date updated_at = new Date();
                java.sql.Date sqlCreated_at = new java.sql.Date(created_at.getTime());
                java.sql.Date sqlUpdated_at = new java.sql.Date(created_at.getTime());
                User a = new User(fullname, phone, gender, sqlBirthdate, address, password, email, sqlCreated_at, sqlUpdated_at, 0, 0, true);

                dao.insert(a);// tao user

                listU = dao.getAll();//tao acc cho user
                for (User user : listU) {
                    if (user.getEmail().equals(email)) {
                        Accounts b = new Accounts(email, password, user.getId(), 2);
                        accDao.insert(b);
                    }
                }

                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (ParseException ex) {

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

    public static boolean isAgeValid(String dobString, int requiredAge) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày tháng
        if (dobString != null && !dobString.isEmpty()) {
            Date dob = sdf.parse(dobString);

            Calendar today = Calendar.getInstance();

            Calendar birthDate = Calendar.getInstance();
            birthDate.setTime(dob);

            int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH)
                    || (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            return age >= requiredAge;
        }
        return false;
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
