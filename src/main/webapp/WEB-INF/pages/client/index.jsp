<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>

    <%@ include file="/WEB-INF/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
// 给加入购物车按钮绑定单击事件
            $("button.addToCart").click(function () {
                var bookId = $(this).attr("bookId");

                $.ajax({
                    url: "addToCart",
                    method: "GET",
                    data:"bookId="+bookId,//提交数据
                    dataType:"json",//返回json格式
                    success: function (data){
                        alert(data.lastName)
                        $("#totalCount").text("您的购物车中有 "+ data.totalCount+" 件商品");
                        $("#lastName").text(data.lastName);
                    },
                    error: function (data){
                        alert("数据传输失败")
                    }
                });
            });
        });
    </script>
</head>
<body>
<%--<a href="login">点击登录</a>--%>
<div id="header">
    <img class="logo_img" alt="" src="static/imgs/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <%--如果用户没有登陆--%>
        <c:if test="${empty sessionScope.user}">
            <a href="login">登录</a> |
            <a href="ToRegister">注册</a> &nbsp;&nbsp;
        </c:if>

        <%--如果用户已经登录--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
            <a href="manager/myOrder/${sessionScope.user.id}">我的订单</a>
            <a href="logout">注销</a>&nbsp;&nbsp;
        </c:if>

        <a class="cart" href="ToCart/${sessionScope.user.id}">购物车（未完善）</a>
        <a href="ToManager">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <script type="text/javascript">
                $(function (){
                  $("#queryBtn").click(function (){
                     var startPrice = $("#min").val();
                     var endPrice = $("#max").val();
                     location.href="pageByPrice/"+startPrice+"/"+endPrice+"/"+1;
                  });
                })
            </script>

                价格：<input id="min" type="text" name="startPrice" value="${param.startPrice}"> 元 -
                <input id="max" type="text" name="endPrice" value="${param.endPrice}"> 元
                <input id="queryBtn" type="submit" value="查询"/>
        </div>
        <div style="text-align: center">
            <span id="totalCount"></span>
            <div>
                您刚刚将<span style="color: red" id="lastName">${sessionScope.lastName}</span>加入到了购物车中(未完善)
            </div>
        </div>

        <c:forEach items="${requestScope.bookList}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="static/imgs/default.gif"/>
                </div>
                <div class="book_info">

                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
    <%--    静态包含分页条数据的代码--%>
    <%@include file="/WEB-INF/pages/common/page_nav.jsp" %>

</div>

<%@include file="/WEB-INF/pages/common/footer.jsp" %>
</body>
</html>