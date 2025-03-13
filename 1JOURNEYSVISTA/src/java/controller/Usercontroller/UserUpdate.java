/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usercontroller;

import static controller.Update.formatString;
import static controller.Update.isAgeValid;
import dal.AccountDao;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Accounts;
import model.User;

/**
 *
 * @author Admin
 */
public class UserUpdate extends HttpServlet {

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
            out.println("<title>Servlet UserUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserUpdate at " + request.getContextPath() + "</h1>");
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
        int userID = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        UserDao dao = new UserDao();
        User user = dao.getUserByID(userID);
        session.setAttribute("user", user);
        request.getRequestDispatcher("UserUpdate.jsp").forward(request, response);

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
            System.out.println(genderCheck);
            fullname = formatString(fullname);
            address = formatString(address);
            List<User> listU = new ArrayList<>();

            if (!isAgeValid(dob, 18)) {
                check = false;
                request.setAttribute("err", "Nguoi dung chua du 18 tuoi");
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }

            if (check == true) {
                System.out.println("huhu");
                if (genderCheck.equals("female")) {
                    gender = false;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthdate = sdf.parse(dob);

                java.sql.Date sqlBirthdate = new java.sql.Date(birthdate.getTime());
                LocalDateTime now = LocalDateTime.now();

                Date updated_at = new Date();

                java.sql.Date sqlUpdated_at = new java.sql.Date(updated_at.getTime());

                Accounts oldACC = accDao.getACCbyEmail(email);
                User oldUser = dao.getUserByID(oldACC.getUser_id());
                Accounts newACC = new Accounts(oldACC.getAcc_id(), email, password, oldACC.getUser_id(), oldACC.getRole());
                User a = new User(oldUser.getId(), fullname, phone, gender, sqlBirthdate, address, password, email, oldUser.getCreated_at(), sqlUpdated_at, oldUser.getFb_account(), oldUser.getGg_acount(), oldUser.isIs_active());

                System.out.println(a.getFullname());
                dao.update(a);
                accDao.update(newACC);

                response.sendRedirect("Userdashboard.jsp");

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
