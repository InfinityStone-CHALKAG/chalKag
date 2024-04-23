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
            <div class="box box-border">
                <div class="box-body">
                    <div class="line">
                        <div>CHAT ROOMS</div>
                    </div>
                    <c:forEach items="${list}" var="room">
                        <article class="article col-md-12">
                            <div class="inner">
                                <div style="text-align: center;" class="padding">
                                    <h2><a href="/room?roomId=${room.roomId}">${room.name}</a></h2>
                                </div>
                            </div>
                        </article>
                    </c:forEach>
                    <form action="/room" method="post">
                        <input type="text" style="text-align: center; " name="name" class="form-control"
                               placeholder="write chatroom name to create chatroom">
                        <button class="btn btn-primary btn-block" style="margin-top: 10px;">개설하기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

</body>
</html>
