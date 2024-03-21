<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <chalKagTags:webCss/>
</head>

<body>

<chalKagTags:webHeader/>

<section class="login first grey">
    <div class="container">
        <div class="box-wrapper">
            <div class="box box-border">
                <div class="box-body">
                    <c:if test="${memberPw == null}">
                        <div class="form-group text-center">
                            <h5>Cannnot find your account</h5>
                        </div>
                        <div class="form-group text-center">
                            <a href="signUp">Create one</a>
                        </div>
                    </c:if>
                    <c:if test="${memberPw != null}">
                        <div class="form-group text-center">
                            <h5>${memberPw}</h5>
                        </div>
                        <div class="form-group text-center">
                            <a href="signIn">Sign in</a>
                        </div>
                        <div class="form-group text-center">
                            <a href="main">Go to CHALKAG</a>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

<!-- JS -->
<chalKagTags:webJs/>


</body>
</html>

