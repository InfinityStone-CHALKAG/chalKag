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
                    text-align: left;
                    /* 텍스트를 양쪽 정렬한다. */
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
                                                    <div class="badge-item"><i class="ion-star"></i> PREMIUM</div>
                                                </div>
                                                <div class="featured-author-center">
                                                    <figure class="featured-author-picture">
                                                        <!-- <img src="profileImg/${memberInfo.profileImgName}" -->
                                                        <img src="css/user/images/sponsored.png" alt="Sample Article"
                                                            data-input="fileInput" style="cursor: pointer;">
                                                        <!-- data-input 태그를 통해 이미지를 누르면 파일 선택 진행 -->
                                                        <input type="file" id="fileInput" class="fileInput"
                                                            accept="image/*" style="display: none;">
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
                                                    style="font-weight: bold; font-family: 'Lato'; font-size:19px ;">Lv
                                                    : 5</div>
                                                <div style="display: flex; justify-content: center;">
                                                    <input type="range" id="Exp" name="Exp" min="0" max="1000"
                                                        value="500" style="width: 250px;">
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
                                                <div class="featured-author-quote" style="margin-top: 11px;">
                                                    <button class="btn btn-primary" id="ChangeIntroductionButton">Change
                                                        Introduction</button>
                                                </div>
                                            </div>
                                            <ul class="changeList">
                                                <li><a href="myPage">MY INFORMATION</a></li>
                                                <li><a href="changeNickname" class="active">CHANGE NICKNAME</a></li>
                                                <li><a href="changePassword">CHANGE PASSWORD</a></li>
                                                <li><a href="changePhoneNumber">CHANGE PHONENUMBER</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </aside>
                        </div>
                        <div class="col-md-5 col-sm-12 col-xs-12"
                            style="border: 2px solid #F73F52; padding-top: 10px; padding-bottom: 10px; border-radius: 10px; margin-left: 20px;">


                            <div class="box box-border">
                                <div class="box-body">
                                    <h4>Change Nickname</h4>
                                    <form>
                                        <div class="form-group">
                                            <label>Before</label>
                                            <input type="text" name="memberNickname" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>After</label>
                                            <input type="text" name="memberNickname" class="form-control">
                                        </div>
                                        <div class="form-group text-right">
                                            <button class="btn btn-primary btn-block">Confrim</button>
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
                $(document).ready(function () {
                    $("#ChangeIntroductionButton").click(function () {
                        if ($(this).text() === "Change Introduction") {
                            // 버튼이 'Change Introduction' 상태일 때
                            var currentIntroduction = $("#Introdudction").text().trim();
                            // 쌍따옴표 제거
                            currentIntroduction = currentIntroduction.replace(/^"|"$/g, '');
                            console.log("[로그] currentIntroduction: " + currentIntroduction);
                            $("#Introdudction").html('<input type="text" id="introductionInput" value="' + currentIntroduction + '" style="border: 2px solid #F73F52; width: 100%; height: 25px; color: black; text-align: center;" />');
                            $(this).text("Confirm");
                            $(this).css({
                                'width': '188.25px',
                                'height': '43.42px'
                            });
                        } else {
                            // 버튼이 'Confirm' 상태일 때
                            var updatedIntroduction = $("#introductionInput").val().trim();
                            if (updatedIntroduction === "") {
                                updatedIntroduction = "Write a self-introduction to showcase yourself";
                            }
                            // 입력된 문구 양 끝에 쌍따옴표 추가
                            updatedIntroduction = '"' + updatedIntroduction + '"';
                            $("#Introdudction").text(updatedIntroduction);
                            $(this).text("Change Introduction");
                            // 버튼에 width와 height 스타일 적용
                            $(this).css({
                                'width': '188.25px',
                                'height': '43.42px'
                            });
                        }
                    });
                });


                // 마이페이지 side바 하단 active Js
                document.addEventListener("DOMContentLoaded", function () {
                    // 모든 링크에 대한 참조를 가져옵니다.
                    var links = document.querySelectorAll('.changeList a');

                    // 각 링크에 클릭 이벤트 리스너를 추가합니다.
                    links.forEach(function (link) {
                        link.addEventListener('click', function () {
                            // 먼저, 모든 링크에서 'active' 클래스를 제거합니다.
                            links.forEach(function (item) {
                                item.classList.remove('active');
                            });

                            // 클릭된 링크에 'active' 클래스를 추가합니다.
                            this.classList.add('active');
                        });
                    });
                });

                //마이페이지 프로필 이미지 변경
                document.addEventListener('DOMContentLoaded', function () {
                    // 이미지 요소를 선택합니다.
                    const image = document.querySelector('.featured-author-picture img');

                    // 이미지에 클릭 이벤트 리스너를 추가합니다.
                    image.addEventListener('click', function () {
                        // 해당 이미지에 매칭되는 파일 입력 필드의 id를 가져옵니다.
                        const fileInputId = image.getAttribute('data-input');
                        const fileInput = document.getElementById(fileInputId);

                        // 파일 입력 필드를 클릭하게 합니다.
                        fileInput.click();

                        // 파일 입력 필드에 onchange 이벤트 리스너를 추가합니다.
                        fileInput.onchange = function (event) {
                            const file = event.target.files[0]; // 선택된 파일을 가져옵니다.
                            if (file) {
                                // FileReader 객체를 생성하여 파일을 읽습니다.
                                const reader = new FileReader();
                                reader.onload = function (e) {
                                    // 읽기 작업이 완료되면 이미지의 src 속성을 변경합니다.
                                    image.src = e.target.result;
                                };
                                reader.readAsDataURL(file); // 파일을 Data URL 형식으로 읽습니다.
                            }
                        };
                    });
                });
            </script>


            </html>