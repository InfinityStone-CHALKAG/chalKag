<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<!DOCTYPE html>
<html>
<head>
    <chalKagTags:webCss/>
</head>
	<body>
		<!-- Start header tag로 출력 -->
		<chalKagTags:webHeader />
		<!-- End header tag로 출력 -->

		<section class="single">
			<div class="container">
				<div class="row">

					<chalKagTags:webSider />

					<div class="details">
						<h3 class="name">
							<c:if test="${member == null || member != headHuntPostSingle.memberId}">
								<a href="/memberPage/memberId=${headHuntPostSingle.memberId}">${headHuntPostSingle.memberId}</a>
							</c:if>
							<c:if test="${member == headHuntPostSingle.memberId}">
								<a href="/myPage/memberId=${member}">${headHuntPostSingle.memberId}</a>
							</c:if>
					</h3>

					</div>

					<div class="col-md-8">
						<article class="article main-article">
							<header>
								<h1>${headHuntPostSingle.headHuntPostTitle}</h1>
								<ul class="details">
									<li>${headHuntPostSingle.headHuntPostDate}</li>
									<li><a>Film</a></li>
									<li>By 
										<c:if test="${member == null || member != headHuntPostSingle.memberId}">
											<a href="/memberPage/memberId=${headHuntPostSingle.memberId}">${headHuntPostSingle.memberId}</a>
										</c:if>
										<c:if test="${member == headHuntPostSingle.memberId}">
											<a href="/myPage/memberId=${member}">${headHuntPostSingle.memberId}</a>
										</c:if>
								</li>
								</ul>
							</header>
							<div class="main">
								<p>직업 : ${headHuntPostSingle.headHuntPostRole}</p>
								<p>지역 : ${headHuntPostSingle.headHuntPostRegion}</p>
								<p>작업 페이 : ${headHuntPostSingle.headHuntPostPay}</p>
								<p>작업 날짜 : ${headHuntPostSingle.headHuntPostWorkdate}</p>
								<p>쵤영 컨셉 : ${headHuntPostSingle.headHuntPostConcept}</p>
								<!-- 게시글 이미지 출력 추후 추가 -->
								<div class="featured">
									<figure>
										<img src="images/news/img01.jpg">
										<figcaption>Image by pexels.com</figcaption>
									</figure>
								</div>

								<!-- 게시글 내용 출력 -->
								<p>${headHuntPostSingle.headHuntPostContent}</p>
							</div>
							<footer>
								<div class="col">
									<a href="#" class="love"><i class="ion-android-favorite-outline"></i> <div>${headHuntPostList.recommendCnt}</div></a>
								</div>
							</footer>
						</article>
						<div class="sharing">
						<div class="line">
							<div>Author</div>
						</div>
						<div class="author">
							<figure>
								<img src="images/img01.jpg">
							</figure>
						</div>
						
						<div class="line thin"></div>
						<!-- 댓글 출력 추후 추가 -->
						<div class="comments">
							<h2 class="title">3 Responses <a href="#">Write a Response</a></h2>
							<div class="comment-list">
								<div class="item">
									<div class="user">                                
										<figure>
											<img src="images/img01.jpg">
										</figure>
										<div class="details">
											<h5 class="name">Mark Otto</h5>
											<div class="time">24 Hours</div>
											<div class="description">
												Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
												tempor incididunt ut labore et dolore <a href="#">magna</a> aliqua. Ut enim ad minim veniam,
												quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo.
											</div>
											<footer>
												<a href="#">Reply</a>
											</footer>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="user">                                
										<figure>
											<img src="images/img01.jpg">
										</figure>
										<div class="details">
											<h5 class="name">Mark Otto</h5>
											<div class="time">24 Hours</div>
											<div class="description">
												Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
												tempor incididunt ut labore et dolore <a href="#">magna</a> aliqua. Ut enim ad minim veniam,
												quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo.
											</div>
											<footer>
												<a href="#">Reply</a>
											</footer>
										</div>
									</div>
									<div class="reply-list">
										<div class="item">
											<div class="user">                                
												<figure>
													<img src="images/img01.jpg">
												</figure>
												<div class="details">
													<h5 class="name">Mark Otto</h5>
													<div class="time">24 Hours</div>
													<div class="description">
														Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
														consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
														cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
														proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
													</div>
													<footer>
														<a href="#">Reply</a>
													</footer>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="user">                                
										<figure>
											<img src="images/img01.jpg">
										</figure>
										<div class="details">
											<h5 class="name">Mark Otto</h5>
											<div class="time">24 Hours</div>
											<div class="description">
												Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
												tempor incididunt ut labore et dolore <a href="#">magna</a> aliqua. Ut enim ad minim veniam,
												quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo.
											</div>
											<footer>
												<a href="#">Reply</a>
											</footer>
										</div>
									</div>
								</div>
							</div>
							<form class="row">
								<div class="col-md-12">
									<h3 class="title">Leave Your Response</h3>
								</div>
								<div class="form-group col-md-4">
									<label for="name">Name <span class="required"></span></label>
									<input type="text" id="name" name="" class="form-control">
								</div>
								<div class="form-group col-md-4">
									<label for="email">Email <span class="required"></span></label>
									<input type="email" id="email" name="" class="form-control">
								</div>
								<div class="form-group col-md-4">
									<label for="website">Website</label>
									<input type="url" id="website" name="" class="form-control">
								</div>
								<div class="form-group col-md-12">
									<label for="message">Response <span class="required"></span></label>
									<textarea class="form-control" name="message" placeholder="Write your response ..."></textarea>
								</div>
								<div class="form-group col-md-12">
									<button class="btn btn-primary">Send Response</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- Start footer tag로 출력 -->
		<chalKagTags:webFooter/>
		<!-- End Footer -->
		<!-- JS -->
		<!-- 페이지 이동 및 필터검색 Js 파일 링크 달기 & Jsp로 작성할때 링크 프로젝트내 링크와 맞추기 -->
	<script src="css/user/js/jquery.js"></script>
	<script src="css/user/js/jquery.migrate.js"></script>
	<script src="css/user/scripts/bootstrap/bootstrap.min.js"></script>
	<script>
		var $target_end = $(".best-of-the-week");
	</script>
	<script src="css/user/scripts/jquery-number/jquery.number.min.js"></script>
	<script src="css/user/scripts/owlcarousel/dist/owl.carousel.min.js"></script>
	<script src="css/user/scripts/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
	<script src="css/user/scripts/easescroll/jquery.easeScroll.js"></script>
	<script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>
	<script src="css/user/scripts/toast/jquery.toast.min.js"></script>
	<script src="css/user/js/demo.js"></script>
		<script>$("input").iCheck({
      checkboxClass: 'icheckbox_square-red',
      radioClass: 'iradio_square-red',
      cursor: true
		});</script>
	<script src="css/user/js/e-magz.js"></script>
	</body>
</html>