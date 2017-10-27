<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title> Async Servlet 3.0 </title>
        <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    </head>
    <body>
        <table>
            <tr>
                <th> 姓名</th>
                <td id="xm"></td>
            </tr>
            <tr>
                <th> 性别</th>
                <td id="sex"></td>
            </tr>
            <tr>
                <th> 年龄</th>
                <td id="age"></td>
            </tr>
        </table>
        <a href="<%=request.getContextPath() %>/book/new"> 创建新图书 </a>
    </body>
</html>
<script type="text/javascript">
    function requestInfo() {
        if ( !!window.EventSource ) {
            var source = new EventSource("sse.do");
            source.onopen  = function(e){
            };
            source.onmessage = function (e) {
                var data = JSON.parse(e.data);
                $("#xm").html(data.xm);
                $("#sex").html(data.sex);
                $("#age").html(data.age);
            };
        } else {
            alert("不支持sse")
        }
    }
    window.addEventListener("load", requestInfo);
</script>