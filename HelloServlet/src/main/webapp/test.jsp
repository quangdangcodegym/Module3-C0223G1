<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/6/2023
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%!
        int counter;
        public void jspInit(){
            counter = 0;
            System.out.println("Khoi tao JSP");
        }

        public void jspDestroy(){
            System.out.println("Huy JSP");
        }
    %>
<h1>HHHHHHHHHHHHHHHH</h1>
<%
    System.out.println("Phuc vu request gui toi: " + counter);
    counter++;
%>
</body>
</html>
