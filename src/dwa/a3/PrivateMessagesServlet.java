package dwa.a3;

import dwa.Configuration;
import dwa.a1.PrivateMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PrivateMessagesServlet", urlPatterns = {"/PrivateMessagesServlet"})
public class PrivateMessagesServlet extends HttpServlet {
    private static final String QUERY = "SELECT m.id, s.login as sender, r.login as receiver, m.message" +
            " FROM message m \n" +
            " LEFT JOIN user s ON m.sender=s.id\n" +
            " LEFT JOIN user r ON m.receiver=r.id\n" +
            " WHERE m.sender='%s' OR m.receiver='%s'";
    private static final String CREATE_QUERY = "INSERT INTO message VALUES(NULL, %d, %d, '%s')";
    private static final String RETRIEVE_RECEIVER_QUERY = "SELECT id FROM user WHERE login='%s'";


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        // if no session is open, redirect to login
        if(session == null){
            RequestDispatcher view = request.getRequestDispatcher("a2.session.login.jsp");
            view.forward(request, response);
        }

//        response.setHeader("SET-COOKIE", "JSESSIONID=" + session.getId());

        try {
            // connect and make query
            Class.forName(Configuration.DRIVER_CLASS);
            Connection con = DriverManager.getConnection(Configuration.URL);
            Statement stmt = con.createStatement();
            ResultSet rs;

            // create new message
            String receiver = request.getParameter("to");
            String text = request.getParameter("text");
            if(receiver != null && text != null){
                // retrieve receiver id
                rs = stmt.executeQuery(String.format(RETRIEVE_RECEIVER_QUERY, receiver));

                if(rs.next()){
                    int receiver_id = rs.getInt("id");
                    stmt.executeUpdate(String.format(CREATE_QUERY, session.getAttribute("uid"), receiver_id, text));
                }else{
                    request.setAttribute("alert", "No such user " + receiver);
                }

                rs.close();
            }else if(receiver != null || text != null){
                // if some of the values is missing
                session.setAttribute("receiver", receiver);
                session.setAttribute("text", text);
                session.setAttribute("alert", "Receiver or text is missing");
            }

            // get all messages
            List<PrivateMessage> messages = new ArrayList<PrivateMessage>();
            rs = stmt.executeQuery(String.format(QUERY, session.getAttribute("uid"), session.getAttribute("uid")));
            while(rs.next()){
                messages.add(new PrivateMessage (rs));
            }

            // close
            rs.close();
            stmt.close();
            con.close();

            // add to view
            request.setAttribute("messages", messages);
            RequestDispatcher view = request.getRequestDispatcher("a3.private.messages.jsp");
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
