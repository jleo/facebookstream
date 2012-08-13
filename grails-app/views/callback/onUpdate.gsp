<%--
  Created by IntelliJ IDEA.
  User: jleo
  Date: 12-8-12
  Time: 下午1:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Facebook Callback</title>
    <r:require module="jquery"></r:require>
    <r:layoutResources/>
    <atmosphere:resources/>

</head>

<body>
<r:script>
    $(function () {
        function callback(response) {
            if (response.status == 200) {
                var data = response.responseBody;
                alert(data);
            }
        }

        if (!window.WebSocket)
            alert("WebSocket not supported by this browser");

        var location = 'http://localhost:8080/facebookstream/atmosphere/callback';
        $.atmosphere.subscribe(location, callback, $.atmosphere.request = {transport:'websocket'});
    });
</r:script>
<r:layoutResources/>
aaa b
</body>
</html>