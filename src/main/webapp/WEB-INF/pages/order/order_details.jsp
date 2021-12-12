<%@include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/WEB-INF/pages/common/head.jsp"%>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="<%=basePath%>static/imgs/logo.gif" >
    <span class="wel_word">订单详情</span>

    <%@include file="/WEB-INF/pages/common/login_success_menu.jsp"%>

</div>

<div id="main">

    <table>
        <tr>
            <td>订单编号</td>
            <td>商品名称</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品总价</td>
            <td>订单号</td>
        </tr>
        <c:forEach items="${sessionScope.orderItem}" var="orderItem">
            <tr>
                <td>${orderItem.id}</td>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
                <td>${orderItem.orderId}</td>
            </tr>
        </c:forEach>

    </table>
</div>
<%@include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html>