<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // allow access only if session exists
    if(session.getAttribute("login") == null){
        response.sendRedirect("a2.url.rewrite.login.jsp");
    }

    // encode logout url
    request.setAttribute("logoutUrl", response.encodeURL("/UrlRewriteLogoutServlet"));
%>

<t:master>
    <jsp:attribute name="title">DVA - Welcome User</jsp:attribute>

    <jsp:body>

        <h1>Welcome ${sessionScope.login}</h1>

        <div class="alert alert-info">
            Message for you: ${sessionScope.message}
        </div>

        <p>
            Your session ID: ${pageContext.session.id}
        </p>

        <a href="${logoutUrl}"
           class="btn btn-primary">Logout
        </a>
    </jsp:body>
</t:master>
