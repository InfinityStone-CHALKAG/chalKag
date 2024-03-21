</html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
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
                    <h4>Forgot Password</h4>
                    <form id="findPw" method="post" action="findPw" onsubmit="return validateForm()">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" id="memberId" name="memberId" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <div style="display: flex;">
                                <input type="text" id="memberPh" name="memberPh" class="form-control"
                                       placeholder="-빼고 입력" maxlength="11"
                                       oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                                       required>
                                <a id="phCheckBtn" style="text-align: center; padding-top: 3%; width: 7rem;"
                                   class="btn btn-magz btn-sm" onclick="sendAuthenticationSMS()">send</a>
                            </div>
                            <p id="phErrMsg" class="error"></p>
                            <br>
                            <div style="display: flex;">
                                <input type="text" id="memberPhCheck" name="memberPhCheck"
                                       class="form-control" placeholder="인증번호 입력" required>
                                <a id="smsCheck" style="text-align: center; padding-top: 3%; width: 7rem;"
                                   class="btn btn-magz btn-sm">check</a>
                            </div>
                            <!-- 결과 확인 비동기 -->
                            <input type="hidden" id="${result}" class="hiddenCheck">
                            <p class="successPhCheck"></p>

                            <!-- <span id="phErrMsg" class="error"></span> -->
                        </div>
                        <div class="form-group text-right">
                            <button class="btn btn-primary btn-block">Find Password</button>
                        </div>
                        <div class="form-group text-center">
                            <span class="text-muted">Back to login?</span> <a href="signIn">Login</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

<!-- JS -->
<chalKagTags:webJs/>
<script src="js/user/checkId.js"></script>
<script src="js/user/smsCheck.js"></script>
<script src="js/user/sendAuthentication.js"></script>
<script src="js/user/ignoreSpacebar.js"></script>

</body>


</html>

</body>
</html>
