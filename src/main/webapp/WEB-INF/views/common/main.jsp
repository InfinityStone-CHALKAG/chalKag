<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <chalKagTags:webCss/>
</head>

<style>
    input[type='range']::-webkit-slider-thumb {
        opacity: 0;
    }

    input[type='range'] {
        pointer-events: none;
    }

    input[type="range"] {
        accent-color: #F73F52;
    }

    /* 숨길 Dot 스타일링 */
    .owl-dots {
        display: none !important;
    }
</style>

<body class="skin-orange">

<chalKagTags:webJs/>

<chalKagTags:webHeader/>

<section class="home">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-sm-12 col-xs-12">
                <div class="headline">
                    <div class="nav" id="headline-nav">
                        <a class="left carousel-control" role="button" data-slide="prev">
                            <span class="ion-ios-arrow-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" role="button" data-slide="next">
                            <span class="ion-ios-arrow-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <div class="owl-carousel owl-theme" id="headline">
                        <div class="item">
                            <a href="#">
                                <div class="badge">Notice!</div>
                                Vestibulum ante ipsum primis in faucibus orci
                            </a>
                        </div>
                        <div class="item">
                            <a href="#">Ut rutrum sodales mauris ut suscipit</a>
                        </div>
                    </div>
                </div>
                <div class="owl-carousel owl-theme slide" id="featured">
                    <div class="item">
                        <article class="featured">
                            <div class="overlay"></div>
                            <figure>
                                <img src="css/user/images/news/img04.jpg" alt="Sample Article">
                            </figure>
                            <div class="details">
                                <div class="category"><a href="category.html">Computer</a></div>
                                <h1><a>Phasellus iaculis quam sed est elementum vel ornare ligula
                                    venenatis</a></h1>
                                <div class="time">December 26, 2016</div>
                            </div>
                        </article>
                    </div>
                    <div class="item">
                        <article class="featured">
                            <div class="overlay"></div>
                            <figure>
                                <img src="css/user/images/news/img14.jpg" alt="Sample Article">
                            </figure>
                            <div class="details">
                                <div class="category"><a href="category.html">Travel</a></div>
                                <h1><a href="single.html">Class aptent taciti sociosqu ad litora
                                    torquent per conubia
                                    nostra</a></h1>
                                <div class="time">December 10, 2016</div>
                            </div>
                        </article>
                    </div>
                    <div class="item">
                        <article class="featured">
                            <div class="overlay"></div>
                            <figure>
                                <img src="css/user/images/news/img13.jpg" alt="Sample Article">
                            </figure>
                            <div class="details">
                                <div class="category"><a href="category.html">International</a></div>
                                <h1><a href="single.html">Maecenas accumsan tortor ut velit pharetra
                                    mollis</a></h1>
                                <div class="time">October 12, 2016</div>
                            </div>
                        </article>
                    </div>
                    <div class="item">
                        <article class="featured">
                            <div class="overlay"></div>
                            <figure>
                                <img src="css/user/images/news/img05.jpg" alt="Sample Article">
                            </figure>
                            <div class="details">
                                <div class="category"><a href="category.html">Lifestyle</a></div>
                                <h1><a href="single.html">Mauris elementum libero at pharetra auctor
                                    Fusce ullamcorper
                                    elit</a></h1>
                                <div class="time">November 27, 2016</div>
                            </div>
                        </article>
                    </div>
                </div>
                <div class="line">
                    <div>LATEST POST</div>
                </div>
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="row">
                            <article class="article col-md-12">
                                <div class="inner">
                                    <figure>
                                        <a href="headHuntPostSingle?headHuntPostId=${latestHeadHuntPost.headHuntPostId}">
                                            <img class="latestPostImg" src="postImg/${latestHeadHuntPost.postImgName}">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <div class="detail">
                                            <div class="category"><a href="headHuntPostList">HEADHUNTPOST</a>
                                            </div>
                                            <div class="time">${latestHeadHuntPost.memberNickname}</div>
                                        </div>
                                        <h2>
                                            <a href="headHuntPostSingle?headHuntPostId=${latestHeadHuntPost.headHuntPostId}">${latestHeadHuntPost.headHuntPostTitle}</a>
                                        </h2>
                                        <pre class="latestPostContent">${latestHeadHuntPost.headHuntPostContent}</pre>
                                        <footer>
                                            <a href="#" class="love"><i
                                                    class="ion-android-favorite-outline"></i>
                                                <div>${latestHeadHuntPost.recommendCnt}</div>
                                            </a>
                                            <a class="btn btn-primary more"
                                               href="headHuntPostSingle?headHuntPostId=${latestHeadHuntPost.headHuntPostId}">
                                                <div>More</div>
                                                <div><i class="ion-ios-arrow-thin-right"></i></div>
                                            </a>
                                        </footer>
                                    </div>
                                </div>
                            </article>
                            <article class="article col-md-12">
                                <div class="inner">
                                    <figure>
                                        <a href="marketPostSingle?marketPostId=${latestMarketPost.marketPostId}">
                                            <img class="latestPostImg" src="postImg/${latestMarketPost.postImgName}"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <div class="detail">
                                            <div class="category"><a href="marketPostList">MARKETPOST</a>
                                            </div>
                                            <div class="time">${latestMarketPost.memberNickname}</div>
                                        </div>
                                        <h2>
                                            <a href="marketPostSingle?marketPostId=${latestMarketPost.marketPostId}">${latestMarketPost.marketPostTitle}</a>
                                        </h2>
                                        <p class="latestPostContent">${latestMarketPost.marketPostContent}
                                        </p>
                                        <footer>
                                            <a href="#" class="love"><i
                                                    class="ion-android-favorite-outline"></i>
                                                <div>${latestMarketPost.recommendCnt}</div>
                                            </a>
                                            <a class="btn btn-primary more"
                                               href="marketPostSingle?marketPostId=${latestMarketPost.marketPostId}">
                                                <div>More</div>
                                                <div><i class="ion-ios-arrow-thin-right"></i></div>
                                            </a>
                                        </footer>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="row">
                            <article class="article col-md-12">
                                <div class="inner">
                                    <figure>
                                        <a href="jobHuntPostSingle?jobHuntPostId=${latestJobHuntPostId.jobHuntPostId}">
                                            <img class="latestPostImg" src="postImg/${latestJobHuntPost.postImgName}">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <div class="detail">
                                            <div class="category"><a href="jobHuntPostList">JOBHUNTPOST</a>
                                            </div>
                                            <div class="time">${latestJobHuntPost.memberNickname}</div>
                                        </div>
                                        <h2>
                                            <a href="jobHuntPostSingle?jobHuntPostId=${latestJobHuntPostId.jobHuntPostId}">${latestJobHuntPost.jobHuntPostTitle}</a>
                                        </h2>
                                        <p class="latestPostContent">${latestJobHuntPost.jobHuntPostContent}
                                        </p>
                                        <footer>
                                            <a href="#" class="love"><i
                                                    class="ion-android-favorite-outline"></i>
                                                <div>${latestJobHuntPost.recommendCnt}</div>
                                            </a>
                                            <a class="btn btn-primary more"
                                               href="jobHuntPostSingle?jobHuntPostId=${latestJobHuntPostId.jobHuntPostId}">
                                                <div>More</div>
                                                <div><i class="ion-ios-arrow-thin-right"></i></div>
                                            </a>
                                        </footer>
                                    </div>
                                </div>
                            </article>
                            <article class="article col-md-12">
                                <div class="inner">
                                    <figure>
                                        <a href="freePostSingle?freePostId=${latestFreePost.freePostId}">
                                            <img class="latestPostImg" src="postImg/${latestFreePost.postImgName}"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <div class="detail">
                                            <div class="category"><a href="freePostList">FREEPOST</a>
                                            </div>
                                            <div class="time">${latestFreePost.memberNickname}</div>
                                        </div>
                                        <h2>
                                            <a href="freePostSingle?freePostId=${latestFreePost.freePostId}">${latestFreePost.freePostTitle}</a>
                                        </h2>
                                        <p class="latestPostContent">${latestFreePost.freePostContent}
                                        </p>
                                        <footer>
                                            <a href="#" class="love"><i
                                                    class="ion-android-favorite-outline"></i>
                                                <div>${latestFreePost.recommentCnt}</div>
                                            </a>
                                            <a class="btn btn-primary more"
                                               href="freePostSingle?freePostId=${latestFreePost.freePostId}">
                                                <div>More</div>
                                                <div><i class="ion-ios-arrow-thin-right"></i></div>
                                            </a>
                                        </footer>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </div>
                </div>
                <div class="banner">
                    <a href="#">
                        <img src="css/user/images/ads.png" alt="Sample Article">
                    </a>
                </div>
                <div class="line transparent little"></div>
                <div class="row">
                    <div class="col-md-6 col-sm-6 trending-tags">
                        <h1 class="title-col">Rank</h1>
                        <div class="body-col">
                            <ol class="tags-list">
                                <c:forEach var="member" items="${levelRank}" varStatus="status">
                                    <li><a href="memberPage?memberId=${member.memberId}">${member.memberNickname}
                                        (LV.${member.currentLevel})</a>
                                    </li>
                                </c:forEach>
                            </ol>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <h1 class="title-col">
                            Hot Post
                            <div class="carousel-nav" id="hot-news-nav">
                                <div class="prev">
                                    <i class="ion-ios-arrow-left"></i>
                                </div>
                                <div class="next">
                                    <i class="ion-ios-arrow-right"></i>
                                </div>
                            </div>
                        </h1>
                        <div class="body-col vertical-slider" data-max="4" data-nav="#hot-news-nav"
                             data-item="article">
                            <article class="article-mini">
                                <div class="inner">
                                    <figure>
                                        <a href="single.html">
                                            <img src="css/user/images/news/img09.jpg"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <h1><a href="single.html">Duis aute irure dolor in reprehenderit
                                            in voluptate
                                            velit</a></h1>
                                        <div class="detail">
                                            <div class="category"><a href="category.html">Lifestyle</a>
                                            </div>
                                            <div class="time">December 22, 2016</div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="article-mini">
                                <div class="inner">
                                    <figure>
                                        <a href="single.html">
                                            <img src="css/user/images/news/img01.jpg"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <h1><a href="single.html">Duis aute irure dolor in reprehenderit
                                            in voluptate
                                            velit</a></h1>
                                        <div class="detail">
                                            <div class="category"><a href="category.html">Lifestyle</a>
                                            </div>
                                            <div class="time">December 22, 2016</div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="article-mini">
                                <div class="inner">
                                    <figure>
                                        <a href="single.html">
                                            <img src="css/user/images/news/img05.jpg"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <h1><a href="single.html">Duis aute irure dolor in reprehenderit
                                            in voluptate
                                            velit</a></h1>
                                        <div class="detail">
                                            <div class="category"><a href="category.html">Lifestyle</a>
                                            </div>
                                            <div class="time">December 22, 2016</div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="article-mini">
                                <div class="inner">
                                    <figure>
                                        <a href="single.html">
                                            <img src="css/user/images/news/img02.jpg"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <h1><a href="single.html">Fusce ullamcorper elit at felis cursus
                                            suscipit</a>
                                        </h1>
                                        <div class="detail">
                                            <div class="category"><a href="category.html">Travel</a>
                                            </div>
                                            <div class="time">December 21, 2016</div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="article-mini">
                                <div class="inner">
                                    <figure>
                                        <a href="single.html">
                                            <img src="css/user/images/news/img13.jpg"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <h1><a href="single.html">Duis aute irure dolor in reprehenderit
                                            in voluptate
                                            velit</a></h1>
                                        <div class="detail">
                                            <div class="category"><a
                                                    href="category.html">International</a></div>
                                            <div class="time">December 20, 2016</div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                            <article class="article-mini">
                                <div class="inner">
                                    <figure>
                                        <a href="single.html">
                                            <img src="css/user/images/news/img08.jpg"
                                                 alt="Sample Article">
                                        </a>
                                    </figure>
                                    <div class="padding">
                                        <h1><a href="single.html">Aliquam et metus convallis tincidunt
                                            velit ut rhoncus
                                            dolor</a></h1>
                                        <div class="detail">
                                            <div class="category"><a href="category.html">Computer</a>
                                            </div>
                                            <div class="time">December 19, 2016</div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-6 col-md-4 sidebar" id="sidebar">
                <div class="sidebar-title for-tablet">Sidebar</div>
                <aside>
                    <div class="aside-body">
                        <div class="featured-author">
                            <div class="featured-author-inner">
                                <c:if test="${sessionScope.member == null}">
                                    <div class="box box-border">
                                        <div class="box-body">
                                            <form action="signIn">
                                                <button type="submit"
                                                        class="btn btn-primary btn-block">Sign In
                                                </button>
                                            </form>
                                            <br>
                                            <form action="signUp">
                                                <button type="submit"
                                                        class="btn btn-primary btn-block">Register
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.member != null}">
                                    <div class="featured-author-cover"
                                         style="background-image: url('css/user/images/news/img15.jpg');">
                                        <c:if test="${memberInfo.memberGrade.equals('PREMIUM')}">
                                            <div class="badges">
                                                <div class="badge-item"><i class="ion-star"></i> PREMIUM
                                                </div>
                                            </div>
                                        </c:if>
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
                                            <div class="item">
                                                <a href="#">
                                                    <div class="name">Posts</div>
                                                    <c:if test="${memberInfo.postCount != null}">
                                                        <div class="value">${memberInfo.postCount}</div>
                                                    </c:if>
                                                    <c:if test="${memberInfo.postCount == null}">
                                                        <div class="value">0</div>
                                                    </c:if>
                                                </a>
                                            </div>
                                            <div class="item">
                                                <a href="../myPage">
                                                    <div class="name">Score</div>
                                                    <c:if test="${memberInfo.currentScore != null}">
                                                        <div class="value">${memberInfo.currentScore}
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${memberInfo.currentScore == null}">
                                                        <div class="value">0.0</div>
                                                    </c:if>
                                                </a>
                                            </div>
                                            <div class="item">
                                                <a href="../myPage">
                                                    <div class="icon">
                                                        <div>More</div>
                                                        <i class="ion-chevron-right"></i>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="featured-author-quote"
                                             style="font-weight: bold; font-family: 'Lato'; font-size:19px ;">
                                            LV . ${memberInfo.currentLevel}
                                        </div>
                                        <div style="display: flex; justify-content: center;">
                                            <input type="range" id="Exp" name="Exp" min="0"
                                                   max="${memberInfo.currentNextExp}"
                                                   value="${memberInfo.currentExp}" style="width: 250px;">
                                        </div>
                                        <div class="detail" style="text-align: center">

                                            <a
                                                    style="text-transform: uppercase; color: #F73F52; text-decoration-line: none;">
                                                    ${memberInfo.currentExp} / ${memberInfo.currentNextExp}
                                            </a>

                                        </div>
                                        <div class="featured-author-quote">
                                            <c:if test="${not empty memberInfo.memberIntroduction}">
                                                " ${memberInfo.memberIntroduction} "
                                            </c:if>
                                            <c:if test="${empty memberInfo.memberIntroduction}">
                                                " Write self-introduction to showcase yourself "
                                            </c:if>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </aside>
                <aside>
                    <div class="box box-border">
                        <div class="box-body">
                            <h1 class="aside-title">PREMIUM POST</h1>
                            <c:forEach items="${headHuntPostPremiumList}" var="headHuntPostPremiumList">
                                <div class="aside-body">
                                    <article class="article-mini">
                                        <div class="inner">
                                            <div class="detail">
                                                <div class="category"><a href="headHuntPostList">HEADHUNTPOST</a>
                                                </div>
                                                <div class="time">${headHuntPostPremiumList.memberNickname}</div>
                                            </div>
                                            <figure>
                                                <a href="headHuntPostSingle?headHuntPostId=${headHuntPostPremiumList.headHuntPostId}">
                                                    <img class="latestPostImg"
                                                         src="postImg/${headHuntPostPremiumList.postImgName}"
                                                         alt="Sample Article">
                                                </a>
                                            </figure>
                                            <div class="padding">
                                                <h1>
                                                    <a href="headHuntPostSingle?headHuntPostId=${headHuntPostPremiumList.headHuntPostId}">${headHuntPostPremiumList.headHuntPostTitle}</a>
                                                </h1>
                                            </div>
                                        </div>
                                    </article>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </aside>

                <aside id="sponsored">
                    <h1 class="aside-title">Sponsored</h1>
                    <div class="aside-body">
                        <ul class="sponsored">
                            <li>
                                <a href="#">
                                    <img src="css/user/images/sponsored.png" alt="Sponsored">
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <img src="css/user/images/sponsored.png" alt="Sponsored">
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <img src="css/user/images/sponsored.png" alt="Sponsored">
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <img src="css/user/images/sponsored.png" alt="Sponsored">
                                </a>
                            </li>
                        </ul>
                    </div>
                </aside>
            </div>
        </div>
    </div>
