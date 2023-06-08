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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <form method="post" id="frmHiden" action="/customers?action=delete">
        <input type="hidden" id="txtIdEdit" name="idEdit"  />
    </form>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Email</td>
            <td>Address</td>
            <td>Action</td>
        </tr>

        <c:forEach items="${requestScope.customers}" var="c">
            <tr>
                <td>${c.getName()}</td>
                <td>${c.getEmail()}</td>
                <td>${c.getAddress()}</td>
                <td>
                    <a href="/customers?action=edit&id=${c.getId()}"><i class="fa-solid fa-pen-to-square"></i></a>
                    <a href="/?action=edit" ><i class="fa-solid fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <script>
        function handleDelete(id, name){
            document.getElementById("txtIdEdit").value = id;
            let cf = confirm("Ban co muon xoa " + name);
            if(cf){
                document.getElementById("frmHiden").submit();
            }
        }
    </script>
</body>
</html>
