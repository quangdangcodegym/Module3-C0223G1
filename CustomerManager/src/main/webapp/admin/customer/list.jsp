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
        <div class="row justify-content-end mt-4" >
            <form class="col-6 d-flex" method="get">
                <input type="text" name="kw" class="form-control mr-2" value="${pageable.getKw()}">
                <select class="form-control mr-2" name="customertype">
                    <option value="-1">All</option>
                    <c:forEach items="${requestScope.customerTypes}" var="ct">
                        <option
                                <c:if test="${ct.getId() == pageable.getCustomerType()}">selected</c:if>
                                value="${ct.getId()}">${ct.getName()}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-primary" >Search</button>
            </form>
        </div>
        <form method="post" id="frmHiden" action="/customers?action=delete">
            <input type="hidden" id="txtIdEdit" name="idEdit"  />
        </form>
        <table border="1" class="table table-hover">
            <tr>
                <td>Name <a><i <c:if test="${pageable.getSortField() == 'name'}">style="color: red"</c:if>
                               onclick="handleSort('name','${pageable.getOrder()}', '${pageable.getKw()}', ${pageable.getCustomerType()} )"
                               class="bi bi-sort-alpha-down"></i></a></td>
                <td>Email <a><i <c:if test="${pageable.getSortField() == 'email'}">style="color: red"</c:if>
                                onclick="handleSort('email','${pageable.getOrder()}', '${pageable.getKw()}', ${pageable.getCustomerType()} )"
                                class="bi bi-sort-alpha-down"></i></a></td>
                <td>Address <a><i class="bi bi-sort-alpha-down"></i></a></td>
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

        <div class="d-flex justify-content-end">
            <ul class="pagination">
                <c:if test="${pageable.getPage() > 1}">
                    <li class="page-item"><a class="page-link" href="/customers?kw=${pageable.getKw()}&page=${pageable.getPage()-1}&customertype=${pageable.getCustomerType()}">Previous</a></li>
                </c:if>
                <c:forEach begin="1" end="${pageable.getTotalPage()}" var="p">
                    <c:choose>
                        <c:when test="${p==pageable.getPage()}">
                            <li class="page-item active"><a class="page-link" href="#">${p}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/customers?kw=${pageable.getKw()}&page=${p}&customertype=${pageable.getCustomerType()}">${p}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageable.getPage() < pageable.getTotalPage()}">
                    <li class="page-item"><a class="page-link" href="/customers?kw=${pageable.getKw()}&page=${pageable.getPage()+1}&customertype=${pageable.getCustomerType()}">Next</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <script>
        function handleDelete(id, name){
            document.getElementById("txtIdEdit").value = id;
            let cf = confirm("Ban co muon xoa " + name);
            if(cf){
                document.getElementById("frmHiden").submit();
            }
        }
        function  handleSort(sortfield, order, kw,customertype){
            let newOrder = order == 'asc' ? 'desc' : 'asc';
            let url = '/customers?sortfield=' + sortfield +'&order=' + newOrder + '&kw=' + kw + '&customertype=' + customertype;
            console.log(url);
            window.location.assign(url);
        }
    </script>
    <jsp:include page="/admin/layout/js_footer.jsp"></jsp:include>
</body>
</html>
