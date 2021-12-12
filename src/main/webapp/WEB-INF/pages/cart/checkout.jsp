<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算页面</title>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("input.wechat").click(function () {
                location.href = "static/img/wechat.png";
            });

            $("input.zfb").click(function () {
                location.href = "static/img/zfb.jpg";
            });
        });
    </script>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../../../imgs/logo.gif">
    <span class="wel_word">结算</span>

    <%@include file="/pages/common/login_success_menu.jsp" %>

</div>

<div id="main">
    <table>
        <tr>
            <td><h1>你的订单已结算，订单号为${sessionScope.orderId}</h1></td>

            <td>
                <h2>请选择支付方式</h2>
                <input class="wechat" type="radio" name="method" value="微信支付"/>微信支付
                <input class="zfb" type="radio" name="method" value="支付宝"/>支付宝支付
            </td>
        </tr>
    </table>

</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>