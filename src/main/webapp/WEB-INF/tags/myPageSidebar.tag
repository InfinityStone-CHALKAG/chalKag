<%@ tag pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                 <div class="value"><fmt:formatNumber value="${memberInfo.currentScore}" pattern="#,##0.0"/></div>
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
                        <div class="detail" style="text-align: center">

                <a style="text-transform: uppercase; color: #F73F52; text-decoration-line: none;">
                        ${memberInfo.currentExp} / ${memberInfo.currentNextExp}
                </a>

            </div>
                    <div class="featured-author-quote"
                        style="font-weight: bold; font-family: 'inherit'; margin-top: 10px;">
                        INTRODUCTION
                    </div>
                    <div class="featured-author-quote" id="Introdudction">
                        <c:if test="${not empty memberInfo.memberIntroduction}">
                            "${memberInfo.memberIntroduction}"
                        </c:if>
                        <c:if test="${empty memberInfo.memberIntroduction}">
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
                            <li><a href="resign">RESIGN</a></li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</aside>