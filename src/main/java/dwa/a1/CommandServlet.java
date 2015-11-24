package dwa.a1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "CommandServlet", urlPatterns = {"/CommandServlet"})
public class CommandServlet extends HttpServlet {
    private static final String COMMAND = "ping -c 4 %s";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get params
        String host = request.getParameter("host");

        // output writer
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // prepare the command to run as string
        String[] list = {
                "bash",
                "-c",
                "ping -c 4 " + host
        };

        String command = String.format(COMMAND, host);
        out.append(command);

        out.append("<pre>");

        // the magic goes here
        try {
            Process p = Runtime.getRuntime().exec(list);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                out.append(line + "\n");
            }

            out.append("</pre>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
