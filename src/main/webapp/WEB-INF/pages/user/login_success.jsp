<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城</title>
    <%@include file="/WEB-INF/pages/common/head.jsp"%>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="<%=basePath%>static/imgs/logo.gif" >

    <%@include file="/WEB-INF/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>

</div>

<%@include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html>