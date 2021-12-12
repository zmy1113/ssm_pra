<%--
  Created by IntelliJ IDEA.
  User: zbx34
  Date: 2021/10/16
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<%@include file="/WEB-INF/pages/common/head.jsp" %>
<script type="text/javascript">
    // 页面加载完成之后
    $(function () {
        //给图形验证码绑定单击事件
        $("#code_img").click(function () {
            this.src = "<%=basePath %>kaptcha.jpg?d=" + new Date();
        });
        //给注册绑定单击事件
        $("#sub_btn").click(function () {
            // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
            //1 获取用户名输入框里的内容
            var usernameText = $("#username").val();
            //2 创建正则表达式对象
            var usernamePatt = /^\w{5,12}$/;
            //3 使用test方法验证
            if (!usernamePatt.test(usernameText)) {
                //4 提示用户结果
                $("span.errorMsg").text("用户名不合法");
                return false;
            }

            // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
            //1 获取用户名输入框里的内容
            var passwordText = $("#password").val();
            //2 创建正则表达式对象
            var passwordPatt = /^\w{5,12}$/;
            //3 使用test方法验证
            if (!passwordPatt.test(passwordText)) {
                //4 提示用户结果
                $("span.errorMsg").text("密码不合法！");

                return false;
            }

            // 验证确认密码：和密码相同
            //1 获取确认密码内容
            var repwdText = $("#repwd").val();
            //2 和密码相比较
            if (repwdText != passwordText) {
                //3 提示用户
                $("span.errorMsg").text("确认密码和密码不一致！");

                return false;
            }

            // 邮箱验证：xxxxx@xxx.com
            //1 获取邮箱里的内容
            var emailText = $("#email").val();
            //2 创建正则表达式对象
            var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
            //3 使用test方法验证是否合法
            if (!emailPatt.test(emailText)) {
                //4 提示用户
                $("span.errorMsg").text("邮箱格式不合法！");
                return false;
            }
            // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
            var codeText = $("#code").val();

            //去掉验证码前后空格
            // alert("去空格前：["+codeText+"]")
            codeText = $.trim(codeText);
            // alert("去空格后：["+codeText+"]")

            if (codeText == null || codeText == "") {
                //4 提示用户
                $("span.errorMsg").text("验证码不能为空！");
                return false;
            }
            // 去掉错误信息
            $("span.errorMsg").text("");
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $("#username-label").blur(function () {
            var username = $("#username-label").val();
            // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
            //1 获取用户名输入框里的内容
            //2 创建正则表达式对象
            var usernamePatt = /^\w{5,12}$/;
            //3 使用test方法验证
            if (!usernamePatt.test(username)) {
                //4 提示用户结果
                $("span.errorMsg").text("用户名不合法");
            }
            $.ajax({
                url: "ajax/register",
                method: "GET",
                data:"username="+username,//提交数据
                dataType:"json",//返回json格式
                success: function (data){
                    if(data.existUsername){
                        $("span.errorMsg").text("用户名已存在");
                    }else {
                        $("span.errorMsg").text("用户名可用");
                    }
                },
                error: function (data){
                    alert("数据传输失败")
                }
                });
        });

        $("#password-label").blur(function () {
            //1 获取密码输入框里的内容
            var password = this.value;
            //2 创建正则表达式对象
            var passwordPatt = /^\w{5,12}$/;
            //3 使用test方法验证
            if (!passwordPatt.test(password)) {
                //4 提示用户结果
                $("span.errorMsg").text("密码不合法！");
            }
        });

        $("#confirm-password").blur(function () {
            // 验证确认密码：和密码相同
            //1 获取确认密码内容
            var repwdText = this.value;
            var passwordText = $("#password-label").value;
            //2 和密码相比较
            if (repwdText != passwordText) {
                //3 提示用户
                $("span.errorMsg").text("确认密码和密码不一致！");
            }
        });
    });
</script>

<style type="text/css">
    .login_form {
        height: 420px;
        margin-top: 25px;
    }

</style>

<body>
<div id="login_header">
    <img class="logo_img" alt="" src="<%=basePath%>static/imgs/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册书城会员</h1>
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="register" method="post">
                        <label>用户名称：</label>
                        <input id="username-label" class="itxt" type="text" placeholder="请输入用户名"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input id="password-label" class="itxt" type="password" placeholder="请输入密码"
                               value=""
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input id="confirm-password" class="itxt" type="password" placeholder="确认密码"
                               value=""
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input id="email" class="itxt" type="text" placeholder="请输入邮箱地址"
                               value="${requestScope.email}"
                               autocomplete="off" tabindex="1" name="email" id="email"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input id="code" class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px; width: 110px; height: 30px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                        <a href="login">立即登录</a>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/common/footer.jsp" %>
</body>
</html>
