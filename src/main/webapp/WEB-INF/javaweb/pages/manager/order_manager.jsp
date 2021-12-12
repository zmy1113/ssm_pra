<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/head.jsp"%>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="../../../../imgs/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>

	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>商品状态</td>
				<td>发货</td>

			</tr>
			<c:forEach items="${sessionScope.orderList.items}" var="orderList">
				<tr>

					<td>${orderList.create_Time}</td>
					<td>${orderList.price}</td>
					<td><a href="manager/orderServlet?action=orderDetails&orderId=${orderList.orderId}">查看详情</a></td>
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
					<td><a href="manager/orderServlet?action=sendOrder&orderId=${orderList.orderId}">点击发货</a></td>
				</tr>
			</c:forEach>


		</table>
		<div id="page_nav">
			<c:if test="${sessionScope.orderList.pageNo>1}">
				<a href="${sessionScope.orderList.url}&pageNo=1">首页</a>
				<a href="${sessionScope.orderList.url}&pageNo=${sessionScope.orderList.pageNo-1}">上一页</a>
			</c:if>

			<%--分页条页码输出的开始--%>

			<c:choose>
				<%--            总页数小于等于5的情况--%>
				<c:when test="${sessionScope.orderList.pageTotal<=5}">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${sessionScope.orderList.pageTotal}"/>
				</c:when>

				<%--            总页数大于5的情况--%>
				<c:when test="${sessionScope.orderList.pageTotal>5}">
					<c:choose>
						<%--                    1.输出前三个页码时--%>
						<c:when test="${sessionScope.orderList.pageNo<=3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>

						<%--                    2.输出后三个页码时--%>
						<c:when test="${sessionScope.orderList.pageNo>sessionScope.orderList.pageTotal-3}">
							<c:set var="begin" value="${sessionScope.orderList.pageTotal-4}"/>
							<c:set var="end" value="${sessionScope.orderList.pageTotal}"/>
						</c:when>

						<%--                    输出中间的页码时--%>
						<c:otherwise>
							<c:set var="begin" value="${sessionScope.orderList.pageNo-2}"/>
							<c:set var="begin" value="${sessionScope.orderList.pageNo+2}"/>
						</c:otherwise>
					</c:choose>

				</c:when>
			</c:choose>

			<%--        通过给begin和end赋值，循环输出--%>
			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${sessionScope.orderList.pageNo==i}">
					【${i}】
				</c:if>
				<c:if test="${sessionScope.orderList.pageNo!=i}">
					<a href="${sessionScope.orderList.url}&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>

			<%--分页条页码输出的结束--%>
			<c:if test="${sessionScope.orderList.pageNo<sessionScope.orderList.pageTotal}">
				<a href="${sessionScope.orderList.url}&pageNo=${ sessionScope.orderList.pageNo +1}">下一页</a>
				<a href="${sessionScope.orderList.url}&pageNo=${sessionScope.orderList.pageTotal}">末页</a>
			</c:if>

			共${ sessionScope.orderList.pageTotal }页，${ sessionScope.orderList.pageTotalCount }条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageNoBtn" type="button" value="确定">

			<script type="text/javascript">
				$(function (){
					$("#searchPageNoBtn").click(function () {
						var pageNo = $("#pn_input").val();
						location.href = "http://localhost:8080/Book/${sessionScope.orderList.url}&pageNo=" + pageNo;
					});
				});
			</script>
		</div>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>