<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2022/7/17
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>超市2021年营售情况展示</title>
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <%--Bootstrap的CSS文件--%>
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
</head>
<body style="background: url(${path}/imag/green.jpeg)">
<div id="main" style="width: 60%;height: 600px;margin-left: 20%"></div>
<div id="main1" style="width: 60%;height: 600px;margin-left: 20%"></div>
<script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${path}/resource/jquery/echarts.min.js"></script>
<%--<img src="${path}/1.jpg" class="img-fluid" alt="...">--%>
<br>
<br>
<script type="text/javascript">
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        title: {
            text: '2021年超市月营业额对比',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        legend: {
            top: 'bottom',
            left: 'center'
        },
        series: [
            {
                name: '月总营业额',
                type: 'pie',
                radius: ['25%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    <c:forEach items="${months}" var="r">
                        {value:${r.sumAmount},name:'${r.month}'+'月'},
                    </c:forEach>
                ]
            }
        ]
    };

    option && myChart.setOption(option);
</script>
<br/>
<br/>

<script type="text/javascript">
    var chartDom1 = document.getElementById('main1');
    var myChart1 = echarts.init(chartDom1);
    var option1;

    option1 = {
        title: {
            text: '不同商品年销售量对比',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: [
                <c:forEach items="${objects}" var="r">
                '${r.pname}',
                </c:forEach>
            ]
        },
        series: [
            {
                name: '年销售额',
                type: 'pie',
                radius: '80%',
                center: ['45%', '55%'],
                data: [<c:forEach items="${objects}" var="r">
                    {name:'${r.pname}',value:${r.sumamount}},
                    </c:forEach>],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    option1 && myChart1.setOption(option1);
</script>
</body>
</html>
