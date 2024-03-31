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
					<!-- 이미지 추후 여러장 출력하는 기능 추가 -->
					<figure>
						<a href="single.html"> <img src="css/user/images/news/img16.jpg">
						</a>
					</figure>
					<div class="details">
						<h1>
							<a href="single.html">Lorem Ipsum Dolor Sit Amet Consectetur
								Adipisicing Elit</a>
						</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua.</p>
						<div class="detail">
							<div class="time">December 26, 2016</div>
							<div class="category">
								<a href="category.html">Lifestyle</a>
							</div>
						</div>
					</div>
				</div>
			</article>
			<div class="line"></div>
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a href="single.html"> <img src="css/user/images/news/img05.jpg">
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a href="single.html">Duis aute irure dolor in reprehenderit
								in voluptate velit</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="category.html">Lifestyle</a>
							</div>
							<div class="time">December 22, 2016</div>
						</div>
					</div>
				</div>
			</article>
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a href="single.html"> <img src="css/user/images/news/img02.jpg">
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a href="single.html">Fusce ullamcorper elit at felis cursus
								suscipit</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="category.html">Travel</a>
							</div>
							<div class="time">December 21, 2016</div>
						</div>
					</div>
				</div>
			</article>
			<article class="article-mini">
				<div class="inner">
					<figure>
						<a href="single.html"> <img src="css/user/images/news/img13.jpg">
						</a>
					</figure>
					<div class="padding">
						<h1>
							<a href="single.html">Duis aute irure dolor in reprehenderit
								in voluptate velit</a>
						</h1>
						<div class="detail">
							<div class="category">
								<a href="category.html">International</a>
							</div>
							<div class="time">December 20, 2016</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</aside>
	<aside>
		<div class="aside-body">
			<form class="newsletter">
				<div class="icon">
					<i class="ion-ios-email-outline"></i>
					<h1>Newsletter</h1>
				</div>
				<div class="input-group">
					<input type="email" class="form-control email"
						placeholder="Your mail">
					<div class="input-group-btn">
						<button class="btn btn-primary">
							<i class="ion-paper-airplane"></i>
						</button>
					</div>
				</div>
				<p>By subscribing you will receive new articles in your email.</p>
			</form>
		</div>
	</aside>
</div>