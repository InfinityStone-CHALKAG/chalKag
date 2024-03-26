<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
        <!DOCTYPE html>
        <html>

        <head>
            <chalKagTags:webCss />

            <style>
                #previewDiv {
                    border-radius: 50%;
                    border: 1px solid gray;
                }
            </style>
        </head>

        <body>

            <chalKagTags:webHeader />

            <section class="login first grey">
                <div class="container">
                    <div class="box-wrapper">
                        <div class="box box-border">
                            <div class="box-body">
                                <h4>Register</h4>

                                <form id="yourFormId" method="post" action="signUp" enctype="multipart/form-data" onsubmit="return validateForm()">

                                    <!-- 프로필 이미지 등록 -->
                                    <div class="featured-author-center">
                                        <div class="actions" id="uploadDiv">
                                            <!-- 프로필 이미지 미리보기 -->
                                            <div style="display: flex; justify-content: center; align-items: center;">
                                                <div class="actions" id="previewDiv"
                                                    style="border: 1px solid rgb(168, 168, 168); width: 20rem; height: 20rem; overflow: hidden;">
                                                    <img id="preview"
                                                        style="width: 100%; height: 100%; object-fit: cover;"
                                                        src="profileImg/default.png" />
                                                </div>
                                            </div>
                                            <input type="file" name="file" id="fileInput" style="display: none;" />
                                            <div class="form-group">
                                                <button class="btn btn-primary btn-block" id="uploadButton"
                                                    style="margin-top: 10px;">UPLOAD
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                    <br>

                                    <!-- 회원 ID 입력 -->
                                    <div class="form-group">
                                        <label>Email</label>
                                        <div style="display: flex;">
                                            <input type="email" id="memberId" name="memberId" class="form-control"> <a
                                                style="text-align: center; padding-top: 3%; width: 7rem;"
                                                class="btn btn-magz btn-sm" onclick="checkId()">check</a>
                                        </div>
                                        <p id="IDErrMsg"></p>
                                    </div>
                                    <!-- 회원 PW 입력 -->
                                    <div class="form-group">
                                        <label class="fw">Password</label> <input type="password" id="memberPw"
                                            name="memberPw" class="form-control" placeholder="8자 이상 영문, 숫자, 특수문자포함">
                                        <p id="pwError" class="error"></p>
                                    </div>

                                    <!-- 회원 PW 체크 -->
                                    <div class="form-group">
                                        <label class="fw">Password Check</label> <input type="password"
                                            id="passwordCheck" name="passwordCheck" class="form-control">

                                    </div>
                                    <!-- 회원 이름 입력 -->
                                    <div class="form-group">
                                        <label>Name</label> <input type="text" id="memberName" name="memberName"
                                            class="form-control">
                                    </div>
                                    <!-- 회원 별칭 입력 -->
                                    <div class="form-group">
                                        <label>NickName</label>
                                        <div style="display: flex;">
                                            <input type="text" id="memberNickname" name="memberNickname"
                                                class="form-control"> <a
                                                style="text-align: center; padding-top: 3%; width: 7rem;"
                                                class="btn btn-magz btn-sm" onclick="checkNickname()">check</a>
                                        </div>
                                        <p id="nicknameErrMsg"></p>
                                    </div>
                                    <!-- 회원 생일 체크 -->
                                    <div class="form-group">
                                        <label>Birth</label> <input type="date" name="memberBirth" class="form-control"
                                            value="1990-01-01">
                                    </div>
                                    <!-- 회원 폰번호 입력 -->
                                    <div class="form-group">
                                        <label>Phone Number</label>
                                        <div style="display: flex;">
                                            <input type="text" id="memberPh" name="memberPh" class="form-control"
                                                placeholder="-빼고 입력" maxlength="11"
                                                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                            <a id="phCheckBtn" style="text-align: center; padding-top: 3%; width: 7rem;"
                                                class="btn btn-magz btn-sm" onclick="checkPh()">send</a>
                                        </div>
                                        <p id="phErrMsg" class="error"></p>
                                        <br>
                                        <div id="memberPhCheckContainer" style="display: flex; display: none;">
                                            <!-- 동적으로 인증번호 입력란과 확인 버튼 생성-->
                                        </div>
                                        <p class="successPhCheck"></p>

                                        <!-- <span id="phErrMsg" class="error"></span> -->
                                    </div>

                                    <!-- 회원 성별 체크 -->
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <div class="form-group text-center">
                                            <label style="margin-right: 10%"><input type="radio" name="memberGender"
                                                    value="male">
                                                Male</label>
                                            <label style="margin-left: 5%"><input type="radio" name="memberGender"
                                                    value="female">
                                                Female</label>
                                        </div>
                                    </div>
                                    <!-- 회원 자기 소개말 입력  -->
                                    <div class="form-group">
                                        <label>Introduction</label>
                                        <textarea id="memberIntroduction" name="memberIntroduction" class="form-control"
                                            style="height: 130px; resize: none;"></textarea>
                                    </div>

                                    <!-- 약관동의 -->
                                    <div class="form-group">
                                        <div style="border: 0.5px solid #E2E2E2; overflow: auto; height: 200px;">
                                            <p>
                                                1. 위치 정보의 수집 및 이용 목적<br>
                                                1.1 회사는 위치 기반 서비스를 제공하기 위해 위치 정보를 수집 및 이용합니다.<br>
                                                1.2 위치 정보는 다음과 같은 목적으로 수집 및 이용됩니다.<br>
                                                - 서비스 제공을 위한 위치 확인<br>
                                                - 사용자 위치를 활용한 맞춤형 서비스 제공<br>
                                                - 서비스 이용 통계 및 분석 자료 생성<br>
                                                - 위치 정보를 활용한 광고 서비스 제공<br><br>

                                                2. 수집하는 위치 정보 항목<br>
                                                2.1 회사가 수집하는 위치 정보의 항목은 다음과 같습니다.<br>
                                                - 현재 위치 또는 특정 지역의 위도, 경도 정보<br>
                                                - 기기 정보: 디바이스 ID, 디바이스 모델, 운영 체제 버전 등<br><br>

                                                3. 위치 정보 제3자 제공 및 파기<br>
                                                3.1 회사는 원칙적으로 위치 정보를 제3자에게 제공하지 않습니다. 다만, 다음의 경우에는 제3자 제공이 가능합니다.<br>
                                                - 이용자 동의를 받은 경우<br>
                                                - 법령에 따라 제공이 요구되는 경우<br>
                                                3.2 위치 정보는 이용자가 해당 서비스를 종료하거나 로그아웃한 시점부터 지체 없이 파기됩니다.<br><br>

                                                4. 위치 정보의 보유 및 이용 기간<br>
                                                4.1 위치 정보는 위치 기반 서비스 제공 목적을 달성한 후에는 지체 없이 파기됩니다. 단, 관련 법규에 따라 일정 기간 동안
                                                보관할 필요가 있는 경우에는 해당 기간 동안 보관됩니다.<br><br>

                                                5. 이용자의 권리<br>
                                                5.1 이용자는 언제든지 위치 정보의 수집 및 이용에 대한 동의 철회 및 위치 정보의 삭제를 요청할 수 있습니다.<br><br>

                                                6. 개인정보 보호책임자<br>
                                                6.1 회사는 위치 정보를 보호하고 개인정보와 관련된 불만 처리 및 피해 구제 등을 위하여 아래와 같이 개인정보 보호책임자를
                                                지정하고 있습니다.<br>
                                                - 성명: [보호책임자의 성명]<br>
                                                - 이메일: [보호책임자의 이메일]<br>
                                                - 전화번호: [보호책임자의 전화번호]<br><br>

                                                7. 개정 및 공지<br>
                                                7.1 이 위치 기반 서비스 이용약관은 정부의 법령, 정책 또는 회사의 내부 운영정책 등의 변경에 따라 변경될 수 있습니다. 변경
                                                사항에 대해서는 홈페이지를 통해 사전에 공지할 것입니다.
                                            </p>
                                        </div>
                                    </div>
                                    <!-- 약관 동의 체크 동의 후 이용가능 -->
                                    <div class="form-group text-center">
                                        <div>
                                            <label><input type="checkbox" id="agreeTerms1" name="agreeTerms1"> I accept
                                                terms of use</label>
                                        </div>
                                    </div>

                                    <!-- 회원 가입 버튼 -->
                                    <div class="form-group">
                                        <button class="btn btn-primary btn-block" id="joinButton">JOIN</button>
                                    </div>
                                    <!-- 로그인 페이지로 이동 -->
                                    <div class="form-group text-center">
                                        <span class="text-muted">Already have an account?</span> <a href="signIn">Sign
                                            In</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <chalKagTags:webFooter />

            <!-- JS -->
            <chalKagTags:webJs />


            <script src="js/user/signUp.js"></script>
            <script src="js/user/checkId.js"></script>
            <script src="js/user/checkPh.js"></script>
            <script src="js/user/checkNickname.js"></script>
            <script src="js/user/smsCheck.js"></script>
            <script src="js/user/sendAuthentication.js"></script>
            <script src="js/user/pwCheck.js"></script>
            <script src="js/user/joinValidation.js"></script>
            <script src="js/user/ignoreSpacebar.js"></script>
        </body>

        </html>