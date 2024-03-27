<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
        <!DOCTYPE html>
        <html>

        <head>
            <chalKagTags:webCss />
        </head>

        <body>

            <chalKagTags:webHeader />

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
                                    <p id="IDErrMsg"></p>
                                    <div class="form-group">
                                        <label>Phone Number</label>
                                        <div style="display: flex;">
                                            <input type="text" id="memberPh" name="memberPh" class="form-control"
                                                placeholder="-빼고 입력" maxlength="11"
                                                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                            <a id="phCheckBtn" style="text-align: center; padding-top: 3%; width: 7rem;"
                                                class="btn btn-magz btn-sm" onclick="sendAuthenticationSMS()">send</a>
                                        </div>
                                        <p id="phErrMsg" class="error"></p>
                                        <br>
                                        <div id="memberPhCheckContainer" style="display: flex; display: none;">
                                            <!-- 동적으로 인증번호 입력란과 확인 버튼 생성-->
                                        </div>
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

            <chalKagTags:webFooter />

            <script>
                var checkIdFlag = false;
                // 함수를 사용하면 DOMContentLoaded 대신  다음처럼 사용가능! 입력될떄마다 checkPresentPw()를 호출한다.
                //  document.getElementById("memberPw").addEventListener("input", checkPresentPw);
                document.addEventListener("DOMContentLoaded", function () {
      
                    // @ . 포함 .뒤는 2개에서 4개의 소문자 알파벳
                    var idRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
                    
                    document.getElementById("memberId").addEventListener("input", function () {
                        var memberId = $('#memberId').val();

                        if (memberId == '') {
                            $("#IDErrMsg").text('입력된 이메일이 없습니다.');
                            $("#IDErrMsg").css('color', 'red');
                            return false;
                        }
                        if (!idRegex.test(memberId)) {
                            $("#IDErrMsg").text('옳바른 이메일 형식이 아닙니다.');
                            $("#IDErrMsg").css('color', 'red');
                            return false;
                        } 
                            $("#IDErrMsg").text('');
                        

                        // input 내용이 바뀔 때마다 checkIDflag를 false로 설정 다시인증하기
                        checkIdFlag = true;
                        return checkIdFlag;
                    });
                });




                function validateForm() {
                    if (!checkIdFlag) {
                        swal("fail", "이메일을 확인해주세요.", "error", {
                            button: "OK",
                        });
                        return false;
                    }
                    else if (!checkPhFlag) {
                        swal("fail", "전화번호를 인증해주세요.", "error", {
                            button: "OK",   
                        });
                        return false;
                    }
                    // 모든 검증이 통과했을 경우
                    return true;
                }

            </script>

            <!-- JS -->
            <chalKagTags:webJs />
            <script src="js/user/smsCheck.js"></script>
            <script src="js/user/sendAuthentication.js"></script>
            <script src="js/user/ignoreSpacebar.js"></script>

        </body>


        </html>

        </body>

        </html>