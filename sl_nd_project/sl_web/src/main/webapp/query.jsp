<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2022/7/17
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查询</title>
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <%--Bootstrap的CSS文件--%>
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
</head>
<body >
    <form>
        <%--class="jumbotron"--%>
        <div style="text-align: center" >
            <br>
            <br>
            <font face="STZhongsong" size="16">欢迎来到商品销量查询系统！</font><br>
            <br>
            <font face="STXingkai" color="#d2691e" size="5">在这里你可以查询到2021年某件商品的销量以及2021年超市营收情况</font>
            <hr class="my-4">
            <font face="STXingkai" size="5">在下方编辑框中输入商品ID即可点击查询</font><br>
            <br>
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/>
            </svg>
        </div>
            <br>
            <br>
        <div style="text-align: center" class="form-group">
            <label for="pName">查询商品一年销量情况</label>
            <input style="width: 30%;margin-left: 35%" type="text" class="form-control"  id="pName" placeholder="请输入商品ID编号"><br>
            <button style="width: 100px" type="button" class="btn btn-success" onclick="queryData()">查询</button>
        </div>
        <br>
        <br>
        <div style="text-align: center" class="form-group">
            <label>点此查询超市一年营业额：</label>
            <button style="width: 100px" type="button" class="btn btn-info" onclick="queryDataallyear()">查询</button>
        </div>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
        <div class="alert alert-success" role="alter" style="text-align: center">
            由中软大数据实习第一小组开发
        </div>
    </form>
    <script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        function queryData() {
            location.href="${path}/record/view.do?pName="+$("#pName").val();
        }
    </script>
    <script type="text/javascript">
        function queryDataallyear() {
            location.href="${path}/record/viewallyear.do";
        }
    </script>
</body>
</html>
