<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../../../imgs/logo.gif" >
			<span class="wel_word">我的订单</span>

		<%@include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${sessionScope.orders}" var="orderList">
				<tr>
					<td>${orderList.create_Time}</td>
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
					<td><a href="manager/orderServlet?action=orderDetails&orderId=${orderList.orderId}">查看详情</a></td>
					<td><a href="manager/orderServlet?action=receiveOrder&orderId=${orderList.orderId}">确认收货</a> </td>
				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>