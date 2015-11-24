<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    //allow access only if session exists
    if(session.getAttribute("login") == null){
        response.sendRedirect("a2.session.login.jsp");
    }
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

        <p>
            <a href="PrivateMessagesServlet">Private Messages</a>
        </p>

        <a href="SessionLogoutServlet" class="btn btn-primary">Logout</a>
    </jsp:body>
</t:master>
