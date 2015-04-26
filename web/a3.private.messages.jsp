<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // allow access only if session exists
    if(session.getAttribute("login") == null){
        response.sendRedirect("a2.session.login.jsp");
    }
%>

<t:master>
    <jsp:attribute name="title">DVA - Private Messages of ${sessionScope.login}</jsp:attribute>

    <jsp:attribute name="header">
        <h1>My Private Messages</h1>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${not empty alert}">
            <div class="alert alert-warning">${alert}</div>
        </c:if>

        <div class="row">
            <table class="table table-responsive">
                <tr>
                    <th>From</th>
                    <th>To</th>
                    <th>Message</th>
                </tr>

                <c:forEach var="message" items="${messages}">
                <tr>
                    <td>
                            ${message.sender}
                    </td>
                    <td>
                            ${message.receiver}
                    </td>
                    <td>
                            ${message.message}
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>

        <div class="row">
            <form method="get" action="/PrivateMessagesServlet">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Send message to..." name="to"
                           value="${receiver}" required
                           autofocus/>
                </div>
                <div class="form-group">
                    <textarea class="form-control" rows="3" name="message" placeholder="Message" required>${message}</textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </div>

    </jsp:body>
</t:master>
