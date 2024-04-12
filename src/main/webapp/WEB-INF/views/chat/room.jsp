<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <chalKagTags:webCss/>
</head>
<body class="skin-orange">

<chalKagTags:webJs/>

<chalKagTags:webHeader/>

<section class="login first grey">
    <div class="container">
        <div class="box-wrapper">
            <div class="col-6">
                <h1>${room.name}</h1>
            </div>
            <div>
                <div id="msgArea" class="col"></div>
                <div class="col-6">
                    <div class="input-group mb-3">
                        <input type="text" id="msg" class="form-control">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" id="button-send">전송</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>


<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script>
    $(document).ready(function () {

        console.log("로그 1");

        var roomName = "${room.name}";
        var roomId = "${room.roomId}";
        var username = "${sessionScope.get('member')}";

        console.log(roomName + ", " + roomId + ", " + username);

        var sockJs = new SockJS("/stomp/chat");
        //1. SockJS를 내부에 들고있는 stomp를 내어줌
        var stomp = Stomp.over(sockJs);

        console.log("로그 2");

        //2. connection이 맺어지면 실행
        stomp.connect({}, function () {
            console.log("STOMP Connection")

            //4. subscribe(path, callback)으로 메세지를 받을 수 있음
            stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
                var content = JSON.parse(chat.body);
                var message = content.message;
                var writer = content.writer;
                var str = '';

                console.log(writer);
                console.log(username);
                console.log(message);

                if (writer === username) {
                    console.log("로그 3");
                    str = "<div class='col-6'>";
                    str += "<div class='alert alert-secondary'>";
                    str += "<b>" + writer + " : " + message + "</b>";
                    str += "</div></div>";
                    $("#msgArea").append(str);
                    console.log("로그 4");
                } else {
                    str = "<div class='col-6'>";
                    str += "<div class='alert alert-warning'>";
                    str += "<b>" + writer + " : " + message + "</b>";
                    str += "</div></div>";
                    $("#msgArea").append(str);
                }
            });

            //3. send(path, header, message)로 메세지를 보낼 수 있음
            stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId: roomId, writer: username}))
        });
        $("#button-send").on("click", function (e) {
            var msg = document.getElementById("msg");

            console.log(username + ":" + msg.value);
            stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, writer: username}));
            msg.value = '';
        });
    });
</script>

</body>
</html>
