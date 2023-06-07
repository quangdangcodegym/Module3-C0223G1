<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/6/2023
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<c:if test="${requestScope.message != null}">
  <h6>Thêm thành công</h6>
</c:if>
<form method="post">
  <fieldset>
    <legend>Edit information</legend>
    <table>
      <tr>
        <td>Name: </td>
        <td><input type="text" name="name" id="name" value="${requestScope.customer.getName()}"></td>
      </tr>
      <tr>
        <td>Email: </td>
        <td><input type="text" name="email" id="email" value="${requestScope.customer.getEmail()}"></td>
      </tr>
      <tr>
        <td>Address: </td>
        <td><input type="text" name="address" id="address" value="${requestScope.customer.getAddress()}"></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Edit customer"></td>
      </tr>
    </table>
  </fieldset>
</form>
</body>
</html>
