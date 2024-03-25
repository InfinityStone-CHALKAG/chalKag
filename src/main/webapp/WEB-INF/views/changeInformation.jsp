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

                table {
                    width: 100%;
                    height: 100%;
                    border-collapse: collapse;
                    border-left: 1px solid transparent;
                    /* 테이블 왼쪽 끝 라인 투명 */
                    border-right: 1px solid transparent;
                    /* 테이블 오른쪽 끝 라인 투명 */
                }

                td,
                th {
                    padding: 20px !important;
                }

                td {

                    border: 1px solid rgb(201, 201, 201);
                    text-align: left;
                    vertical-align: middle;
                    border-left: none;
                    /* 셀의 왼쪽 라인 제거 */
                    border-right: none;
                    /* 셀의 오른쪽 라인 제거 */
                }

                /* 첫 번째와 마지막 셀에 대해 왼쪽과 오른쪽 라인을 투명하게 설정 */
                td:first-child {
                    border-left: 1px solid transparent;
                }

                td:last-child {
                    border-right: 1px solid transparent;
                }

                .gray-background {
                    background-color: #efefef;
                    /* 회색 바탕 */
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
                                                            alt="Sample Article"
                                                            style="width: 100%; height: 100%; object-fit: cover;">
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


                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="box box-border">
                                <div class="box-body">
                                    <h4>Change Information</h4>
                                    <form id="changeInformation" method="post" action="changeInformation"
                                        enctype="multipart/form-data" onsubmit="return true">
                                        <div class="form-group">
                                            <table>
                                                <tr>
                                                    <td class="gray-background" style="width: 32%;">Profile Image</td>
                                                    <td>

                                                        <figure class="featured-author-picture">
                                                            <img src="profileImg/${memberInfo.profileImgName}"
                                                                id="preview" alt="Image Not Loaded" style="width: 76px; 
                                                height: 76px; 
                                                border-radius: 50%; 
                                                object-fit: cover;
                                                border: 1px solid rgb(168, 168, 168); 
                                                margin-bottom: 10px">
                                                        </figure>
                                                        <input type="file" name="file" id="fileInput" accept="image/*"
                                                            style="display: none;">
                                                        <button type="button" id="uploadButton"
                                                            class="btn btn-primary btn-sm btn-rounded">Upload</button>

                                                        <button type="button" id="defaultButton"
                                                            class="btn btn-primary btn-sm btn-rounded">Default</button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="gray-background" style="width: 32%;">Introduction</td>
                                                    <td> <textarea id="memberIntroduction" name="memberIntroduction"
                                                            class="form-control" rows="1"
                                                            style="width:310px; height:70px; resize:none;">${memberInfo.memberIntroduction}</textarea>
                                                    </td>
                                                </tr>
                                            </table>
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




                // 텍스트박스 길이 초과시 스크롤생성
                const textarea = document.getElementById('memberIntroduction');
                textarea.addEventListener('input', autoResize, false);

                function autoResize() {
                    this.style.height = 'auto';
                    this.style.height = this.scrollHeight + 'px';
                }


                // Default 버튼 클릭 시 미리보기를 default 이미지로 변경 및 파일 설정
                defaultButton.addEventListener('click', function () {
                    preview.src = 'profileImg/default.png';

                    // 파일 입력 요소 가져오기
                    var fileInput = document.getElementById('fileInput');

                    // default.png 파일을 가져오기
                    fetch('profileImg/default.png')
                        .then(response => response.blob()) // 응답을 Blob으로 변환
                        .then(blob => {
                            // default.png와 해당 내용을 포함한 새로운 File 객체 생성
                            var defaultFile = new File([blob], "default.png", { type: "image/png" });

                            // default 파일을 포함하는 새로운 FileList 객체 생성
                            var fileList = new DataTransfer();
                            fileList.items.add(defaultFile);

                            // 파일 입력의 files 속성을 생성된 FileList 객체로 설정
                            fileInput.files = fileList.files;
                        })
                        .catch(error => console.error('파일 가져오기 오류:', error));

                });

            </script>
            <!-- 이미지 등록 및 유효성 signUp.js 재활용 -->
            <script src="js/user/signUp.js"></script>


            </html>