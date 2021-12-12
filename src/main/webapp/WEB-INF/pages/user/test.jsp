<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zbx34
  Date: 2021/8/30
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/pages/common/head.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.books.pageNum>1}">
        <a href="${requestScope.url}/1/4">首页</a>
        <a href="${requestScope.url}/${requestScope.books.pageNum-1}">上一页</a>
    </c:if>

    <%--分页条页码输出的开始--%>

    <c:choose>
        <%--            总页数小于等于5的情况--%>
        <c:when test="${requestScope.books.pages<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.books.pages}"/>
        </c:when>

        <%--            总页数大于5的情况--%>
        <c:when test="${requestScope.books.pages>5}">
            <c:choose>
                <%--                    1.输出前三个页码时--%>
                <c:when test="${requestScope.books.pageNum<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>

                <%--                    2.输出后三个页码时--%>
                <c:when test="${requestScope.books.pageNum>requestScope.books.pages-3}">
                    <c:set var="begin" value="${requestScope.books.pages-4}"/>
                    <c:set var="end" value="${requestScope.books.pages}"/>
                </c:when>

                <%--                    输出中间的页码时--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.books.pageNum-2}"/>
                    <c:set var="begin" value="${requestScope.books.pageNum+2}"/>
                </c:otherwise>
            </c:choose>

        </c:when>
    </c:choose>


    <%--        通过给begin和end赋值，循环输出--%>
    <c:forEach begin="1" end="5" var="i">
        <c:if test="${requestScope.books.pageNum==i}">
            【${i}】
        </c:if>
        <c:if test="${requestScope.books.pageNum!=i}">
            <a href="${requestScope.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--分页条页码输出的结束--%>
    <c:if test="${requestScope.books.pageNum<requestScope.books.total}">
        <a href="${requestScope.url}&pageNo=${ requestScope.books.pageNum +1}">下一页</a>
        <a href="${requestScope. url}&pageNo=${requestScope.books.total }">末页</a>
    </c:if>

    共${ requestScope.books.pages }页，${ requestScope.books.total }条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageNoBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function (){
            $("#searchPageNoBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "http://localhost:8080/Book/${requestScope.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
