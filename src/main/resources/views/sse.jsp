<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MVC-STUDY</title>
</head>
<body>
    <pre>
        sse
    </pre>
    <div id="msgFromPush">
    </div>
<script type="text/javascript" src="http://47.107.243.217/statics/js/jquery.min.js"></script>
<script type="text/javascript">
    if(!!window.EventSource){
        var source=new EventSource("push");
        s='';
        source.addEventListener('message',function (e) {
            s+=e.data+"</br>";
            $("#msgFromPush").html(s);
        });

        source.addEventListener('open',function (e) {
            console.log("链接打开");
        },false);

        source.addEventListener('error',function (e) {
            console.log(e);
            if(e.readyState ==EventSource.CLOSED){
                console.log("连接关闭");
            }else {
                console.log(e.readyState);
                console.log("连接错误");
            }
        },false);
    }
</script>
</body>
</html>