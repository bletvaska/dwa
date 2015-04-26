<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:master>
  <jsp:attribute name="title">DVA - Home</jsp:attribute>

  <jsp:body>
    <div class="row">
      <h1>Hello in DWA!</h1>
      <p>
        <i>Damn Vulnerable web Application</i> is set of labs for security course.
      </p>


      <h2>List of Labs</h2>

      <h3>A1 Injection</h3>

      <ul>
        <li>
          <a href="/MessagesServlet?uid=2">Injection - Private Messages</a> (SQL Injection)
        </li>

        <li>
          <a href="/a1.login.jsp">Injection - Login</a> (SQL Injection)
        </li>

        <li>
          <a href="/a1.register.jsp">Injection - Register New User</a> (SQL Injection)
        </li>

        <li>
          <a href="/a1.command.jsp">Injection - Command</a> (Command Injection)
        </li>
      </ul>

      <h3>A2 Broken Authentication and Session Management</h3>

      <ul>
          <li>
            <a href="/a2.cookie.main.jsp">Cookie Based Homepage</a> (You have to log in)
          </li>

          <li>
            <a href="/a2.session.main.jsp">Session Based Homepage</a> (You have to log in)
          </li>

        <li>
          <a href="/a2.url.rewrite.main.jsp">URL Rewrite Based Homepage</a> (You have to log in)
        </li>
      </ul>


      <h3>A3 Cross-Site Scripting (XSS)</h3>

      <ul>
        <li>
          <a href="/PrivateMessagesServlet">Private Messages</a> (Must be logged with <a
                href="/a2.session.main.jsp">Session Login</a>)
        </li>
      </ul>

      <h3>A4 Insecure Direct Object References</h3>

      <ul>
        <li>
          <a href="/GetPrivateMessageServlet?id=1">Private Messages Getter</a>
        </li>
      </ul>



    </div>
  </jsp:body>

</t:master>