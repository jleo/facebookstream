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
                if(data.length>0)
                    $('#res').append(data+"</br>");
            }
        }

        var location = 'http://localhost:8080/facebookstream/atmosphere/callback';
        $.atmosphere.subscribe(location, callback, $.atmosphere.request = {fallbackTransport: 'long-polling',transport:'websocket'});
    });
</r:script>
<r:layoutResources/>

<div id="res">

</div>
</body>
</html>