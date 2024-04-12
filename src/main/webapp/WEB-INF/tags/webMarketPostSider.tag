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
                         <a href="headHuntPostSingle?headHuntPostId=${latestMarketPost.headHuntPostId}">
                             <img class="latestPostImg" src="postImg/${latestMarketPost.postImgName}">
                         </a>
                    </figure>
					<div class="details">
						<h1>
							 <a href="headHuntPostSingle?headHuntPostId=${latestMarketPost.headHuntPostId}">${latestMarketPost.headHuntPostTitle}</a>
						</h1>
						 <p class="latestPostContent">${latestMarketPost.headHuntPostContent}</p>
						<div class="detail">
							 <div class="time">${latestMarketPost.memberNickname}</div>
							 <div class="category"><a href="headHuntPostList">HEADHUNTPOST</a></div>
						</div>
					</div>
				</div>
			</article>
			<div class="line"></div>
			<!-- 구인글 -->
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a
							href="headHuntPostSingle?headHuntPostId=${latestheadHuntPost.headHuntPostId}">
							<img class="latestPostImg"
							src="postImg/${latestHeadHuntPost.postImgName}">
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a
								href="headHuntPostSingle?headHuntPostId=${latestHeadHuntPost.headHuntPostId}">${latestHeadHuntPost.headHuntPostTitle}</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="headHuntPostList">HEADHUNTPOST</a>
							</div>
							<div class="time">${latestHeadHuntPost.memberNickname}</div>
						</div>
					</div>
				</div>
			</article>
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
			<!-- 자유글 -->
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a href="freePostSingle?freePostId=${latestFreePost.freePostId}">
							<img class="latestPostImg"
							src="${latestFreePost.postImgName ? 'postImg/' + latestFreePost.postImgName : 'postImg/postDefault.jpg'}"
							alt="Sample Article">
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