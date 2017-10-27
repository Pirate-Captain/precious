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
                <td><input type="button" value="建立连接" id="open"></td>
                <td><input type="text" id="message"></td>
                <td><input type="button" value="发送消息" id="sendMessage"></td>
            </tr>
        </table>
    </body>
</html>
<script type="text/javascript">
    var websocket = null;
    $(function () {
        $("#open").click(function () {
            if ( 'WebSocket' in window ) {
                websocket = new WebSocket("ws://localhost/springmvc/ws.ws");
                websocket.onopen = function (event) {
                    alert("已连接")
                };
                websocket.onmessage = function (event) {
                    alert(event.data);
                };
                websocket.onclose = function (event) {
                    alert("已关闭");
                };
                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = function () {
                    websocket.close();
                }
            }
        });
        $("#sendMessage").click(function () {
            websocket.send($("#message").val());
        });
    });
</script>