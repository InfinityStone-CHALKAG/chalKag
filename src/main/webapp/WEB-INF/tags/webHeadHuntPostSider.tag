<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-4 sidebar" id="sidebar">
	<aside>
		<div class="aside-body">
			<figure class="ads">
				<img src="css/user/images/ad.png">
				<figcaption>Advertisement</figcaption>
			</figure>
		</div>
	</aside>
	<aside>
		<h1 class="aside-title">Recent Post</h1>
		<div class="aside-body">
			<article class="article-fw">
				<div class="inner">
					<figure>
						<a
							href="headHuntPostSingle?headHuntPostId=${latestHeadHuntPost.headHuntPostId}">
							<img class="latestPostImg"
							src="postImg/${latestHeadHuntPost.postImgName}">
						</a>
					</figure>
					<div class="details">
						<h1 style="width: 255px; max-height: 44px; overflow: hidden; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 1;">
							<a
								href="headHuntPostSingle?headHuntPostId=${latestHeadHuntPost.headHuntPostId}">${latestHeadHuntPost.headHuntPostTitle}</a>
						</h1>
						<p class="latestPostContent">${latestHeadHuntPost.headHuntPostContent}</p>
						<div class="detail">
							<div class="time">${latestHeadHuntPost.memberNickname}</div>
							<div class="category">
								<a href="headHuntPostList">HEADHUNTPOST</a>
							</div>
						</div>
					</div>
				</div>
			</article>
			<div class="line"></div>
			<!-- 구직글 -->
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a
							href="jobHuntPostSingle?jobHuntPostId=${latestJobHuntPost.jobHuntPostId}">
							<img class="latestPostImg"
							src="postImg/${latestJobHuntPost.postImgName}">
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a
								href="jobHuntPostSingle?jobHuntPostId=${latestJobHuntPost.jobHuntPostId}">${latestJobHuntPost.jobHuntPostTitle}</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="jobHuntPostList">JOBHUNTPOST</a>
							</div>
							<div class="time">${latestJobHuntPost.memberNickname}</div>
						</div>
					</div>
				</div>
			</article>
			<!-- 장터글 -->
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a
							href="marketPostSingle?marketPostId=${latestMarketPost.marketPostId}">
							<img class="latestPostImg"
							src="postImg/${latestMarketPost.postImgName}"
							alt="Sample Article">
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a
								href="marketPostSingle?marketPostId=${latestMarketPost.marketPostId}">${latestMarketPost.marketPostTitle}</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="marketPostList">MARKETPOST</a>
							</div>
							<div class="time">${latestMarketPost.memberNickname}</div>
						</div>
					</div>
				</div>
			</article>
			<!-- 자유글 -->
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a href="freePostSingle?freePostId=${latestFreePost.freePostId}">
							<c:if test="${latestFreePost.postImgName != null }">
                                               <img src="postImg/${latestFreePost.postImgName}" alt="Sample Article">
                                            </c:if>
                                            <c:if test="${latestFreePost.postImgName == null}">
                                               <img src="postImg/postDefault.jpg" alt="Sample Article">
                                            </c:if>
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a href="freePostSingle?freePostId=${latestFreePost.freePostId}">${latestFreePost.freePostTitle}</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="freePostList">FREEPOST</a>
							</div>
							<div class="time">${latestFreePost.memberNickname}</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</aside>

</div>