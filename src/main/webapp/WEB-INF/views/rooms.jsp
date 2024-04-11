<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<div class="container">
    <div>
        <ul>
            <c:forEach items="${list}" var="room">
                <li><a href="<c:url value='/room/${room.roomId}' />">${room.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<form action="<c:url value='/room' />" method="post">
    <input type="text" name="name" class="form-control">
    <button class="btn btn-secondary">개설하기</button>
</form>

<script>
    $(document).ready(function () {

        console.log("로그 1")

        var roomName = [[${roomName}]];

        console.log("로그 2")

        if (roomName != null) {
            alert(roomName + "방이 개설되었습니다.");
        }

        $(".btn btn-secondary").on("click", function (e) {
            e.preventDefault();

            var name = $("input[name='name']").val();

            if (name == "") {
                alert("Please write the name.")
            } else {
                $("form").submit();
            }
        });

    });
</script>

</body>
</html>
