<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<header class="primary">
			<div class="firstbar">
				<div class="container">
					<div class="row">
						<div class="col-md-3 col-sm-12">
							<div class="brand">
								<a href="main">
									<img src="css/user/images/chalKagLogo.png" alt="Magz Logo">
								</a>
							</div>						
						</div>
						<div class="col-md-6 col-sm-12">
							<form class="search" autocomplete="off">
								<div class="form-group">
									<div class="input-group">
										<input type="text" name="q" class="form-control" placeholder="Type something here">									
										<div class="input-group-btn">
											<button class="btn btn-primary"><i class="ion-search"></i></button>
										</div>
									</div>
								</div>
								<div class="help-block">
									<div>Popular:</div>
									<ul>
										<li><a href="#">HTML5</a></li>
										<li><a href="#">CSS3</a></li>
										<li><a href="#">Bootstrap 3</a></li>
										<li><a href="#">jQuery</a></li>
										<li><a href="#">AnguarJS</a></li>
									</ul>
								</div>
							</form>								
						</div>
						<div class="col-md-3 col-sm-12 text-right">
							<ul class="nav-icons">
								<c:if test="${sessionScope.member == null}">
								<li><a href="signUp"><i class="ion-person-add"></i><div>Register</div></a></li>
								<li><a href="signIn"><i class="ion-person"></i><div>Sign In</div></a></li>
								</c:if>
								<c:if test="${sessionScope.member != null}">
								<li><a href="signOut"><i class="icon ion-log-out"></i><div>Sign Out</div></a></li>
								</c:if>	

							</ul>
						</div>
					</div>
				</div>
			</div>

			<!-- Start nav -->
			<nav class="menu">
				<div class="container">
					<div id="menu-list">
						<ul class="nav-list">
							<li class="for-tablet nav-title"><a>Menu</a></li>
						
							<li class="dropdown magz-dropdown"><a href="category.html">Market Post<i class="ion-ios-arrow-right"></i></a>
								<ul class="dropdown-menu">
									<li><a href="marketPostList?marketPostStatus=Sell">Sell</a></li>
									<li><a href="marketPostList?marketPostStatus=Buy">Buy</a></li>
									<li><a href="marketPostList?marketPostStatus=Freecycle">Freecycle</a></li>
								</ul>
							</li>
							<li><a href="headHuntPostList">Headhunt Post<i class="ion-ios-arrow-right"></i></a></li>
							<li><a href="jobHuntPostList">Jobhunt Post<i class="ion-ios-arrow-right"></i></a></li>
							<li><a href="freePostList">Free Post<i class="ion-ios-arrow-right"></i></a></li>
					
							<li class="dropdown magz-dropdown"><a href="myPage">My Page<i class="ion-ios-arrow-right"></i></a>
								<ul class="dropdown-menu">
									<c:if test="${sessionScope.member != null}">
										<li><a href="myPage"><i class="icon ion-person"></i> My Account</a></li>
										<li><a href="changeInformation"><i class="icon ion-heart"></i>Change Information</a></li>
										<li><a href="changeNickname"><i class="icon ion-chatbox"></i>Change Nickname</a></li>
										<li><a href="changePw"><i class="icon ion-key"></i>Change Password</a></li>
										<li><a href="changePh"><i class="icon ion-settings"></i>Change PhoneNumber</a></li>
									</c:if>
									<c:if test="${sessionScope.member == null}">
										<li><a href="signIn"><i class="icon ion-person"></i> My Account</a></li>
										<li><a href="signIn"><i class="icon ion-heart"></i>Change Information</a></li>
										<li><a href="signIn"><i class="icon ion-chatbox"></i>Change Nickname</a></li>
										<li><a href="signIn"><i class="icon ion-key"></i>Change Password</a></li>
										<li><a href="signIn"><i class="icon ion-settings"></i>Change PhoneNumber</a></li>
									</c:if>
									<li class="divider"></li>
									<c:if test="${sessionScope.member != null}">
										<li><a href="signOut"><i class="icon ion-log-out"></i>Log Out</a></li>
									</c:if>
									<c:if test="${sessionScope.member == null}">
										<li><a href="signIn"><i class="icon ion-person"></i>Sign In</a></li>
									</c:if>
								</ul>
							</li>
							<li><a href="payment">Chat<i class="ion-ios-arrow-right"></i></a></li>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- End nav -->
		</header>