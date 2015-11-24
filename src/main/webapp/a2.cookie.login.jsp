<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:master>
  <jsp:attribute name="title">DVA - Login with Sesion</jsp:attribute>

  <jsp:attribute name="head">
    <link href="css/signin.css" rel="stylesheet">
  </jsp:attribute>


  <jsp:body>
    <c:if test="${not empty alert}">
      <div class="alert alert-warning">${alert}</div>
    </c:if>


    <form class="form-signin" action="CookieLoginServlet" method="get">
      <h2 class="form-signin-heading">Please sign in</h2>
      <label for="login" class="sr-only">Login</label>
      <input type="text" name="login" id="login" class="form-control" placeholder="Login" required autofocus>
      <label for="password" class="sr-only">Password</label>
      <input type="text" name="password" id="password" class="form-control" placeholder="Password" required>
      <div class="checkbox">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
  </jsp:body>

</t:master>
