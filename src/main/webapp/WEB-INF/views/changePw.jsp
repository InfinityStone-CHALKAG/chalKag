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
                            <chalKagTags:myPageSidebar />
                        </div>
                        <div class="col-md-5 col-sm-12 col-xs-12">
                            <div class="box box-border">
                                <div class="box-body">
                                    <h4>Change Password</h4>
                                    <form id="changePwForm" method="post" action="changePw">

                                        <label>Present</label>
                                        <div style="display: flex;">
                                            <input type="text" id="memberPw" name="memberPw" class="form-control">
                                            <a id="pwCheckBtn" style="text-align: center; padding-top: 3%; width: 7rem;"
                                                class="btn btn-magz btn-sm" onclick="checkPresentPw()">check</a>
                                        </div>
                                        <p id="PresentPwErrMsg" class="error"></p>
                                        <div class="form-group">
                                            <label>New Password</label>
                                            <input type="text" id="newPw" name="newPw" class="form-control"
                                                placeholder="8자 이상 영문, 숫자, 특수문자포함">
                                            <p id="pwError" class="error"></p>
                                            <label>New Password Check</label>
                                            <input type="text" id="newPwCheck" name="newPwCheck" class="form-control">
                                        </div>
                                        <div class="form-group text-right">
                                            <button class="btn btn-primary btn-block">Confirm</button>
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



                // 비밀번호 확인 함수
                var checkPasswordFlag = false;
                function checkPassword() {
                    var password = document.getElementById("newPw").value;
                    var confirmPassword = document.getElementById("newPwCheck").value;
                    var errorElement = document.getElementById("pwError");

                    // 비밀번호 형식 검사
                    var pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;



                    if (!pwRegex.test(password)) {
                        errorElement.textContent = "비밀번호는 최소 8자 이상이며 영문, 숫자, 특수문자를 포함해야 합니다."; // 에러 메시지 표시
                        errorElement.style.color = "red";
                        checkPasswordFlag = false;
                        return checkPasswordFlag;
                    }


                    // 비밀번호와 비밀번호 확인이 일치하는지 확인
                    if (password === confirmPassword) {
                        errorElement.textContent = "비밀번호가 일치합니다."; // 에러 메시지를 초기화
                        errorElement.style.color = "green";
                        checkPasswordFlag = true;
                        return checkPasswordFlag;
                    }
                    else {
                        errorElement.textContent = "비밀번호가 일치하지 않습니다."; // 에러 메시지 표시
                        errorElement.style.color = "red";
                        checkPasswordFlag = false;
                        return checkPasswordFlag;
                    }


                }

                // 비밀번호 확인 필드 입력 시 체크 함수 호출
                document.getElementById("newPw").addEventListener("input", checkPassword);
                document.getElementById("newPwCheck").addEventListener("input", checkPassword);

                var changePwForm = document.getElementById('changePwForm');

                if (changePwForm) {
                    changePwForm.onsubmit = function () {
                        return validateForm();
                    };
                }

                function validateForm() {

                    if (checkPresentPwFlag == false) {
                        swal("fail", "본인획인을 해주세요.", "error", {
                            button: "OK",
                        });
                        return false;
                    }

                    if (checkPasswordFlag == false) {
                        swal("fail", "변경할 비밀번호를 확인해주세요.", "error", {
                            button: "OK",
                        });
                        return false;
                    }
                    // 모든 검증이 통과했을 경우
                    return true;
                }



            </script>


            <script src="js/user/checkPresentPw.js"></script>



            </html>