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
<body style="background: url(${path}/imag/query2.jpg)">
    <form>
        <%--class="jumbotron"--%>
        <div style="text-align: center" >
            <br>
            <br>
            <font face="STZhongsong" size="16">欢迎来到商品销量查询系统！</font><br>
            <br>
            <font face="STXingkai" color="#d2691e" size="5">在这里你可以查询到2021年某件商品的销量以及2021年超市营收情况</font>
            <hr class="my-4">
            <font face="STXingkai" size="5">在下方编辑框中输入提示内容即可点击查询</font><br>
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
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"  class="bi bi-1-circle" viewBox="0 0 16 16">
                <path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM9.283 4.002V12H7.971V5.338h-.065L6.072 6.656V5.385l1.899-1.383h1.312Z"/>
            </svg>
            <label for="pName">查询商品一年销量情况</label>
            <input style="width: 30%;margin-left: 35%" type="text" class="form-control"  id="pName" placeholder="请输入商品ID编号"><br>
            <button style="width: 100px" type="button" class="btn btn-success" onclick="queryData()">查询</button>
        </div>
            <br>
            <br>
            <div style="text-align: center" class="form-group">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-2-circle" viewBox="0 0 16 16">
                    <path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM6.646 6.24v.07H5.375v-.064c0-1.213.879-2.402 2.637-2.402 1.582 0 2.613.949 2.613 2.215 0 1.002-.6 1.667-1.287 2.43l-.096.107-1.974 2.22v.077h3.498V12H5.422v-.832l2.97-3.293c.434-.475.903-1.008.903-1.705 0-.744-.557-1.236-1.313-1.236-.843 0-1.336.615-1.336 1.306Z"/>
                </svg>
                <label for="pmonth">查询单月各商品销量情况</label>
                <input style="width: 30%;margin-left: 35%" type="text" class="form-control"  id="pmonth" placeholder="请输入月份（1-12阿拉伯数字）"><br>
                <button style="width: 100px" type="button" class="btn btn-success" onclick="queryMonth()">查询</button>
            </div>
            <br>
            <br>
        <div style="text-align: center" class="form-group">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-3-circle" viewBox="0 0 16 16">
                <path d="M7.918 8.414h-.879V7.342h.838c.78 0 1.348-.522 1.342-1.237 0-.709-.563-1.195-1.348-1.195-.79 0-1.312.498-1.348 1.055H5.275c.036-1.137.95-2.115 2.625-2.121 1.594-.012 2.608.885 2.637 2.062.023 1.137-.885 1.776-1.482 1.875v.07c.703.07 1.71.64 1.734 1.917.024 1.459-1.277 2.396-2.93 2.396-1.705 0-2.707-.967-2.754-2.144H6.33c.059.597.68 1.06 1.541 1.066.973.006 1.6-.563 1.588-1.354-.006-.779-.621-1.318-1.541-1.318Z"/>
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0ZM1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8Z"/>
            </svg>
            <label>点此查询超市一年营业额：</label>
            <button style="width: 100px" type="button" class="btn btn-success" onclick="queryDataallyear()">查询</button>
        </div>
            <br>
            <br>
        <div class="alert alert-info" role="alter" style="text-align: center">
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
    <script type="text/javascript">
        function queryMonth() {
            location.href="${path}/record/viewmonth.do?pmonth="+$("#pmonth").val();
        }
    </script>
</body>
</html>
