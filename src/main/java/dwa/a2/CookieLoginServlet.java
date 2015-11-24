package dwa.a2;

import dwa.Configuration;
import dwa.a1.PrivateMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "CookieLoginServlet", urlPatterns = {"/CookieLoginServlet"})
public class CookieLoginServlet extends HttpServlet {
    private static final String QUERY = "SELECT * FROM user WHERE login='%s' AND password='%s'";


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // get params
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Class.forName(Configuration.DRIVER_CLASS);
            Connection con = DriverManager.getConnection(Configuration.URL);
            Statement stmt = con.createStatement();
            String query = String.format(QUERY, login, password);

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                Cookie loginCookie = new Cookie("login", login);
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                response.sendRedirect("a2.cookie.main.jsp");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/a2.cookie.login.jsp");
                request.setAttribute("alert", "Login or password is wrong.");
                rd.include(request, response);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
