package dwa.a1;

import dwa.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends javax.servlet.http.HttpServlet {
    private static final String QUERY = "SELECT * FROM user WHERE login='%s' AND password='%s'";


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get params
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // output writer
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection con = null;
        try {
            Class.forName(Configuration.DRIVER_CLASS);
            con = DriverManager.getConnection(Configuration.URL);
            Statement stmt = con.createStatement();
            String query = String.format(QUERY, login, password);

            out.println(query + "<br>");

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                out.println("<h1>Welcome " + login + "</h1>");
                out.println(String.format("email: %s<br/>", rs.getString("email")));
                out.println(String.format("role: %s<br/>", rs.getString("role")));
            } else {
                out.println("LOGIN FAILED");
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace(out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        processRequest(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        processRequest(request, response);
    }
}
