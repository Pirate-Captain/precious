<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title> Async Servlet 3.0 </title>
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
    function loadpage() {
        $.get("asyncsevlt.do", function (data) {
            alert(data);
        });
    }
</script>