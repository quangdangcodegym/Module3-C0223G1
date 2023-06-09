<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/6/2023
  Time: 11:20 AM
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
        <form method="post" id="frmHiden" action="/customers?action=delete">
            <input type="hidden" id="txtIdEdit" name="idEdit"  />
        </form>
        <table border="1" class="table table-hover">
            <tr>
                <td>Name</td>
                <td>Email</td>
                <td>Address</td>
                <td>Type</td>
                <td>Action</td>
            </tr>

            <c:forEach items="${requestScope.customers}" var="c">
                <tr>
                    <td>${c.getName()}</td>
                    <td>${c.getEmail()}</td>
                    <td>${c.getAddress()}</td>
                    <td>
                            <%--                    <c:forEach items="${requestScope.customerTypes}" var="ct">--%>
                            <%--                        <c:if test="${c.getTypeId() == ct.getId()}">--%>
                            <%--                            ${ct.getName()}--%>
                            <%--                        </c:if>--%>
                            <%--                    </c:forEach>--%>
                            ${c.getCustomerType().getName()}
                    </td>
                    <td>
                        <a href="/customers?action=edit&id=${c.getId()}"><i class="fa-solid fa-pen-to-square"></i></a>
                        <a href="/?action=edit" ><i class="fa-solid fa-trash"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script>
        function handleDelete(id, name){
            document.getElementById("txtIdEdit").value = id;
            let cf = confirm("Ban co muon xoa " + name);
            if(cf){
                document.getElementById("frmHiden").submit();
            }
        }
    </script>
    <jsp:include page="/admin/layout/js_footer.jsp"></jsp:include>
</body>
</html>
