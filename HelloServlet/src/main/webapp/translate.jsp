<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/6/2023
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<h1>Dùng JSP ngon hơn bạn ơi</h1>
<%= "Hello" %>
${"Hello JSTL"}
<form action="/translate" method="post">
    <input type="text" name="txtSearch" >
    <input type="submit" value="Translate" />
</form>


<%
    System.out.println(MINMIN);
    if(request.getAttribute("value")!=null){
        out.println("<h6>Meaning: " + request.getAttribute("value") + "</h6>");
    }
%>
<ul>
    <%
        /**
         if(request.getAttribute("listVocabulary")!=null){
         for (String item : (Set<String>) request.getAttribute("listVocabulary")) {
         out.println("<li>" + item + MAX + "</li>");
         }
         }
         **/
    %>


    <c:if test="${requestScope.listVocabulary != null}">
        <c:set var="count" value="0"></c:set>
        <c:forEach var="vocabulary" items="${requestScope.listVocabulary}">
            <c:set var="count" value="${count+1}"></c:set>
            <c:choose>
                <c:when test="${count %2 !=0}">
                    <li style="color: green">${vocabulary}</li>
                </c:when>
                <c:otherwise>
                    <li >${vocabulary}</li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>
