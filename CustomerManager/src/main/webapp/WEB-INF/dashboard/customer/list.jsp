<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Dashboard 1 | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
        <jsp:include page="/WEB-INF/dashboard/layout/head_css.jsp" />
    </head>

    <body data-layout="horizontal">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Navigation Bar-->
            <jsp:include page="/WEB-INF/dashboard/layout/top_nav.jsp" />
                <!-- End Navigation Bar-->

            <!-- ============================================================== -->
            <!-- Start Page Content here -->
            <!-- ============================================================== -->

            <div class="content-page">
                <div class="content">

                    <!-- Start Content-->
                    <div class="container-fluid">

                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Zircos</a></li>
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboard </a></li>
                                            <li class="breadcrumb-item active">Dashboard</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Dashboard</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row">
                            <table class="table table-hover">
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
                                            <a href="/customers?action=edit&id=${c.getId()}"><i class="fas fa-edit"></i></a>
                                            <a href="/?action=edit" ><i class="fas fa-trash-alt"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>


                    </div>
                    <!-- end container-fluid -->

                </div>
                <!-- end content -->

                

                <!-- Footer Start -->
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                2018 - 2020 &copy; Zircos theme by <a href="">Coderthemes</a>
                            </div>
                        </div>
                    </div>
                </footer>
                <!-- end Footer -->

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->

        </div>
        <!-- END wrapper -->

        <!-- Right Sidebar -->
        <jsp:include page="/WEB-INF/dashboard/layout/rightbar.jsp" />

        <jsp:include page="/WEB-INF/dashboard/layout/footer_js.jsp">
            <jsp:param name="page" value="list"/>
        </jsp:include>

    </body>

</html>