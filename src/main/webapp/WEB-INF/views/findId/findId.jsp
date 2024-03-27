<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
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
                                <h4>Forgot Email</h4>
                                <form id="findId" method="post" action="findId" onsubmit="return validateForm()">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input type="text" id="memberName" name="memberName" class="form-control">
                                    </div>
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
                                        <button class="btn btn-primary btn-block">Find ID</button>
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


                function validateForm() {
                    var memberName = document.getElementById("memberName").value;
                    if (memberName.trim() === "") {
                        console.log(memberName);
                        console.log(memberName.trim());

                        // SweetAlert으로 실패 메시지 표시
                        swal("fail", "이름을 확인해주세요.", "error", {
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