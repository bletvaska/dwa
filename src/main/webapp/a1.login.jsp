<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
  <jsp:attribute name="title">DVA - Signin</jsp:attribute>

  <jsp:attribute name="head">
    <link href="css/signin.css" rel="stylesheet">
  </jsp:attribute>


  <jsp:body>
    <form class="form-signin" action="LoginServlet" method="get">
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
