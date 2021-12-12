<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/WEB-INF/pages/common/head.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/imgs/logo.gif">
    <span class="wel_word">我的订单</span>

    <%@include file="/WEB-INF/pages/common/login_success_menu.jsp" %>

</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td id="status">状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${sessionScope.orders}" var="orderList">
            <tr>
                <td>${orderList.createTime}</td>
                <td>${orderList.price}</td>
                <td><c:choose>
                    <c:when test="${orderList.status==0}">
                        未发货
                    </c:when>
                    <c:when test="${orderList.status==1}">
                        已发货
                    </c:when>
                    <c:when test="${orderList.status==2}">
                        已签收
                    </c:when>
                </c:choose></td>
                <td><a href="manager/orderDetails/${orderList.orderId}">查看详情(未完善)</a></td>
                <c:if test="${orderList.status!=2}">
                    <td><a href="manager/receive/${orderList.orderId}" id="receive">确认收货（未完善)</a></td>
                </c:if>

                <script type="text/javascript">
                    $(function () {
                        $("#receive").click(function () {
                            if (${sessionScope.orders!=null}) {
                                alert("确认收货成功")
                            } else {
                                alert("确认收货失败，请稍后重试或联系管理员")
                            }
                        });
                    })
                </script>
            </tr>
        </c:forEach>

    </table>


</div>

<%@include file="/WEB-INF/pages/common/footer.jsp" %>
</body>
</html>