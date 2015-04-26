package dwa.a1;

import dwa.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MessagesServlet", urlPatterns = {"/MessagesServlet"})
public class MessagesServlet extends HttpServlet {
    private static final String QUERY = "SELECT m.id, s.login as sender, r.login as receiver, m.message" +
            " FROM message m \n" +
            " LEFT JOIN user s ON m.sender=s.id\n" +
            " LEFT JOIN user r ON m.receiver=r.id\n" +
            " WHERE m.sender='%s' OR m.receiver='%s'";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get params
        String uid = request.getParameter("uid");

        try {
            // connect and make query
            Class.forName(Configuration.DRIVER_CLASS);
            Connection con = DriverManager.getConnection(Configuration.URL);
            Statement stmt = con.createStatement();
            String query = String.format(QUERY, uid, uid);

            List<PrivateMessage> messages = new ArrayList<PrivateMessage>();

            // get all messages
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                messages.add(new PrivateMessage (rs));
            }

            // close
            rs.close();
            stmt.close();
            con.close();

            // add to view
            request.setAttribute("messages", messages);
            request.setAttribute("query", query);
            RequestDispatcher view = request.getRequestDispatcher("a1.messages.jsp");
            view.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
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
