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
            <div>
                <ul>
                    <c:forEach items="${list}" var="room">
                        <li><a href="/room?roomId=${room.roomId}">${room.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <form action="/room" method="post">
            <input type="text" name="name" class="form-control">
            <button class="btn btn-secondary">개설하기</button>
        </form>
    </div>
</section>

<chalKagTags:webFooter/>

</body>
</html>
