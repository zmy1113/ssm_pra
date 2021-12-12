<%--
  Created by IntelliJ IDEA.
  User: zbx34
  Date: 2021/8/18
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
    <a href="manager/myOrder/${sessionScope.user.id}">我的订单</a>
    <a href="logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
