<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
  <jsp:attribute name="title">DVA - Register</jsp:attribute>

  <jsp:attribute name="header">
    <h1>New User</h1>
  </jsp:attribute>

  <jsp:body>
    <p>
      Enter your login and password to register to DVA
    </p>

    <form class="form-inline" action="RegisterServlet" method="get">
      <div class="form-group">
        <label for="login" class="sr-only">Login</label>
        <input type="text" id="login" name="login" placeholder="Login" class="form-control"/>
      </div>

      <div class="form-group">
        <label for="password" class="sr-only">Password</label>
        <input type="text" id="password" name="password" placeholder="Password" class="form-control"/>
      </div>
      <button class="btn btn-primary" type="submit">Register</button>
    </form>
  </jsp:body>
</t:master>
