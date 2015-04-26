package dwa.a1;

import dwa.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    private static final String QUERY = "INSERT INTO user VALUES (NULL, '%s', '%s', NULL, NULL)";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

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

            // list all entries first
//            out.append("<pre>");
//            ResultSet rs = stmt.executeQuery("SELECT * FROM phonebook");
//            while(rs.next()){
//                String n = rs.getString("name");
//                String p = rs.getString("phone");
//                out.append(n + " " + p + "<br/>");
//            }
//            rs.close();
//
//            out.append("</pre>");
//            out.append("<hr/>");

            // insert
            String query = String.format(QUERY, login, password);
            out.println(query + "<br/>");

            int result = stmt.executeUpdate(query);

            if(result == 1){
                out.append("Successfully registered.");
            }else{
                out.append("Something wrong.");
            }

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace(out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
