<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2022/7/17
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品2021年销售情况展示</title>
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <%--Bootstrap的CSS文件--%>
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
</head>
<body style="background: url(${path}/imag/wenli4.jpeg)">
<br>
<br>
<br>
    <%--${records}--%>
    <div id="main" style="width: 80%;height: 650px;margin-left: 10%"></div>
    <script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${path}/resource/jquery/echarts.min.js"></script>
    <script type="text/javascript">
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        // prettier-ignore
        let dataAxis = [<c:forEach items="${records}" var="r">
            ${r.month},
            </c:forEach>];
        // prettier-ignore
        let data = [<c:forEach items="${records}" var="r">
                ${r.amount},
            </c:forEach>];
        let yMax = 500;
        let dataShadow = [];
        for (let i = 0; i < data.length; i++) {
            dataShadow.push(yMax);
        }
        option = {
            tooltip: {
                trigger: 'axis',
            },
            legend: {
                data: ['月份', '销售额']
            },
            title: {
                text: '商品2021年销量柱形统计图',
                subtext: '商品名称：'+'${records[0].pname}'
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    saveAsImage: { show: true }
                }
            },
            calculable: true,
            xAxis: {
                type: 'category',
                name:'月份',
                data: dataAxis,
                axisLabel: {
                    color: '#000'
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    formatter: '{value} 月'
                },
                z: 10
            },
            yAxis: {
                name: '销售金额',
                /*axisLine: {
                    show: false
                },*/
                axisTick: {
                    show: false
                },
                axisLabel: {
                    color: '#000'
                },
                axisLabel: {
                    formatter: '{value} 元'
                }
            },
            dataZoom: [
                {
                    type: 'inside'
                }
            ],
            series: [
                {
                    type: 'bar',
                    /*showBackground: true,*/
                    tooltip: {
                        valueFormatter: function (value) {
                            return value + ' 元';
                        }
                    },
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            { offset: 0, color: '#83bff6' },
                            { offset: 0.5, color: '#188df0' },
                            { offset: 1, color: '#188df0' }
                        ])
                    },
                    emphasis: {
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                { offset: 0, color: '#2378f7' },
                                { offset: 0.7, color: '#2378f7' },
                                { offset: 1, color: '#83bff6' }
                            ])
                        }
                    },
                    data: data,
                    markLine: {
                        data: [{ type: 'average', name: 'Avg' }]
                    }
                }
            ]
        };
        // Enable data zoom when user click bar.
        const zoomSize = 6;
        myChart.on('click', function (params) {
            console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
            myChart.dispatchAction({
                type: 'dataZoom',
                startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                endValue:
                    dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
            });
        });

        option && myChart.setOption(option);
    </script>
</body>
</html>
