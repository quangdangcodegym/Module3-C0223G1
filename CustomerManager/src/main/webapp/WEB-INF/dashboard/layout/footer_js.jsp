<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Vendor js -->
<script src="assets\js\vendor.min.js"></script>

<c:if test="${param.page == 'list'}">
    <script src="/dashboard/assets\libs\morris-js\morris.min.js"></script>
    <script src="/dashboard/assets\libs\raphael\raphael.min.js"></script>

    <script src="/dashboard/assets\js\pages\dashboard.init.js"></script>
</c:if>

<!-- App js -->
<script src="/dashboard/assets\js\app.min.js"></script>