</section>

<section class="best-of-the-week">
    <div class="container">
        <h1>
            <div class="text">Best Of The Week</div>
            <div class="carousel-nav" id="best-of-the-week-nav">
                <div class="prev">
                    <i class="ion-ios-arrow-left"></i>
                </div>
                <div class="next">
                    <i class="ion-ios-arrow-right"></i>
                </div>
            </div>
        </h1>
        <div class="owl-carousel owl-theme carousel-1">
            <c:forEach items="${headHuntPostWeeklyBestList}" var="headHuntPostWeeklyBestList">
                <article class="article">
                    <div class="inner">
                        <figure>
                            <a href="headHuntPostSingle?headHuntPostId=${headHuntPostWeeklyBestList.headHuntPostId}">
                                <img class="latestPostImg"
                                     src="postImg/${headHuntPostWeeklyBestList.postImgName}"
                                     alt="Sample Article">
                            </a>
                        </figure>
                        <div class="padding">
                            <div class="detail">
                                <div class="category"><a
                                        href="headHuntPostSingle?headHuntPostId=${headHuntPostWeeklyBestList.headHuntPostId}">HEADHUNTPOST</a>
                                </div>
                                <div class="time">${headHuntPostWeeklyBestList.memberNickname}</div>
                            </div>
                            <h2>
                                <a href="headHuntPostSingle?headHuntPostId=${headHuntPostWeeklyBestList.headHuntPostId}">${headHuntPostWeeklyBestList.headHuntPostTitle}</a>
                            </h2>
                            <p class="latestPostContent">${headHuntPostWeeklyBestList.headHuntPostContent}</p>
                        </div>
                    </div>
                </article>
            </c:forEach>
        </div>
    </div>
</section>

<chalKagTags:webFooter/>

</body>

</html>