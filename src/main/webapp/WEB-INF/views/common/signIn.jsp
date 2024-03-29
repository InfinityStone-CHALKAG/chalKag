<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<!DOCTYPE html>
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
                    <h4>Sign In</h4>
                    <!-- 로그인 비동기 -->
                    <div class="form-group">
                        <label class="fw">Email
                            <a href="../findId" class="pull-right">Forgot Email?</a>
                        </label>
                        <input type="email" name="memberId" class="form-control" id="memberId" required>
                    </div>
                    <p id="IDErrMsg"></p>
                    <div class="form-group">
                        <label class="fw">Password
                            <a href="../findPw" class="pull-right">Forgot Password?</a>
                        </label>
                        <input type="password" name="memberPw" class="form-control" id="memberPw" required>
                    </div>
                    <div class="form-group text-right">
                        <button type="button" class="btn btn-primary btn-block" onclick="signIn()">Sign
                            In
                        </button>
                    </div>
                    <div class="form-group text-center">
                        <span class="text-muted">Don't have an account?</span>
                        <a href="signUp">Create one</a>
                    </div>
                    <div class="title-line">
                        or
                    </div>
                    <a href="/oauth2/authorization/google" class="btn btn-social btn-block buffer"><i class="xi-google"></i> Sign In
                        with Google</a>
                    <a href="/oauth2/authorization/kakao" class="btn btn-social btn-block kakao"><i class="xi-kakaotalk"></i> Sign In
                        with Kakao</a>
                    <a href="/oauth2/authorization/naver" class="btn btn-social btn-block naver"><i class="xi-naver"></i> Sign In with
                        Naver</a>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

<!-- JS -->
<chalKagTags:webJs/>
<script src="js/user/signIn.js"></script>

<!-- XEIcons-->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</body>


</html>