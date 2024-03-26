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
                            <chalKagTags:myPageSidebar />
                        </div>
                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="box box-border">
                                <div class="box-body">
                                    <div>Basic Information <i class="ion-ios-information-outline"
                                            style="font-size: 16px; margin-bottom:15px"></i></div>

                                    <div style="display: flex; align-items: center;">
                                        <figure class="featured-author-picture">
                                            <img src="profileImg/${memberInfo.profileImgName}" id="ImgPreview"
                                                alt="Image Not Loaded" style="width: 76px; 
                                    height: 76px; 
                                    border-radius: 50%; 
                                    object-fit: cover;
                                    border: 1px solid rgb(168, 168, 168);">
                                        </figure>
                                        <div style="margin-left: 10px;">
                                            <div class="memberName" id="memberName"
                                                style="font-size: 30px; font-weight: bold;">
                                                ${memberInfo.memberName}
                                            </div>
                                            <div class="memberId" id="memberId"
                                                style="font-size: 20px; font-weight:20px">${memberInfo.memberId}</div>
                                        </div>
                                    </div>

                                    <hr style="border-top: 0.1px solid #cdcdcd; margin-top: 5px; margin-bottom: 10px;">

                                    <div style="display: flex; align-items: center; margin-bottom: 8px">
                                        <i class="ion-social-snapchat-outline"
                                            style="margin-right: 10px; font-size: 18px;"></i>
                                        <div class="memberNickname" id="memberNickname">${memberInfo.memberNickname}
                                        </div>
                                    </div>

                                    <div style="display: flex; align-items: center; margin-bottom: 8px">
                                        <i class="ion-iphone" style="margin-right: 17px; font-size: 18px;"></i>
                                        <div class="memberPh" id="memberPh">${memberInfo.memberPh}</div>
                                    </div>

                                    <div style="display: flex; align-items: center; margin-bottom: 8px">
                                        <i class="ion-ios-color-wand" style="margin-right: 12px; font-size: 18px;"></i>
                                        <div class="memberBirth" id="memberBirth">${memberInfo.memberBirth}</div>
                                    </div>

                                    <div style="display: flex; align-items: center; margin-bottom: 8px">
                                        <i class="ion-female" style="font-size: 18px;"></i><i class="ion-male"
                                            style="margin-right: 8px; font-size: 10px;"></i>
                                        <div class="memberGender" id="memberGender">${memberInfo.memberGender}</div>
                                    </div>

                                    <div style="display: flex; align-items: center; margin-bottom: 8px">
                                        <i class="ion-ribbon-b" style="margin-right: 13px; font-size: 18px;"></i>
                                        <div class="memberGrade" id="memberGrade">${memberInfo.memberGrade}</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Start nav -->
                        <div class="col-md-6 col-sm-12 col-xs-12" style="margin-left: 30px;">
                            <nav class="menu" style="border-top:0px; border-bottom:0px">
                                <div class="container">
                                    <div id="menu-list">
                                        <ul class="nav-list">
                                            <li class="dropdown magz-dropdown" ">
                            </i><a href=" category.html">My Posts<i class="ion-android-create"></i></a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="marketPost">MarketPost</a></li>
                                                    <li><a href="HeadHuntPost">HeadHuntPost</a></li>
                                                    <li><a href="JobHuntPost">JobHuntPost</a></li>
                                                    <li><a href="FreePost">FreePost</a></li>
                                                </ul>
                                            </li>
                                            <li class="dropdown magz-dropdown">
                                                <a href="category.html">My Recommend
                                                    Posts<i class="ion-ios-heart"></i></a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="marketPost">MarketPost</a></li>
                                                    <li><a href="HeadHuntPost">HeadHuntPost</a></li>
                                                    <li><a href="JobHuntPost">JobHuntPost</a></li>
                                                    <li><a href="FreePost">FreePost</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="myReview">My Review List<i class="ion-ios-star"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <a href="#" class="btn btn-magz"
                                    style="text-transform: uppercase; font-weight: 500; margin-top:100px">BOOST<i
                                        class="ion-ios-fastforward" style="font-size: 20px;"></i></a>
                            </nav>
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

                // 레벨업시 경험치 초기화
                // memberInfo 객체를 JavaScript에서 사용할 수 있도록 변수에 할당합니다.
                // 실제 사용 시에는 서버 사이드 코드에서 해당 값을 JavaScript 변수로 전달해야 합니다.
                var currentExp = parseInt('${memberInfo.currentExp}', 10); // 현재 경험치
                var currentNextExp = parseInt('${memberInfo.currentNextExp}', 10); // 다음 레벨까지 필요한 경험치

                // 경험치가 같아지는 순간을 감지하고, min 값을 변경합니다.
                if (currentExp >= currentNextExp) {
                    document.getElementById('Exp').min = currentExp.toString();
                }



            </script>


            </html>