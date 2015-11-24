<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
  <jsp:attribute name="title">DVA - Ping</jsp:attribute>

  <jsp:attribute name="header">
    <h1>Ping</h1>
  </jsp:attribute>

  <jsp:body>
    <p>
      In the following form, enter the hostname or IP address of host to check connection with ping.
    </p>

    <form class="form-inline" action="CommandServlet" method="get">
      <div class="form-group">
        <label for="host" class="sr-only">Host</label>
        <input type="text" id="host" name="host" placeholder="Hostname or IP" class="form-control"/>
      </div>
      <button class="btn btn-primary" type="submit">Ping</button>
    </form>
  </jsp:body>
</t:master>