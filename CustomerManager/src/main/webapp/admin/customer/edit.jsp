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
  <jsp:include page="/admin/layout/head_css.jsp"></jsp:include>
</head>
<body>
<div class="container">
  <div class="row d-flex justify-content-center">
    <c:if test="${requestScope.message != null}">
      <script>
        window.onload = function (){
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Thêm thành công',
            showConfirmButton: false,
            timer: 1500
          })
        }
      </script>
    </c:if>
  </div>
  <div class="row d-flex justify-content-center">
    <div class="col-6">

      <form method="post" class="container">
        <fieldset>
          <legend>Edit Customer</legend>
          <c:if test="${requestScope.errors != null}">
            <div class="alert alert-danger">
              <ul>
                <c:forEach items="${errors}" var="e">
                  <li>${e}</li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
          <table>
            <tr>
              <td>Name: </td>
              <td><input type="text" name="name" id="name" value="${requestScope.customer.getName()}" class="form-control"></td>
            </tr>
            <tr>
              <td>Email: </td>
              <td><input type="text" name="email" id="email" value="${requestScope.customer.getEmail()}" class="form-control"></td>
            </tr>
            <tr>
              <td>Address: </td>
              <td><input type="text" name="address" id="address" value="${requestScope.customer.getAddress()}" class="form-control"></td>
            </tr>
            <tr>
              <td>Customer Type: </td>
              <td>
                <select name="customertype" class="form-control">
                  <c:forEach items="${requestScope.customerTypes}" var="ct">
                    <option
                            <c:if test="${ct.getId() == requestScope.customer.getTypeId()}">selected</c:if>
                            value="${ct.getId()}">${ct.getName()}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td></td>
              <td><input type="submit" class="btn btn-primary" value="Edit customer"></td>
            </tr>
          </table>
        </fieldset>
      </form>
    </div>

  </div>
</div>

<jsp:include page="/admin/layout/js_footer.jsp"></jsp:include>
</body>
</html>
