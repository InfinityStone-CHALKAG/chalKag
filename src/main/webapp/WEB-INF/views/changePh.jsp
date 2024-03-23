<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>
            <style>
                /* Chrome, Safari, Edge, Opera */
                /* 경험치 바로 사용 */
                input[type='range']::-webkit-slider-thumb {
                    opacity: 0;
                }

                input[type='range'] {
                    pointer-events: none;
                }

                input[type="range"] {
                    accent-color: #F73F52;
                }

                .featured-author .featured-author-body .featured-author-quote {
                    padding: 4px !important;
                }

                #scroll-container {
                    max-height: 200px;
                    /* 원하는 최대 높이 설정 */
                    overflow-y: auto;
                    /* 세로 스크롤 활성화 */
                    border: 1px solid #ccc;
                    /* 테두리 설정 */
                    padding: 10px;
                    /* 내부 여백 설정 */
                }


                /* ul 태그에 대한 스타일 */
                .changeList {
                    font-size: 16px;
                    list-style: none;
                    /* 리스트의 점을 없앤다 */
                    padding-left: 0;
                    /* 왼쪽 패딩을 제거해 기본적으로 생기는 들여쓰기를 없앤다 */
                    width: 100%;
                    /* 리스트의 너비를 설정한다. 필요에 따라 조정할 수 있습니다. */
                    font-family: 'Lato', sans-serif;
                    margin-top: 20px;
                }

                .changeList li {
                    margin-bottom: 15px;
                    /* 리스트 아이템 사이의 간격을 조정한다. 필요에 따라 조정할 수 있습니다. */
                }

                .changeList li:after {
                    content: '';
                    display: inline-block;
                    width: 100%;
                }

                .changeList a {
                    color: black;
                    /* 링크의 글자색을 검은색으로 변경한다. */
                    text-decoration: none;
                    /* 링크 밑줄을 제거한다. */
                    position: relative;
                    /* 밑줄을 위한 위치 지정 */
                    padding-bottom: 5px;
                    /* 밑줄과의 간격 조정 */
                }

                .changeList a:hover {
                    text-decoration: none;
                    /* 마우스 오버 시 링크의 밑줄을 제거 */
                    color: #F73F52;
                }

                .changeList a.active {
                    text-decoration: none;
                    color: #F73F52;
                    font-weight: bold;
                    /* 글자를 굵게 */
                    border-bottom: 3px solid #F73F52;
                    /* 밑줄 스타일: 두께와 색상 */
                }
            </style>

            <head>
                <chalKagTags:webCss />
            </head>

            <body>

                <chalKagTags:webHeader />

                <section class="myPage">
                    <div class="container">
                        <div class="col-xs-6 col-md-4 sidebar" id="sidebar">
                            <div class="sidebar-title for-tablet">Sidebar</div>
                            <aside>
                                <div class="aside-body">
                                    <div class="featured-author">
                                        <div class="featured-author-inner">
                                            <div class="featured-author-cover"
                                                style="background-image: url('css/user/images/news/img15.jpg');">
                                                <div class="badges">
                                                    <c:if test="${memberInfo.memberGrade eq 'PREMIUM'}">
                                                        <div class="badge-item"><i class="ion-star"></i> PREMIUM</div>
                                                    </c:if>
                                                    <c:if test="${memberInfo.memberGrade eq 'USER'}">
                                                        <div class="badge-item"><i class="ion-star"></i> PREMIUM...할래?
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <div class="featured-author-center">
                                                    <figure class="featured-author-picture">
                                                        <img src="profileImg/${memberInfo.profileImgName}"
                                                            alt="Sample Article">
                                                    </figure>
                                                    <div class="featured-author-info">
                                                        <h2 class="name">${memberInfo.memberNickname}</h2>
                                                        <div class="desc">${memberInfo.memberId}</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="featured-author-body">
                                                <div class="featured-author-count">
                                                    <div class="item" style="width: -webkit-calc(100% / 2);">
                                                        <a href="#">
                                                            <div class="name">Posts</div>
                                                            <div class="value">208</div>
                                                        </a>
                                                    </div>
                                                    <div class="item" style="width: -webkit-calc(100% / 2);">
                                                        <a href="#">
                                                            <div class="name">Score</div>
                                                            <div class="value">${memberInfo.currentScore}</div>
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="featured-author-quote"
                                                    style="font-weight: bold; font-family: 'Lato'; font-size:19px ;">
                                                    LV : ${memberInfo.currentLevel}
                                                </div>
                                                <div style="display: flex; justify-content: center;">
                                                    <input type="range" id="Exp" name="Exp" min="0"
                                                        max="${memberInfo.currentNextExp}"
                                                        value="${memberInfo.currentExp}" style="width: 250px;">
                                                </div>
                                                <div class="featured-author-quote"
                                                    style="font-weight: bold; font-family: 'inherit'; margin-top: 10px;">
                                                    INTRODUCTION</div>
                                                <div class="featured-author-quote" id="Introdudction">
                                                    <c:if test="${memberInfo.memberIntroduction != null}">
                                                        "${memberInfo.memberIntroduction}"
                                                    </c:if>
                                                    <c:if test="${memberInfo.memberIntroduction == null}">
                                                        " Write a self-introduction to showcase yourself "
                                                    </c:if>
                                                </div>


                                                <div class="featured-author-quote">
                                                    <ul class="changeList">
                                                        <li><a href="myPage" class="active">MY INFORMATION</a></li>
                                                        <li><a href="changeInformation">CHANGE INFORMATION</a></li>
                                                        <li><a href="changeNickname">CHANGE NICKNAME</a></li>
                                                        <li><a href="changePw">CHANGE PASSWORD</a></li>
                                                        <li><a href="changePh">CHANGE PHONENUMBER</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </aside>
                        </div>
                        <div class="col-md-5 col-sm-12 col-xs-12">


                            <div class="box box-border">
                                <div class="box-body">
                                    <h4>Change PhoneNumber</h4>
                                    <form id="changePhoneNumber" method="post" action="changePhoneNumber"
                                        onsubmit="return true">
                                        <!-- 회원 폰번호 입력 -->
                                        <div class="form-group">
                                            <label>Present</label>
                                            <div style="display: flex;">
                                                <input type="text" id="memberPh" name="memberPh" class="form-control"
                                                    placeholder="-빼고 입력" maxlength="11"
                                                    oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                                <a id="phCheckBtn"
                                                    style="text-align: center; padding-top: 3%; width: 7rem;"
                                                    class="btn btn-magz btn-sm">send</a>
                                            </div>
                                            <p id="phErrMsg1" class="error"></p>
                                            <br>
                                            <div id="memberPhCheckContainer" style="display: flex; display: none;">
                                                <!-- 동적으로 인증번호 입력란과 확인 버튼 생성-->
                                            </div>
                                            <p class="successPhCheck1"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>New PhoneNumber</label>
                                            <div style="display: flex;">
                                                <input type="text" id="memberPhNew" name="memberPhNew"
                                                    class="form-control" placeholder="-빼고 입력" maxlength="11"
                                                    oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                                <a id="phNewCheckBtn"
                                                    style="text-align: center; padding-top: 3%; width: 7rem;"
                                                    class="btn btn-magz btn-sm">send</a>
                                            </div>
                                            <p id="phErrMsg2" class="error"></p>
                                            <br>
                                            <div id="memberPhNewCheckContainer" style="display: flex; display: none;">
                                                <!-- 동적으로 인증번호 입력란과 확인 버튼 생성-->
                                            </div>
                                            <p class="successPhCheck2"></p>
                                        </div>
                                        <div class="form-group text-right">
                                            <button class="btn btn-primary btn-block">Confirm</button>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    </div>
                </section>

                <chalKagTags:webFooter />

                <!-- JS -->
                <chalKagTags:webJs />

                <!-- XEIcons-->
                <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
            </body>
            <script>

                // 마이페이지 side바 하단 active Js
                document.addEventListener("DOMContentLoaded", function () {
                    // 현재 페이지의 URL을 가져옵니다.
                    var currentPage = window.location.href;

                    // 모든 메뉴 항목을 찾습니다.
                    var menuItems = document.querySelectorAll('li a');

                    // 각 메뉴 항목을 순회하면서
                    menuItems.forEach(function (item) {
                        // 만약 현재 페이지의 URL이 메뉴 항목의 href 속성과 일치한다면
                        if (currentPage.includes(item.getAttribute('href'))) {
                            // 해당 메뉴 항목에 'active' 클래스를 추가합니다.
                            item.classList.add('active');
                        } else {
                            // 일치하지 않는 경우, 'active' 클래스를 제거합니다.
                            item.classList.remove('active');
                        }
                    });
                });




                var memberPhId;
                var phErrMsg;
                var successPhCheck;
                var containerId;
                document.getElementById("phCheckBtn").addEventListener("click", function () {
                    memberPhId = "memberPh";
                    phErrMsg = "phErrMsg1";
                    successPhCheck = "successPhCheck1";
                    containerId = "memberPhCheckContainer";

                    sendAuthenticationSMS(memberPhId, phErrMsg, successPhCheck, containerId);

                });

                document.getElementById("phNewCheckBtn").addEventListener("click", function () {
                    memberPhId = "memberPhNew";
                    phErrMsg = "phErrMsg2";
                    successPhCheck = "successPhCheck2";
                    containerId = "memberPhNewCheckContainer";
                    sendAuthenticationSMS(memberPhId, phErrMsg, successPhCheck, containerId);

                });


                //인증번호 송신이 확인되면 인증번호 입력 란 생성 Js
                var serverGeneratedCode = "";
                var checkPhFlag = false;
                var phRegex = /^010\d{8}$/i;

                function sendAuthenticationSMS(memberPhId, phErrMsg, successPhCheck, containerId) {
                    $("." + successPhCheck).text("");
                    var memberPh = $("#" + memberPhId).val();
                    // 사용자가 입력한 전화번호 가져오기

                    console.log("[로그] : ")
                    console.log("[로그] memberPhId: " + memberPhId);
                    console.log("[로그] phRegex: " + phRegex.test(memberPhId));
                    if (!phRegex.test(memberPh)) {
                        $("#" + phErrMsg).text('올바른 번호 형식이 아닙니다.');
                        $("#" + phErrMsg).css('color', 'red');
                        return checkPhFlag;
                    }





                    // AJAX를 사용하여 서버에 전화번호 전송
                    $.ajax({
                        url: "/sendAuthenticationSMS",
                        type: "POST",
                        dataType: "text",
                        data: { memberPh: memberPh },
                        success: function (data) {
                            // 서버 응답에 따른 처리
                            if (data !== "fail") {
                                console.log("data " + data);
                                $("#" + phErrMsg).text('');
                                swal("success", "인증번호 발송이 완료되었습니다.", "success", {
                                    button: "OK",
                                });
                                createMemberPhCheckInput(containerId);

                                document.getElementById("memberPhNewCheckContainer").style.display = "flex"; //
                                console.log("[로그] serverGeneratedCode :" + data)
                                // 성공적으로 SMS를 보낸 경우 추가 동작을 수행할 수 있습니다.
                                serverGeneratedCode = data;
                            } else {
                                swal("fail", "인증번호 발송 실패", "error", {
                                    button: "OK",
                                });
                                // SMS 전송 실패 시 사용자에게 알림을 표시할 수 있습니다.
                            }
                        },
                        error: function (error) {
                            console.log(error);
                            alert("ajax 요청오류");
                            // AJAX 요청 중 오류가 발생한 경우에 대한 처리
                        }
                    });
                }

                document.getElementById("memberPh").addEventListener("input", function () {
                    // input 내용이 바뀔 때마다 checkPhFlag를 false로 설정 다시인증하기
                    checkPhFlag = false;
                });

                function createMemberPhCheckInput(containerId) {
                    var container = document.getElementById(containerId); // 동적으로 컨테이너 선택

                    // 기존에 생성된 입력란과 버튼이 있다면 제거
                    container.innerHTML = '';

                    // 인증번호 입력란 생성
                    var input = document.createElement("input");
                    input.type = "text";
                    input.id = containerId + "Input"; // 고유 ID 부여
                    input.name = "memberPhCheck";
                    input.className = "form-control";
                    input.placeholder = "인증번호 입력";

                    // 확인 버튼 생성
                    var button = document.createElement("a");
                    button.id = containerId + "Button"; // 고유 ID 부여
                    button.className = "btn btn-magz btn-sm";
                    button.style.textAlign = "center";
                    button.style.paddingTop = "3%";
                    button.style.width = "7rem";
                    button.textContent = "check";

                    // 생성된 요소를 부모 컨테이너에 추가
                    container.appendChild(input);
                    container.appendChild(button);

                    // 컨테이너 표시
                    container.style.display = "flex";
                    console.log("[로그] input.id : " + input.id);




                    //SMS check.js

                    $(document).ready(function () {
                        $("#" + button.id).on('click', function () {


                            console.log("smsCheck 동작함");
                            console.log(serverGeneratedCode);
                            if ($("#" + input.id).val() == serverGeneratedCode) {
                                $("#" + input.id).prop('disabled', true);
                                $("." + successPhCheck).text("인증번호가 일치합니다.");
                                $("." + successPhCheck).css("color", "green");
                                checkPhFlag = true;
                                return checkPhFlag;
                            } else {
                                $("." + successPhCheck).text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
                                $("." + successPhCheck).css("color", "red");
                                $(this).attr("autofocus", true);
                                checkPhflag = false;
                                return checkPhFlag;

                            }
                        });
                    });
                }





            </script>


            </html>