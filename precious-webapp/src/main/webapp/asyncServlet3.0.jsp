<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title> Async Servlet 3.0</title>
        <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    </head>
    <body>
    <table>
        <tr>
            <th>姓名</th>
            <td id="xm"></td>
        </tr>
        <tr>
            <th>性别</th>
            <td id="sex"></td>
        </tr>
        <tr>
            <th>年龄</th>
            <td id="age"></td>
        </tr>
    </table>
    <a href="<%=request.getContextPath() %>/book/new">创建新图书</a>
    </body>
    <script type="text/javascript">
        loadpage();

        function loadpage() {
            $.get("asyncsevlt.do", function (data) {
                $("#xm").html(data.xm);
                $("#sex").html(data.sex);
                $("#age").html(data.age);
            });
        }
    </script>
</html>
