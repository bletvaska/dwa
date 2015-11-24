<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:master>
    <jsp:attribute name="title">DVA - Private Messages</jsp:attribute>

  <jsp:attribute name="header">
    <h1>My Private Messages</h1>
  </jsp:attribute>

    <jsp:body>
        <pre>${query}</pre>

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
    </jsp:body>
</t:master>
