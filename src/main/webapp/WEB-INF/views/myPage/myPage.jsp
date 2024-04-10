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

                    /* 별점 */


                    .rate {
                        display: inline-block;
                        border: 0;
                        margin-right: 15px;
                    }

                    .rate>input {
                        display: none;
                    }

                    .rate>label {
                        float: right;
                        color: #ddd
                    }

                    .rate>label:before {
                        display: inline-block;
                        font-size: 2rem;
                        padding: .3rem .2rem;
                        margin: 0;
                        cursor: pointer;
                        font-family: FontAwesome;
                        content: "\f005 ";
                    }

                    .rate .half:before {
                        content: "\f089 ";
                        position: absolute;
                        padding-right: 0;
                    }

                    .rate input:checked~label {
                        color: #f73c32 !important;
                    }

                    .rate input:checked+.rate label:hover,
                    .rate input input:checked~label:hover,
                    .rate input:checked~.rate label:hover~label,
                    .rate label:hover~input:checked~label {
                        color: #f73c32 !important;
                    }
            </style>

            <head>
                <chalKagTags:webCss />
                <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
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
                                    <div style="display: flex; align-items: center;">
                                        <div>Basic Information <i class="ion-ios-information-outline"
                                                style="font-size: 16px; margin-bottom:15px"></i></div>
                                                <a href="payment" class="btn btn-magz btn-sm" style="text-transform: uppercase; font-weight: 500; margin-top:0%; margin-left:49%; font-size:18px;">BOOST<i class="ion-ios-fastforward" style="font-size: 20px;"></i></a>
                                       </div>
                
                                    <div style="display: flex; align-items: center;">
                                        <figure class="featured-author-picture">
                                            <img src="profileImg/${memberInfo.profileImgName}" id="ImgPreview" alt="Image Not Loaded" style="width: 76px; height: 76px; border-radius: 50%; object-fit: cover; border: 1px solid rgb(168, 168, 168);">
                                        </figure>
                                        <div style="margin-left: 10px;">
                                            <div class="memberName" id="memberName" style="font-size: 30px; font-weight: bold;">${memberInfo.memberName}</div>
                                            <div class="memberId" id="memberId" style="font-size: 20px; font-weight:20px">${memberInfo.memberId}</div>
                                        </div>
                                    </div>
                
                                    <hr style="border-top: 0.1px solid #cdcdcd; margin-top: 5px; margin-bottom: 10px;">
                
                                    <div style="display: flex; align-items: center; margin-bottom: 8px">
                                        <i class="ion-social-snapchat-outline" style="margin-right: 10px; font-size: 18px;"></i>
                                        <div class="memberNickname" id="memberNickname">${memberInfo.memberNickname}</div>
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
                                        <i class="ion-female" style="font-size: 18px;"></i><i class="ion-male" style="margin-right: 8px; font-size: 10px;"></i>
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
                                            <li class="dropdown magz-dropdown">
                                                <a href="category.html">My Posts<i class="ion-android-create"></i></a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="myMarketPostList">MarketPost</a></li>
                                                    <li><a href="myHeadHuntPostList">HeadHuntPost</a></li>
                                                    <li><a href="myJobHuntPostList">JobHuntPost</a></li>
                                                    <li><a href="myFreePostList">FreePost</a></li>
                                                </ul>
                                            </li>
                                            <li class="dropdown magz-dropdown">
                                                <a href="category.html">My Recommend Posts<i class="ion-ios-heart"></i></a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="recommendMarketPostList">MarketPost</a></li>
                                                    <li><a href="recommendHeadHuntPostList">HeadHuntPost</a></li>
                                                    <li><a href="recommendJobHuntPostList">JobHuntPost</a></li>
                                                    <li><a href="recommendFreePostList">FreePost</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="myReview">My Review List<i class="ion-ios-star"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </nav>
                        </div>
                        <br>
                
                        <div class="review">
                            <div class="col-md-6 col-sm-12 col-xs-12">
                                <div class="box box-border" style="margin-top:3%;">
                                    <div class="box-body">

                                        <form id="writeReviewForm" action="/writeReview" method="post" class="row">
                                            <input type="hidden" id="reviewPartner" name="reviewPartner"
                                                value="${memberInfo.memberId}">
                                            <div class="form-group col-md-12" style="text-align: left;">
                                                <div class="line">
                                                    <div style="left:31px">Review</div>
                                                </div>
                                            </div>
                                        </form>
                                        <c:if test="${not empty reviewList}">
                                            <h5 class="title">${reviewList[0].reviewTotalCnt} REVIEWS</h5>
                                        </c:if>
                                        <c:if test="${empty reviewList}">
                                            <h5 class="title">0 REVIEWS</h5>
                                        </c:if>


                                        <div class="comments">
                                            <div class="comment-list">
                                                <div class="item"
                                                    style="border:0px; padding:0px; margin-bottom:0%;">                                          
                                                    <c:if test="${not empty reviewList}">
                                                        <c:forEach items="${reviewList}" var="reviewData"
                                                            varStatus="loop">
                                                            <!-- 두 번째 목록부터는 위쪽에 <div class="line thin"></div> 추가 -->
                                                            <c:if test="${loop.index > 0}">
                                                                <div class="line thin" style="margin:0%;"></div>
                                                            </c:if>
                                                            <div class="rate">
                                                                <!-- 별점을 표시하기 위한 fieldset -->
                                                                <fieldset class="rate"
                                                                    style="pointer-events: none;">
                                                                    <!-- 별점에 따라 해당하는 input 태그를 체크 -->
                                                                    <input type="radio" id="rating10_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="5" ${reviewData.reviewScore=='5.0'
                                                                        ? 'checked' : '' }>
                                                                    <label for="rating10_${loop.index}"
                                                                        title="5점"></label>
                                                                    <input type="radio" id="rating9_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="4.5" ${reviewData.reviewScore=='4.5'
                                                                        ? 'checked' : '' }><label class="half"
                                                                        for="rating9_${loop.index}"
                                                                        title="4.5점"></label>
                                                                    <input type="radio" id="rating8_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="4" ${reviewData.reviewScore=='4.0'
                                                                        ? 'checked' : '' }><label
                                                                        for="rating8_${loop.index}"
                                                                        title="4점"></label>
                                                                    <input type="radio" id="rating7_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="3.5" ${reviewData.reviewScore=='3.5'
                                                                        ? 'checked' : '' }><label class="half"
                                                                        for="rating7_${loop.index}"
                                                                        title="3.5점"></label>
                                                                    <input type="radio" id="rating6_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="3" ${reviewData.reviewScore=='3.0'
                                                                        ? 'checked' : '' }><label
                                                                        for="rating6_${loop.index}"
                                                                        title="3점"></label>
                                                                    <input type="radio" id="rating5_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="2.5" ${reviewData.reviewScore=='2.5'
                                                                        ? 'checked' : '' }><label class="half"
                                                                        for="rating5_${loop.index}"
                                                                        title="2.5점"></label>
                                                                    <input type="radio" id="rating4_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="2" ${reviewData.reviewScore=='2.0'
                                                                        ? 'checked' : '' }><label
                                                                        for="rating4_${loop.index}"
                                                                        title="2점"></label>
                                                                    <input type="radio" id="rating3_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="1.5" ${reviewData.reviewScore=='1.5'
                                                                        ? 'checked' : '' }><label class="half"
                                                                        for="rating3_${loop.index}"
                                                                        title="1.5점"></label>
                                                                    <input type="radio" id="rating2_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="1" ${reviewData.reviewScore=='1.0'
                                                                        ? 'checked' : '' }><label
                                                                        for="rating2_${loop.index}"
                                                                        title="1점"></label>
                                                                    <input type="radio" id="rating1_${loop.index}"
                                                                        name="reviewScoreShow_${loop.index}"
                                                                        value="0.5" ${reviewData.reviewScore=='0.5'
                                                                        ? 'checked' : '' }><label class="half"
                                                                        for="rating1_${loop.index}"
                                                                        title="0.5점"></label>
                                                                </fieldset>
                                                            </div>
                                                            <div class="user" style="margin-bottom:0%;">
                                                                <!-- 회원 프로필 이미지 -->
                                                                <figure>
                                                                    <img src="profileImg/${reviewData.profileImgName}"
                                                                        style="width:100%; height:100%; object-fit:cover">
                                                                </figure>
                                                                <div class="details">
                                                                    <h5 class="name">${reviewData.memberNickname}
                                                                        <a href="/memberPage?memberId=${reviewData.memberId}" style="font-size:14px;">${reviewData.memberId}</a>
                                                                    </h5>
                                                                    <div class="time">${reviewData.reviewDate}</div>
                                                                    <div class="description" style="word-break: break-all;">
                                                                        ${reviewData.reviewContent}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>

                                                    <div class="moreReviewContainer">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${reviewList[0].reviewTotalCnt > 10}">
                                            <button class="btn btn-magz btn-sm" id="moreReview">더보기</button>
                                        </c:if>







                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>


                <chalKagTags:webFooter />

                <!-- JS -->
                <script src="css/user/js/jquery.js"></script>
                <script src="css/user/js/jquery.migrate.js"></script>
                <script src="css/user/scripts/bootstrap/bootstrap.min.js"></script>
                <script src="css/user/scripts/jquery-number/jquery.number.min.js"></script>
                <script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
                <script src="css/user/scripts/owlcarousel/dist/owl.carousel.min.js"></script>
                <script src="css/user/js/e-magz.js"></script>
                <script src="css/user/js/demo.js"></script>
             

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

                document.addEventListener("DOMContentLoaded", function () {
                    var memberPhElement = document.getElementById('memberPh');
                    var memberPh = memberPhElement.innerText.trim();
                    if (memberPh.length === 11) {
                        var formattedPh = memberPh.substring(0, 3) + '-' + memberPh.substring(3, 7) + '-' + memberPh.substring(7);
                        memberPhElement.innerText = formattedPh;
                    }
                });



                var reviewStart = 10; // 초기값 설정

                if (document.getElementById("moreReview")) {
                    // User Hold 버튼을 클릭했을 때의 이벤트 처리
                    document.getElementById("moreReview").addEventListener("click", function (event) {
                        var checkedRadioIds = [];
                        console.log("REVIEW");
                        var moreReviewButton = document.getElementById("moreReview");
                        moreReviewButton.style.display = "none";


                        // 현재 선택된 라디오 버튼 중에서 input type이 radio이고 name이 reviewScore가 아닌 것을 찾아서
                        $('input[type="radio"]:not([name="reviewScore"])').each(function () {
                            // 만약 현재 라디오 버튼이 체크되어 있다면
                            if ($(this).prop('checked')) {
                                // 각각의 ID를 배열에 저장
                                checkedRadioIds.push($(this).attr('id'));
                            }
                        });


                        // 1. 신고를 당해 정지된 사용자가 있는지 확인 
                        $.ajax({
                            type: 'GET',
                            url: '/moreReview',
                            dataType: 'json',
                            data: {
                                reviewPartner: $('#reviewPartner').val(), // reviewPartner의 값을 가져와 전송
                                reviewStart: reviewStart // reviewStart 변수의 값을 전송
                            }, // 폼 데이터 전송
                            success: function (data) {
                                if (data) {
                                    console.log(data);
                                    // 받아온 데이터를 반복하여 HTML을 생성하고 추가
                                    data.forEach(function (reviewData, index) {
                                        var html = '<div class="rate">' +
                                            '<fieldset class="rate" style="pointer-events: none;">' +
                                            '<input type="radio" id="rating10_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="5"' + (reviewData.reviewScore === '5.0' ? ' checked' : '') + '><label for="rating10_' + index + '" title="5점"></label>' +
                                            '<input type="radio" id="rating9_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="4.5"' + (reviewData.reviewScore === '4.5' ? ' checked' : '') + '><label class="half" for="rating9_' + index + '" title="4.5점"></label>' +
                                            '<input type="radio" id="rating8_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="4"' + (reviewData.reviewScore === '4.0' ? ' checked' : '') + '><label for="rating8_' + index + '" title="4점"></label>' +
                                            '<input type="radio" id="rating7_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="3.5"' + (reviewData.reviewScore === '3.5' ? ' checked' : '') + '><label class="half" for="rating7_' + index + '" title="3.5점"></label>' +
                                            '<input type="radio" id="rating6_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="3"' + (reviewData.reviewScore === '3.0' ? ' checked' : '') + '><label for="rating6_' + index + '" title="3점"></label>' +
                                            '<input type="radio" id="rating5_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="2.5"' + (reviewData.reviewScore === '2.5' ? ' checked' : '') + '><label class="half" for="rating5_' + index + '" title="2.5점"></label>' +
                                            '<input type="radio" id="rating4_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="2"' + (reviewData.reviewScore === '2.0' ? ' checked' : '') + '><label for="rating4_' + index + '" title="2점"></label>' +
                                            '<input type="radio" id="rating3_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="1.5"' + (reviewData.reviewScore === '1.5' ? ' checked' : '') + '><label class="half" for="rating3_' + index + '" title="1.5점"></label>' +
                                            '<input type="radio" id="rating2_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="1"' + (reviewData.reviewScore === '1.0' ? ' checked' : '') + '><label for="rating2_' + index + '" title="1점"></label>' +
                                            '<input type="radio" id="rating1_' + index + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="0.5"' + (reviewData.reviewScore === '0.5' ? ' checked' : '') + '><label class="half" for="rating1_' + index + '" title="0.5점"></label>' +
                                            '</fieldset>' +
                                            '</div>' +
                                            '<div class="user" style="margin-bottom:0%;">' +
                                            '<figure>' +
                                            '<img src="profileImg/' + reviewData.profileImgName + '" style="width:100%; height:100%; object-fit:cover">' +
                                            '</figure>' +
                                            '<div class="details">' +
                                            '<h5 class="name">' + reviewData.memberNickname + ' <a href="/memberPage?memberId=' + reviewData.memberId + '"style="font-size:14px;">' + reviewData.memberId + '</a></h5>' +
                                            '<div class="time">' + reviewData.reviewDate + '</div>' +
                                            '<div class="description" style="word-break: break-all;">' + reviewData.reviewContent + '</div>' +
                                            '</div>' +
                                            '</div>';

                                        $('.moreReviewContainer').append(html);


                                    });
                                    // 저장된 ID를 가진 라디오 버튼을 찾아서 checked: true로 설정
                                    checkedRadioIds.forEach(function (id) {
                                        $('#' + id).prop('checked', true);
                                    });


                                    reviewStart += 10;
                                    var restReviewCnt = data[0].reviewTotalCnt - reviewStart;
                                    console.log(restReviewCnt);
                                    // reviewTotalCnt를 사용하여 추가적인 작업을 수행
                                    if (restReviewCnt > 0) {
                                        moreReviewButton.style.display = "block";
                                    }
                                }
                            }
                        });
                    });
                }


            </script>


            </html>