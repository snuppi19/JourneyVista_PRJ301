/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDao;
import dal.UserDao;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Accounts;

import model.GoogleAccount;
import model.User;

/**
 *
 * @author Admin
 */
public class GG_USER extends HttpServlet {

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
        boolean check = false;
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);

        AccountDao accDao = new AccountDao();
        List<Accounts> list_acc = accDao.getAll();
        UserDao dao = new UserDao();
        for (Accounts a : list_acc) {
            if (acc.getEmail().equals(a.getEmail())) {
                check=true;
                User user = dao.getUserByID(a.getUser_id());

                session.setAttribute("dataUser", user);
                response.sendRedirect("maybay.jsp");
            }
        }

        if (check == false) {
            session.setAttribute("gg", acc);
            request.getRequestDispatcher("HoSoUser.jsp").forward(request, response);
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

        System.out.println("heeh");
        try {
            System.out.println("heeh2");
            boolean check = true;
            boolean gender = true;
            HttpSession session = request.getSession();
            UserDao dao = new UserDao();
            AccountDao accDao = new AccountDao();
            String email = request.getParameter("email");
            String password = "email@email.com";
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");
            String genderCheck = request.getParameter("gender");
            fullname = formatString(fullname);
            address = formatString(address);
            List<User> listU = new ArrayList<>();
            if (!isAgeValid(dob, 18)) {
                check = false;
                System.out.println("<18");
                session.setAttribute("fullname", fullname);
                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                request.setAttribute("err", "Nguoi dung chua du 18 tuoi");
                request.getRequestDispatcher("HoSoUser.jsp").forward(request, response);
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
                java.sql.Date sqlUpdated_at = new java.sql.Date(updated_at.getTime());
                User a = new User(fullname, phone, gender, sqlBirthdate, address, password, email, sqlCreated_at, sqlUpdated_at, 0, 1, true);

                dao.insert(a);// tao user

                listU = dao.getAll();//tao acc cho user
                for (User user : listU) {
                    if (user.getEmail().equals(email)) {
                        Accounts b = new Accounts(email, password, user.getId(), 2);
                        accDao.insert(b);
                    }
                }
                List<Accounts> list_acc = accDao.getAll();
                for (Accounts b : list_acc) {
                    if (email.equals(b.getEmail()) && password.equals(b.getPassword())) {
                        check = true;

                        User user = dao.getUserByID(b.getUser_id());

                        session.setAttribute("dataUser", user);
                        response.sendRedirect("maybay.jsp");
                    }
                }

//                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
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
