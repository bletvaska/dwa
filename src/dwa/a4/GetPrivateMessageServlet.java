package dwa.a4;

import dwa.Configuration;
import dwa.a1.PrivateMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetPrivateMessageServlet", urlPatterns = {"/GetPrivateMessageServlet"})
public class GetPrivateMessageServlet extends HttpServlet {
    private static final String QUERY = "SELECT m.id, s.login as sender, r.login as receiver, m.message" +
            " FROM message m \n" +
            " LEFT JOIN user s ON m.sender=s.id\n" +
            " LEFT JOIN user r ON m.receiver=r.id\n" +
            " WHERE m.id=%s";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            // connect and make query
            Class.forName(Configuration.DRIVER_CLASS);
            Connection con = DriverManager.getConnection(Configuration.URL);
            Statement stmt = con.createStatement();

            // create new message
            String id = request.getParameter("id");
            if(id == null){
                // return NOT FOUND
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // get all messages
            ResultSet rs = stmt.executeQuery(String.format(QUERY, id));
            PrivateMessage message;
            if(rs.next()) {
                message = new PrivateMessage(rs);

                // close
                rs.close();
                stmt.close();
                con.close();

                // return JSON
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(message.toJson());
                out.close();
            }else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
