<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    z #scroll-container {
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

    .review textarea.form-control {
        height: 90px;
        resize: none;
    }


    /* 별점 */


    .rate {
        display: inline-block;
        border: 0;
        margin-right: 15px;
    }

    .rate > input {
        display: none;
    }

    .rate > label {
        float: right;
        color: #ddd
    }

    .rate > label:before {
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

    .rate input:checked ~ label {
        color: #f73c32 !important;
    }

    .rate input:checked + .rate label:hover,
    .rate input input:checked ~ label:hover,
    .rate input:checked ~ .rate label:hover ~ label,
    .rate label:hover ~ input:checked ~ label {
        color: #f73c32 !important;
    }
</style>

<head>
    <chalKagTags:webCss/>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
</head>

<body>

<chalKagTags:webHeader/>

<section class="myPage">
    <div class="container">
        <div class="col-xs-6 col-md-4 sidebar" id="sidebar">
            <div class="sidebar-title for-tablet">Sidebar</div>
            <div class="aside-body">
                <div class="featured-author">
                    <div class="featured-author-inner">
                        <div class="featured-author-cover"
                             style="background-image: url('css/user/images/news/img15.jpg');">
                            <div class="badges">
                                <c:if test="${memberInfo.memberGrade eq 'PREMIUM'}">
                                    <div class="badge-item"><i class="ion-star"></i> PREMIUM</div>
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
                                        <div class="value">${memberInfo.postCount}</div>
                                    </a>
                                </div>
                                <div class="item" style="width: -webkit-calc(100% / 2);">
                                    <a href="#">
                                        <div class="name">Score</div>
                                        <c:if test="${memberInfo.currentScore != null}">
                                            <div class="value">
                                                <fmt:formatNumber value="${memberInfo.currentScore}"
                                                                  pattern="#,##0.0"/>
                                            </div>
                                        </c:if>
                                        <c:if test="${memberInfo.currentScore == null}">
                                            <div class="value">0.0</div>
                                        </c:if>
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
                                INTRODUCTION
                            </div>
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
                                    <li><a href="memberPage" class="active">USER INFORMATION</a>
                                    </li>
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
                    <div style="display: flex; align-items: center;">
                        <div>Basic Information <i class="ion-ios-information-outline"
                                                  style="font-size: 16px; margin-bottom:15px"></i></div>
                        <c:if test="${sessionScope.member != null}">
                            <a href="writeReport?reportSuspector=${memberInfo.memberId}"
                               class="btn btn-primary btn-sm" style="margin-left:61%">Report</a>
                        </c:if>
                        <c:if test="${sessionScope.member == null}">
                            <a href="writeReport?reportSuspector=${memberInfo.memberId}"
                               class="btn btn-primary btn-sm" style="margin-left:61%" onclick="checkSignIn(event)">Report</a>
                        </c:if>

                    </div>
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
                                ${memberInfo.memberNickname}
                            </div>
                            <div class="memberId" id="memberId"
                                 style="font-size: 20px; font-weight:20px">${memberInfo.memberId}
                            </div>
                        </div>
                    </div>

                    <hr
                            style="border-top: 0.1px solid #cdcdcd; margin-top: 5px; margin-bottom: 10px;">

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
                        <i class="ion-ios-color-wand"
                           style="margin-right: 12px; font-size: 18px;"></i>
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

                                <fieldset class="rate">
                                    <input type="radio" id="rating10" name="reviewScore"
                                           value="5"><label for="rating10" title="5점"></label>
                                    <input type="radio" id="rating9" name="reviewScore"
                                           value="4.5"><label class="half" for="rating9"
                                                              title="4.5점"></label>
                                    <input type="radio" id="rating8" name="reviewScore"
                                           value="4"><label for="rating8" title="4점"></label>
                                    <input type="radio" id="rating7" name="reviewScore"
                                           value="3.5"><label class="half" for="rating7"
                                                              title="3.5점"></label>
                                    <input type="radio" id="rating6" name="reviewScore"
                                           value="3"><label for="rating6" title="3점"></label>
                                    <input type="radio" id="rating5" name="reviewScore"
                                           value="2.5"><label class="half" for="rating5"
                                                              title="2.5점"></label>
                                    <input type="radio" id="rating4" name="reviewScore"
                                           value="2"><label for="rating4" title="2점"></label>
                                    <input type="radio" id="rating3" name="reviewScore"
                                           value="1.5"><label class="half" for="rating3"
                                                              title="1.5점"></label>
                                    <input type="radio" id="rating2" name="reviewScore"
                                           value="1"><label for="rating2" title="1점"></label>
                                    <input type="radio" id="rating1" name="reviewScore"
                                           value="0.5"><label class="half" for="rating1"
                                                              title="0.5점"></label>

                                </fieldset>


                                <c:if test="${sessionScope.member == null}">
                                                        <textarea class="form-control" name="reviewContent"
                                                                  placeholder="please SignIn to write review"
                                                                  onclick="checkSignIn(event)"></textarea>
                                </c:if>
                                <c:if test="${sessionScope.member != null}">
                                    <textarea class="form-control" name="reviewContent"></textarea>
                                </c:if>

                            </div>
                            <c:if test="${sessionScope.member == null}">
                                <div class="form-group col-md-12" style="text-align: right;">
                                    <button class="btn btn-primary btn-rounded"
                                            id="writeReviewFalse"
                                            onclick="checkSignIn(event)">Write
                                    </button>
                                </div>
                            </c:if>

                            <c:if test="${sessionScope.member != null}">
                                <div class="form-group col-md-12" style="text-align: right;">
                                    <button class="btn btn-primary btn-rounded"
                                            id="writeReview">Write
                                    </button>
                                </div>
                            </c:if>

                        </form>
                        <c:if test="${not empty reviewList}">
                            <h5 class="title">${reviewList[0].reviewTotalCnt} REVIEWS</h5>
                        </c:if>
                        <c:if test="${empty reviewList}">
                            <h5 class="title">0 REVIEWS</h5>
                        </c:if>


                        <div class="comments">
                            <div class="comment-list">
                                <c:if test="${empty reviewList}">
                                    <h4 id="emptyReview">Write first review !</h4>
                                </c:if>
                                <div class="writeReviewContainer">


                                </div>
                                <c:forEach items="${reviewList}" var="reviewData" varStatus="loop">

                                    <div class="item"
                                         style="border:0px; padding:0px; margin-bottom:0%;"
                                         id="item_${reviewData.reviewId}">


                                        <c:if test="${not empty reviewList}">


                                            <!-- 두 번째 목록부터는 위쪽에 <div class="line thin"></div> 추가 -->
                                            <c:if test="${loop.index > 0}">
                                                <div class="line thin" style="margin:0%;"
                                                     id="line_thin_${reviewData.reviewId}"></div>
                                            </c:if>
                                            <div class="rate">
                                                <!-- 별점을 표시하기 위한 fieldset -->
                                                <fieldset class="rate"
                                                          id="reviewScore_${reviewData.reviewId}"
                                                          style="pointer-events: none;">
                                                    <!-- 별점에 따라 해당하는 input 태그를 체크 -->
                                                    <input type="radio"
                                                           id="rating10_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="5" ${reviewData.reviewScore=='5.0'
                                                            ? 'checked' : '' }>
                                                    <label for="rating10_${reviewData.reviewId}"
                                                           title="5점"></label>
                                                    <input type="radio"
                                                           id="rating9_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="4.5" ${reviewData.reviewScore=='4.5'
                                                            ? 'checked' : '' }><label class="half"
                                                                                      for="rating9_${reviewData.reviewId}"
                                                                                      title="4.5점"></label>
                                                    <input type="radio"
                                                           id="rating8_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="4" ${reviewData.reviewScore=='4.0'
                                                            ? 'checked' : '' }><label
                                                        for="rating8_${reviewData.reviewId}"
                                                        title="4점"></label>
                                                    <input type="radio"
                                                           id="rating7_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="3.5" ${reviewData.reviewScore=='3.5'
                                                            ? 'checked' : '' }><label class="half"
                                                                                      for="rating7_${reviewData.reviewId}"
                                                                                      title="3.5점"></label>
                                                    <input type="radio"
                                                           id="rating6_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="3" ${reviewData.reviewScore=='3.0'
                                                            ? 'checked' : '' }><label
                                                        for="rating6_${reviewData.reviewId}"
                                                        title="3점"></label>
                                                    <input type="radio"
                                                           id="rating5_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="2.5" ${reviewData.reviewScore=='2.5'
                                                            ? 'checked' : '' }><label class="half"
                                                                                      for="rating5_${reviewData.reviewId}"
                                                                                      title="2.5점"></label>
                                                    <input type="radio"
                                                           id="rating4_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="2" ${reviewData.reviewScore=='2.0'
                                                            ? 'checked' : '' }><label
                                                        for="rating4_${reviewData.reviewId}"
                                                        title="2점"></label>
                                                    <input type="radio"
                                                           id="rating3_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="1.5" ${reviewData.reviewScore=='1.5'
                                                            ? 'checked' : '' }><label class="half"
                                                                                      for="rating3_${reviewData.reviewId}"
                                                                                      title="1.5점"></label>
                                                    <input type="radio"
                                                           id="rating2_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="1" ${reviewData.reviewScore=='1.0'
                                                            ? 'checked' : '' }><label
                                                        for="rating2_${reviewData.reviewId}"
                                                        title="1점"></label>
                                                    <input type="radio"
                                                           id="rating1_${reviewData.reviewId}"
                                                           name="reviewScoreShow_${reviewData.reviewId}"
                                                           value="0.5" ${reviewData.reviewScore=='0.5'
                                                            ? 'checked' : '' }><label class="half"
                                                                                      for="rating1_${reviewData.reviewId}"
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
                                                        <a href="/memberPage?memberId=${reviewData.memberId}"
                                                           style="font-size:14px;">${reviewData.memberId}</a>
                                                    </h5>
                                                    <div class="time">${reviewData.reviewDate}</div>
                                                    <div id="reviewContent_${reviewData.reviewId}"
                                                         class="description"
                                                         style="word-break: break-all;">
                                                            ${reviewData.reviewContent}
                                                    </div>
                                                </div>
                                            </div>
                                            <footer
                                                    style="display: flex; flex-wrap: wrap; justify-content: flex-end;">
                                                <!-- 작성자가 자기 댓글을 수정 또는 삭제를 할 경우 -->
                                                <c:if test="${reviewData.memberId == member}">
                                                    <!-- 수정 버튼 -->
                                                    <i id="updateIcon_${reviewData.reviewId}"
                                                       class="ion-android-refresh btn-icon-like"
                                                       style="cursor: pointer; font-size: 25px; margin-right: 10px;"
                                                       onclick="reviewUpdate('${reviewData.reviewId}', '${reviewData.reviewContent}')"></i>
                                                    <i id="deleteIcon_${reviewData.reviewId}"
                                                       class="ion-trash-a btn-icon-like"
                                                       style="cursor: pointer; font-size: 25px; margin-right: 10px;"
                                                       onclick="reviewDelete('${reviewData.reviewId}')"></i>

                                                </c:if>

                                            </footer>

                                        </c:if>


                                    </div>
                                </c:forEach>
                                <div class="moreReviewContainer">

                                </div>

                            </div>
                        </div>

                        <c:if test="${reviewList[0].reviewTotalCnt > 10}">
                            <button class="btn btn-magz btn-sm" id="moreReview">More</button>
                        </c:if>


                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

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

    //이메일 마스킹
    document.addEventListener("DOMContentLoaded", function () {
        var memberIdElement = document.getElementById('memberId');
        var memberId = memberIdElement.innerText.trim();
        var atIndex = memberId.indexOf('@');
        if (atIndex !== -1) {
            var maskedId = memberId.substring(0, atIndex).replace(/./g, '*') + memberId.substring(atIndex);
            memberIdElement.innerText = maskedId;
        }
    });

    //전화번호 마스킹
    document.addEventListener("DOMContentLoaded", function () {
        var memberPhElement = document.getElementById('memberPh');
        var memberPh = memberPhElement.innerText.trim();
        if (memberPh.length === 11) {
            var maskedPh = memberPh.substring(0, 3) + '-****-' + memberPh.substring(7);
            memberPhElement.innerText = maskedPh;
        }
    });
    if (document.getElementById("writeReview")) {
        document.getElementById("writeReview").addEventListener("click", function (event) {
            event.preventDefault();
            writeReview();
        });
    }


    function writeReview() {
        if (!checkReview(document.getElementsByName('reviewScore'), document.getElementsByName('reviewContent')[0].value)) {
            return false;
        }
        console.log("reviewScore저장되는게 뭘까~~" + $('#writeReviewForm').serialize());

        // 선택된 라디오 버튼의 ID를 저장할 배열
        var checkedRadioIds = [];
        var member = "${sessionScope.member}";


        // 현재 선택된 라디오 버튼 중에서 input type이 radio이고 name이 reviewScore가 아닌 것을 찾아서
        $('input[type="radio"]:not([name="reviewScore"])').each(function () {
            // 만약 현재 라디오 버튼이 체크되어 있다면
            if ($(this).prop('checked')) {
                // 각각의 ID를 배열에 저장
                checkedRadioIds.push($(this).attr('id'));
            }
        });
        // 결과 출력
        console.log("reviewScore가아닌것:" + checkedRadioIds);

        $.ajax({
            type: 'POST',
            url: '/writeReview',
            data: $('#writeReviewForm').serialize(), // 폼 데이터 전송
            dataType: 'json',
            success: function (reviewData) {
                if (reviewData) {
                    // 별점 초기화
                    $('input[name^="reviewScore"]').prop('checked', false);
                    console.log($('input[name^="reviewScore"]'));
                    // 텍스트 영역 비우기
                    $('textarea[name="reviewContent"]').val('');
                    //h4 문구 지우기
                    $("#emptyReview").text("");

                    var currentReviewTotalCnt = parseInt($('.title').text().match(/\d+/)[0]); // 현재 리뷰 총 개수 가져오기
                    $('.title').text(currentReviewTotalCnt + 1 + ' REVIEWS'); // 업데이트된 리뷰 총 개수 반영


                    var html = '<div class="item" style="border:0px; padding:0px; margin-bottom:0%;" id="item_' + reviewData.reviewId + '">' +
                        '<div class="rate">' +
                        '<fieldset class="rate" id="reviewScore_' + reviewData.reviewId + '" style="pointer-events: none;">' +
                        '<input type="radio" id="rating10_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="5" ' + (reviewData.reviewScore == '5.0' ? 'checked' : '') + '><label for="rating10_' + +reviewData.reviewId + '" title="5점"></label>' +
                        '<input type="radio" id="rating9_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="4.5" ' + (reviewData.reviewScore == '4.5' ? 'checked' : '') + '><label class="half" for="rating9_' + reviewData.reviewId + '" title="4.5점"></label>' +
                        '<input type="radio" id="rating8_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="4" ' + (reviewData.reviewScore == '4.0' ? 'checked' : '') + '><label for="rating8_' + reviewData.reviewId + '" title="4점"></label>' +
                        '<input type="radio" id="rating7_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="3.5" ' + (reviewData.reviewScore == '3.5' ? 'checked' : '') + '><label class="half" for="rating7_' + reviewData.reviewId + '" title="3.5점"></label>' +
                        '<input type="radio" id="rating6_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="3" ' + (reviewData.reviewScore == '3.0' ? 'checked' : '') + '><label for="rating6_' + reviewData.reviewId + '" title="3점"></label>' +
                        '<input type="radio" id="rating5_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="2.5" ' + (reviewData.reviewScore == '2.5' ? 'checked' : '') + '><label class="half" for="rating5_' + reviewData.reviewId + '" title="2.5점"></label>' +
                        '<input type="radio" id="rating4_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="2" ' + (reviewData.reviewScore == '2.0' ? 'checked' : '') + '><label for="rating4_' + reviewData.reviewId + '" title="2점"></label>' +
                        '<input type="radio" id="rating3_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="1.5" ' + (reviewData.reviewScore == '1.5' ? 'checked' : '') + '><label class="half" for="rating3_' + reviewData.reviewId + '" title="1.5점"></label>' +
                        '<input type="radio" id="rating2_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="1" ' + (reviewData.reviewScore == '1.0' ? 'checked' : '') + '><label for="rating2_' + reviewData.reviewId + '" title="1점"></label>' +
                        '<input type="radio" id="rating1_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="0.5" ' + (reviewData.reviewScore == '0.5' ? 'checked' : '') + '><label class="half" for="rating1_' + reviewData.reviewId + '" title="0.5점"></label>' +
                        '</fieldset>' +
                        '</div>' +
                        '<div class="user" style="margin-bottom:0%;">' +
                        '<figure>' +
                        '<img src="profileImg/' + reviewData.profileImgName + '" style="width:100%; height:100%; object-fit:cover">' +
                        '</figure>' +
                        '<div class="details">' +
                        '<h5 class="name">' + reviewData.memberNickname + ' <a href="/memberPage?memberId=' + reviewData.memberId + '"style="font-size:14px;">' + reviewData.memberId + '</a></h5>' +
                        '<div class="time">' + reviewData.reviewDate + '</div>' +
                        '<div id="reviewContent_' + reviewData.reviewId + '" class="description" style="word-break: break-all;">' + reviewData.reviewContent + '</div>' +
                        '</div>' +
                        '</div>' +
                        '<footer style="display: flex; flex-wrap: wrap; justify-content: flex-end;">';

                    // 작성자가 자신의 댓글을 수정 또는 삭제할 경우에 대한 코드 추가
                    if (reviewData.memberId == member) {
                        html += '<i id="updateIcon_' + reviewData.reviewId + '" class="ion-android-refresh btn-icon-like" style="cursor: pointer; font-size: 25px; margin-right: 10px;" onclick="reviewUpdate(\'' + reviewData.reviewId + '\', \'' + reviewData.reviewContent + '\')"></i>' +
                            '<i id="deleteIcon_' + reviewData.reviewId + '" class="ion-trash-a btn-icon-like" style="cursor: pointer; font-size: 25px; margin-right: 10px;" onclick="reviewDelete(\'' + reviewData.reviewId + '\')"></i>';
                    }
                    if (currentReviewTotalCnt >= 1) {
                        linethin = '<div class="line thin" style="margin:0%;" id="line_thin_' + reviewData.reviewId + '"></div>';
                    } else {
                        linethin = '';
                    }

                    html += '</footer>' +
                        linethin +
                        '</div>';


                    $('.writeReviewContainer').prepend(html);


                    // 저장된 ID를 가진 라디오 버튼을 찾아서 checked: true로 설정
                    checkedRadioIds.forEach(function (id) {
                        $('#' + id).prop('checked', true);
                    });
                    // 평점에 대한 prop 설정


                } else {
                    swal("WARNING", "작성실패", "error", {
                        button: "OK",
                    });
                }
            },
            error: function () {
                alert('서버 오류가 발생했습니다.');
            }
        });
    }


    var deletedCnt = 0;
    var reviewStart = 10; // 초기값 설정

    if (document.getElementById("moreReview")) {
        // User Hold 버튼을 클릭했을 때의 이벤트 처리
        document.getElementById("moreReview").addEventListener("click", function (event) {
            //삭제된 데이터가 있어도 처음 출력된 것의 다음 10개를 가져옴
            reviewStart -= deletedCnt;
            var checkedRadioIds = [];
            var member = "${sessionScope.member}";
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
                        data.forEach(function (reviewData) {
                            var html = '<div class="item" style="border:0px; padding:0px; margin-bottom:0%;" id="item_' + reviewData.reviewId + '">' +
                                '<div class="line thin" style="margin:0%;" id="line_thin_' + reviewData.reviewId + '"></div>' +
                                '<div class="rate">' +
                                '<fieldset class="rate" id="reviewScore_' + reviewData.reviewId + '" style="pointer-events: none;">' +
                                '<input type="radio" id="rating10_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="5" ' + (reviewData.reviewScore == '5.0' ? 'checked' : '') + '><label for="rating10_' + +reviewData.reviewId + '" title="5점"></label>' +
                                '<input type="radio" id="rating9_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="4.5" ' + (reviewData.reviewScore == '4.5' ? 'checked' : '') + '><label class="half" for="rating9_' + reviewData.reviewId + '" title="4.5점"></label>' +
                                '<input type="radio" id="rating8_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="4" ' + (reviewData.reviewScore == '4.0' ? 'checked' : '') + '><label for="rating8_' + reviewData.reviewId + '" title="4점"></label>' +
                                '<input type="radio" id="rating7_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="3.5" ' + (reviewData.reviewScore == '3.5' ? 'checked' : '') + '><label class="half" for="rating7_' + reviewData.reviewId + '" title="3.5점"></label>' +
                                '<input type="radio" id="rating6_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="3" ' + (reviewData.reviewScore == '3.0' ? 'checked' : '') + '><label for="rating6_' + reviewData.reviewId + '" title="3점"></label>' +
                                '<input type="radio" id="rating5_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="2.5" ' + (reviewData.reviewScore == '2.5' ? 'checked' : '') + '><label class="half" for="rating5_' + reviewData.reviewId + '" title="2.5점"></label>' +
                                '<input type="radio" id="rating4_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="2" ' + (reviewData.reviewScore == '2.0' ? 'checked' : '') + '><label for="rating4_' + reviewData.reviewId + '" title="2점"></label>' +
                                '<input type="radio" id="rating3_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="1.5" ' + (reviewData.reviewScore == '1.5' ? 'checked' : '') + '><label class="half" for="rating3_' + reviewData.reviewId + '" title="1.5점"></label>' +
                                '<input type="radio" id="rating2_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="1" ' + (reviewData.reviewScore == '1.0' ? 'checked' : '') + '><label for="rating2_' + reviewData.reviewId + '" title="1점"></label>' +
                                '<input type="radio" id="rating1_' + reviewData.reviewId + '" name="reviewScoreShow_' + reviewData.reviewId + '" value="0.5" ' + (reviewData.reviewScore == '0.5' ? 'checked' : '') + '><label class="half" for="rating1_' + reviewData.reviewId + '" title="0.5점"></label>' +
                                '</fieldset>' +
                                '</div>' +
                                '<div class="user" style="margin-bottom:0%;">' +
                                '<figure>' +
                                '<img src="profileImg/' + reviewData.profileImgName + '" style="width:100%; height:100%; object-fit:cover">' +
                                '</figure>' +
                                '<div class="details">' +
                                '<h5 class="name">' + reviewData.memberNickname + ' <a href="/memberPage?memberId=' + reviewData.memberId + '"style="font-size:14px;">' + reviewData.memberId + '</a></h5>' +
                                '<div class="time">' + reviewData.reviewDate + '</div>' +
                                '<div id="reviewContent_' + reviewData.reviewId + '" class="description" style="word-break: break-all;">' + reviewData.reviewContent + '</div>' +
                                '</div>' +
                                '</div>' +
                                '<footer style="display: flex; flex-wrap: wrap; justify-content: flex-end;">';
                            // 작성자가 자신의 댓글을 수정 또는 삭제할 경우에 대한 코드 추가
                            if (reviewData.memberId == member) {
                                html += '<i id="updateIcon_' + reviewData.reviewId + '" class="ion-android-refresh btn-icon-like" style="cursor: pointer; font-size: 25px; margin-right: 10px;" onclick="reviewUpdate(\'' + reviewData.reviewId + '\', \'' + reviewData.reviewContent + '\')"></i>' +
                                    '<i id="deleteIcon_' + reviewData.reviewId + '" class="ion-trash-a btn-icon-like" style="cursor: pointer; font-size: 25px; margin-right: 10px;" onclick="reviewDelete(\'' + reviewData.reviewId + '\')"></i>';
                            }

                            html += '</footer>' +
                                '</div>';

                            $('.moreReviewContainer').append(html);


                        });
                        // 저장된 ID를 가진 라디오 버튼을 찾아서 checked: true로 설정
                        checkedRadioIds.forEach(function (id) {
                            $('#' + id).prop('checked', true);
                        });

                        console.log(data[0]);


                        reviewStart += 10;
                        var restReviewCnt = data[0].reviewTotalCnt - reviewStart;
                        console.log(restReviewCnt);
                        // reviewTotalCnt를 사용하여 추가적인 작업을 수행
                        if (restReviewCnt > 0) {
                            moreReviewButton.style.display = "block";

                            //삭제된 개수 초기화
                            deletedCnt = 0;
                        }
                    }
                }
            });
        });
    }

    function checkSignIn(event) {
        event.preventDefault();
        swal({
            title: "fail",
            text: "로그인 후 이용해주세요.",
            type: "error",
            showCancelButton: false,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "OK",
            closeOnConfirm: true
        }, function () {
            // "OK" 버튼 누르면 실행될 코드
            window.location.href = "/signIn"; // 로그인 페이지로 이동
        });
    }

    function checkReview(reviewScore, reviewContent) {
        var radioChecked = false;
        var textAreaEmpty = false;

        // Check if any radio button is checked
        var radioButtons = reviewScore;
        for (var i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].checked) {
                radioChecked = true;
                break;
            }
        }

        // Check if the text area is empty
        var Content = reviewContent;
        if (Content.trim() === '') {
            textAreaEmpty = true;
        }

        // Display alert if necessary
        if (!radioChecked || textAreaEmpty) {
            swal({
                title: "fail",
                text: "별점과 내용을 입력해주세요",
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "OK",
                closeOnConfirm: true
            });
            return false; // Prevent form submission
        }
        return true;
    }


    function reviewUpdate(reviewId, reviewContent) {
        console.log("${sessionScope.member}");
        console.log("update 진입");
        console.log(reviewId);
        console.log(reviewContent);


        // 기존 아이콘을 찾아서 변경
        var updateIcon = document.getElementById("updateIcon_" + reviewId);
        if (updateIcon) {
            // 새로운 아이콘으로 변경
            updateIcon.id = "saveIcon_" + reviewId; // id 변경
            updateIcon.className = "ion-compose btn-icon-like"; // 클래스 변경
            updateIcon.onclick = function () {
                save(reviewId, reviewContent); // 클릭 이벤트 변경 // 클릭 이벤트 변경
            }
        }


        // 현재 리뷰의 내용을 가져옴
        var divContent = $('#reviewContent_' + reviewId).text();
        var textarea = $('<textarea class="form-control" style="height:90px;">').val(divContent.trim()).attr('rows', 5).attr('id', 'textarea_' + reviewId);
        $('#reviewContent_' + reviewId).empty().append(textarea);
        var reviewScore = $('#reviewScore_' + reviewId);


        // 기존에 체크되어 있는 라디오 버튼 해제
        var radioButtons = document.getElementsByName("reviewScoreShow_" + reviewId);
        for (var i = 0; i < radioButtons.length; i++) {
            radioButtons[i].checked = false;
        }

        reviewScore.css('pointer-events', 'visible');

        reviewScore.click(function () {
            console.log("Review score element clicked!");
        });


    }


    function save(reviewId, reviewContent) {

        // 새롭게 입력받은 별점을 가져오기
        console.log("save 진입");
        console.log(reviewContent);


        var radioChecked = false;
        var textAreaEmpty = false;
        // 각 라디오 버튼에 대한 name을 동적으로 생성합니다.
        var radioName = "reviewScoreShow_" + reviewId;
        var radioGroup = $('input[name="' + radioName + '"]');

        for (var i = 0; i < radioGroup.length; i++) {
            if (radioGroup[i].checked) {
                radioChecked = true;
                break;
            }
        }

        // textarea 요소 선택
        var textarea = $('#textarea_' + reviewId);

        // textarea가 비어 있는지 확인
        if (textarea.val().trim() === '') {
            // 비어 있으면 경고창 표시
            textAreaEmpty = true;
        }

        // Display alert if necessary
        if (!radioChecked || textAreaEmpty) {
            swal({
                title: "fail",
                text: "별점과 내용을 입력해주세요",
                type: "error",
                showCancelButton: false,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "OK",
                closeOnConfirm: true
            });
            return false; // Prevent form submission
        }


        var selectedValue = null;

        radioGroup.each(function () {
            if ($(this).is(':checked')) {
                selectedValue = $(this).val();
                return false;
            }
        });

        var updatedScore = selectedValue;
        var updatedContent = $('#textarea_' + reviewId).val();
        console.log(updatedContent);
        console.log(updatedScore);

        $.ajax({
            url: "/updateReview",
            type: "POST",
            data: {
                'reviewId': reviewId,
                'reviewScore': updatedScore,
                'reviewContent': updatedContent
            },
            dataType: 'text',
            success: function (data) {

                var reviewData = JSON.parse(data);

                radioGroup.each(function () {
                    if ($(this).val() == data.reviewScore) {
                        $(this).prop('checked', true);
                        return false; // 선택된 것을 찾았으므로 반복을 종료합니다.
                    }
                });

                // 동적으로 HTML 요소 생성
                var reviewContentDiv = $('<div>').addClass('description').text(reviewData.reviewContent);

                // 기존의 내용을 비우고 새로운 내용으로 채웁니다.
                $('#reviewContent_' + reviewId).empty().append(reviewContentDiv);
                // 버튼의 텍스트를 '수정'으로 변경

                // 기존 아이콘을 찾아서 변경
                var saveIcon = document.getElementById("saveIcon_" + reviewId);
                if (saveIcon) {
                    // 새로운 아이콘으로 변경
                    saveIcon.id = "updateIcon_" + reviewId; // id 변경
                    saveIcon.className = "ion-android-refresh btn-icon-like"; // 클래스 변경
                    saveIcon.onclick = function () {
                        reviewUpdate(reviewId, reviewContent); // 클릭 이벤트 변경 // 클릭 이벤트 변경
                    }
                }
                var reviewScore = $('#reviewScore_' + reviewId);
                reviewScore.css('pointer-events', 'none');


            },
            error: function (error) {
                console.error('에러 발생:', error);
            }
        });


    }

    function reviewDelete(reviewId) {
        console.log("delete진입");
        $.ajax({
            url: "/deleteReview",
            type: "POST",
            data: {
                'reviewId': reviewId
            },
            dataType: 'text',
            success: function (response) {
                // 성공적으로 삭제되었다는 메시지를 콘솔에 출력
                console.log('삭제 완료:', response);
                deletedCnt++;
                // UI에서 삭제된 댓글 제거 또는 사용자에게 성공 알림
                // 예: $('#commentSection_' + commentId).remove();
                swal({
                    title: "success",
                    text: "리뷰가 성공적으로 삭제되었습니다.",
                    type: "success"
                }, function () {

                    $('#item_' + reviewId).remove();

                    var nextReviewId = reviewId - 1;
                    console.log(nextReviewId);
                    $('#line_thin_' + nextReviewId).remove();

                    var currentReviewTotalCnt = parseInt($('.title').text().match(/\d+/)[0]); // 현재 리뷰 총 개수 가져오기
                    $('.title').text(currentReviewTotalCnt - 1 + ' REVIEWS'); // 업데이트된 리뷰 총 개수 반영


                    if (currentReviewTotalCnt <= 1) {
                        $("#emptyReview").text("Write first review !");
                    }


                });
            },
            error: function (error) {
                console.error('에러 발생:', error);
                // 사용자에게 에러 발생을 알림
                swal({
                    title: "fail",
                    text: "리뷰를 삭제하는 도중 에러가 발생했습니다.",
                    type: "error",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "OK",
                    closeOnConfirm: true
                });
            }
        });
    }


</script>


</html>