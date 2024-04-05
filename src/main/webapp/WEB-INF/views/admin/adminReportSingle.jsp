<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!doctype html>

            <html class="no-js" lang=""> <!--<![endif]-->

            <head>
                <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <title>Ela Admin - HTML5 Admin Template</title>
                <meta name="description" content="Ela Admin - HTML5 Admin Template">
                <meta name="viewport" content="width=device-width, initial-scale=1">

                <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
                <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
                <link rel="stylesheet" href="css/admin/assets/css/cs-skin-elastic.css">
                <link rel="stylesheet" href="css/admin/assets/css/style.css">
                <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet'
                    type='text/css'>
                <link rel="stylesheet" href="css/user/scripts/sweetalert/dist/sweetalert.css">






                <style>
                    #weatherWidget .currentDesc {
                        color: #ffffff !important;
                    }

                    .traffic-chart {
                        min-height: 335px;
                    }

                    #flotPie1 {
                        height: 150px;
                    }

                    #flotPie1 td {
                        padding: 3px;
                    }

                    #flotPie1 table {
                        top: 20px !important;
                        right: -10px !important;
                    }

                    .chart-container {
                        display: table;
                        min-width: 270px;
                        text-align: left;
                        padding-top: 10px;
                        padding-bottom: 10px;
                    }

                    #flotLine5 {
                        height: 105px;
                    }

                    #flotBarChart {
                        height: 150px;
                    }

                    #cellPaiChart {
                        height: 160px;
                    }

                    /* tab css */
                    a {
                        text-decoration: none;
                        color: inherit;
                    }

                    a:focus {
                        color: #FFF;
                    }

                    .tab {
                        display: flex;
                        align-items: center;
                        padding: 1rem;
                    }

                    .tab__item {
                        padding: 0.6rem 1.3rem;
                        margin-right: 1rem;
                        border: 1px solid #ddd;
                        border-radius: 0.25rem;
                        cursor: pointer;
                    }

                    .tab__item.active {
                        display: inline-block;
                        border: 1px solid #F73F52;
                        background-color: #F73F52;
                        color: #fff;
                    }

                    .tab__item:hover {
                        border: 1px solid #F73F52;
                    }

                    .tab__item.active a:hover {
                        color: inherit;
                        /* 현재 색상 유지 */
                    }

                    .tab__content-wrapper {
                        padding: 1rem
                    }

                    .tab__content {
                        display: none;
                    }

                    .tab__content.active {
                        display: block;
                    }

                    ul {
                        list-style: none;
                    }
                </style>
            </head>

            <body>
                <!-- Left Panel -->
                <aside id="left-panel" class="left-panel">
                    <nav class="navbar navbar-expand-sm navbar-default">
                        <div id="main-menu" class="main-menu collapse navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li class="menu-title">MAIN</li>
                                <li>
                                    <a href="adminMain"><i class="menu-icon fa fa-laptop"></i>Main</a>
                                </li>
                                <li class="menu-title">MANAGEMENT</li><!-- /.menu-title -->
                                <li> <a href="adminProfit"><i class="menu-icon fa fa-signal"></i>Profit</a> </li>
                                <li> <a href="adminReportList"><i class="menu-icon fa fa-warning (alias)"></i>Report</a>
                                </li>
                                <li> <a href="adminContent"><i class="menu-icon fa fa-picture-o"></i>Web Content</a>
                                </li>
                                <li class="menu-title">MOVE TO</li>
                                <li>
                                    <a href="main"><i class="menu-icon fa fa-camera-retro"></i>CHALKAG</a>
                                </li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </nav>
                </aside>
                <!-- /#left-panel -->
                <!-- Right Panel -->
                <div id="right-panel" class="right-panel">
                    <!-- Header-->
                    <header id="header" class="header">
                        <div class="top-left">
                            <div class="navbar-header">
                                <a class="navbar-brand" href="/adminMain"><img src="css/admin/images/logo.png"
                                        alt="Logo"></a>
                                <a class="navbar-brand hidden" href="/adminMain"><img src="css/admin/images/logo2.png"
                                        alt="Logo"></a>
                                <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                            </div>
                        </div>
                        <div class="top-right">
                            <div class="header-menu">
                                <div class="header-left">
                                    <button class="search-trigger"><i class="fa fa-search"></i></button>
                                    <div class="form-inline">
                                        <form class="search-form">
                                            <input class="form-control mr-sm-2" type="text" placeholder="Search ..."
                                                aria-label="Search">
                                            <button class="search-close" type="submit"><i
                                                    class="fa fa-close"></i></button>
                                        </form>
                                    </div>

                                    <div class="dropdown for-notification">
                                        <button class="btn btn-secondary dropdown-toggle" type="button"
                                            id="notification" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                            <i class="fa fa-bell"></i>
                                            <span class="count bg-danger">3</span>
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="notification">
                                            <p class="red">You have 3 Notification</p>
                                            <a class="dropdown-item media" href="#">
                                                <i class="fa fa-check"></i>
                                                <p>Server #1 overloaded.</p>
                                            </a>
                                            <a class="dropdown-item media" href="#">
                                                <i class="fa fa-info"></i>
                                                <p>Server #2 overloaded.</p>
                                            </a>
                                            <a class="dropdown-item media" href="#">
                                                <i class="fa fa-warning"></i>
                                                <p>Server #3 overloaded.</p>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="dropdown for-message">
                                        <button class="btn btn-secondary dropdown-toggle" type="button" id="message"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fa fa-envelope"></i>
                                            <span class="count bg-primary">4</span>
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="message">
                                            <p class="red">You have 4 Mails</p>
                                            <a class="dropdown-item media" href="#">
                                                <span class="photo media-left"><img alt="avatar"
                                                        src="css/admin/images/avatar/1.jpg"></span>
                                                <div class="message media-body">
                                                    <span class="name float-left">Jonathan Smith</span>
                                                    <span class="time float-right">Just now</span>
                                                    <p>Hello, this is an example msg</p>
                                                </div>
                                            </a>
                                            <a class="dropdown-item media" href="#">
                                                <span class="photo media-left"><img alt="avatar"
                                                        src="css/admin/images/avatar/2.jpg"></span>
                                                <div class="message media-body">
                                                    <span class="name float-left">Jack Sanders</span>
                                                    <span class="time float-right">5 minutes ago</span>
                                                    <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                </div>
                                            </a>
                                            <a class="dropdown-item media" href="#">
                                                <span class="photo media-left"><img alt="avatar"
                                                        src="css/admin/images/avatar/3.jpg"></span>
                                                <div class="message media-body">
                                                    <span class="name float-left">Cheryl Wheeler</span>
                                                    <span class="time float-right">10 minutes ago</span>
                                                    <p>Hello, this is an example msg</p>
                                                </div>
                                            </a>
                                            <a class="dropdown-item media" href="#">
                                                <span class="photo media-left"><img alt="avatar"
                                                        src="css/admin/images/avatar/4.jpg"></span>
                                                <div class="message media-body">
                                                    <span class="name float-left">Rachel Santos</span>
                                                    <span class="time float-right">15 minutes ago</span>
                                                    <p>Lorem ipsum dolor sit amet, consectetur</p>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <div class="user-area dropdown float-right">
                                    <a href="#" class="dropdown-toggle active" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                        <img class="user-avatar rounded-circle" src="css/admin/images/admin.jpg"
                                            alt="User Avatar">
                                    </a>

                                    <div class="user-menu dropdown-menu">
                                        <a class="nav-link" href="#"><i class="fa fa- user"></i>My Profile</a>

                                        <a class="nav-link" href="#"><i class="fa fa- user"></i>Notifications <span
                                                class="count">13</span></a>

                                        <a class="nav-link" href="#"><i class="fa fa -cog"></i>Settings</a>

                                        <a class="nav-link" href="#"><i class="fa fa-power -off"></i>Logout</a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </header>
                    <!-- /#header -->
                    <!-- Content -->
                    <div class="content">
                        <!-- Animated -->
                        <div class="animated fadeIn">
                            <!-- Widgets  -->
                            <div class="row">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="stat-widget-five">
                                                <div class="stat-icon dib flat-color-1">
                                                    <i class="pe-7s-cash"></i>
                                                </div>
                                                <div class="stat-content">
                                                    <div class="text-left dib">
                                                        <div class="stat-text"><span class="count">1450000</span></div>
                                                        <div class="stat-heading">Revenue</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="stat-widget-five">
                                                <div class="stat-icon dib flat-color-2">
                                                    <i class="pe-7s-note2"></i>
                                                </div>
                                                <div class="stat-content">
                                                    <div class="text-left dib">
                                                        <div class="stat-text"><span class="count">1980</span></div>
                                                        <div class="stat-heading">PostDatas</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>



                                <div class="col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="stat-widget-five">
                                                <div class="stat-icon dib flat-color-3">
                                                    <i class="pe-7s-diamond"></i>
                                                </div>
                                                <div class="stat-content">
                                                    <div class="text-left dib">
                                                        <div class="stat-text"><span class="count">20</span></div>
                                                        <div class="stat-heading">Premium Users</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="stat-widget-five">
                                                <div class="stat-icon dib flat-color-4">
                                                    <i class="pe-7s-users"></i>
                                                </div>
                                                <div class="stat-content">
                                                    <div class="text-left dib">
                                                        <div class="stat-text"><span class="count">20</span></div>
                                                        <div class="stat-heading">Users</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 offset-md-3">
                                    <div class="card">
                                        <div class="card-header">
                                            <strong>Report Details</strong>
                                        </div>
                                        <div class="card-body card-block">
                                            <form id="reportStateHoldForm" action="reportStateHold" method="post"
                                                class="form-horizontal">
                                                <!-- reportState를 HOLD로 바꾸기 위한 hidden value 제출-->
                                                <input type="hidden" id="reportId" name="reportId"
                                                    value="${reportSingle.reportId}">

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label
                                                            class=" form-control-label">Date</label></div>
                                                    <div class="col-12 col-md-9"><input type="text" id="text-input"
                                                            name="text-input" class="form-control"
                                                            value="${reportSingle.reportDate}" readonly>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label
                                                            class=" form-control-label">Reporter</label></div>
                                                    <div class="col-12 col-md-9"><input type="email" id="reporter"
                                                            name="reporter" class="form-control"
                                                            value="${reportSingle.memberId}" readonly>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><strong
                                                            class=" form-control-label">Suspector Email</Strong></div>
                                                    <div class="col-12 col-md-9"><input type="email" id="memberId"
                                                            name="memberId" class="form-control"
                                                            value="${reportSingle.reportSuspector}"
                                                            style="font-weight: bold;" readonly></div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-3"><strong class=" form-control-label"
                                                            style="font-size:15px;">Suspector Nickname</Strong></div>
                                                    <div class="col-12 col-md-9"><input type="email" id="memberNickname"
                                                            name="memberNickname" class="form-control"
                                                            value="${reportSingle.suspectorNickname}"
                                                            style="font-weight: bold;" readonly></div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label
                                                            class=" form-control-label">Textarea</label></div>
                                                    <div class="col-12 col-md-9"><textarea name="reportContent"
                                                            id="reportContent" rows="9" style="resize: none;"
                                                            class="form-control"
                                                            readonly>${reportSingle.reportContent}</textarea></div>
                                                </div>

                                                <c:if test="${reportSingle.reportState eq 'HOLD'}">
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"></div>
                                                        <div class="col col-md-9">
                                                            <button id="reportStateUnHold"
                                                                class="btn btn-outline-success" style="width: 120px; 
                                                            height: 40px; 
                                                            font-size: 16px; 
                                                            padding: 5px 10px;">User UnHold</button>
                                                        </div>
                                                    </div>
                                                </c:if>

                                                <c:if
                                                    test="${reportSingle.reportState eq 'READ' || reportSingle.reportState eq 'UNREAD'}">
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label
                                                                class=" form-control-label">Hold
                                                                Period</label></div>
                                                        <div class="col col-md-9">
                                                            <div class="form-check-inline form-check">
                                                                <label for="inline-radio1" class="form-check-label ">
                                                                    <input type="radio" id="timeOutDuration1"
                                                                        name="timeOutDuration" value="1"
                                                                        class="form-check-input">1 day &nbsp;&nbsp;
                                                                </label>
                                                                <label for="inline-radio2" class="form-check-label ">
                                                                    <input type="radio" id="timeOutDuration2"
                                                                        name="timeOutDuration" value="7"
                                                                        class="form-check-input"> 7 days &nbsp;&nbsp;
                                                                </label>
                                                                <label for="inline-radio3" class="form-check-label ">
                                                                    <input type="radio" id="timeOutDuration3"
                                                                        name="timeOutDuration" value="30"
                                                                        class="form-check-input"> 30 days &nbsp;&nbsp;
                                                                </label>
                                                                <label for="inline-radio4" class="form-check-label ">
                                                                    <input type="radio" id="timeOutDuration4"
                                                                        name="timeOutDuration" value="90"
                                                                        class="form-check-input"> 90 days
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row form-group">
                                                        <div class="col col-md-3"></div>
                                                        <div class="col col-md-9">
                                                            <button id="reportStateHold" class="btn btn-outline-danger"
                                                                style="width: 100px; 
                                                            height: 40px; 
                                                            font-size: 16px; 
                                                            padding: 5px 10px;">User Hold</button>&nbsp;
                                                            <button id="reportStateReject"
                                                                class="btn btn-outline-secondary"
                                                                style="width: 100px; height: 40px; font-size: 16px; padding: 5px 10px;">Reject</button>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- .animated -->
                    </div><!-- .content -->

                    <div class="clearfix"></div>
                    <!-- Footer -->
                    <footer class="site-footer">
                        <div class="footer-inner bg-white">
                            <div class="row">
                                <div class="col-sm-6">
                                    Copyright &copy; 2018 Ela Admin
                                </div>
                                <div class="col-sm-6 text-right">
                                    Designed by <a href="https://colorlib.com">Colorlib</a>
                                </div>
                            </div>
                        </div>
                    </footer>
                </div>

                <!-- Scripts -->
                <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
                <script
                    src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
                <script src="css/admin/assets/js/main.js"></script>
                <script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>



                <script src="js/admin/leftPanalActive.js"></script>

                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        if (document.getElementById('reportStateReject')) {
                            document.getElementById('reportStateReject').addEventListener('click', function (event) {
                                event.preventDefault();
                                swal({
                                    title: 'WARNING',
                                    text: '정말 Reject 하시겠습니까?',
                                    type: 'warning',
                                    showCancelButton: true, // 확인, 취소 버튼 표시
                                    confirmButtonText: 'OK',
                                    cancelButtonText: 'Cancel'
                                }, function (isConfirmed) {
                                    // 확인 버튼을 클릭했을 때의 동작
                                    if (isConfirmed) {
                                        // location.href를 사용하여 신고 전체 출력 페이지로 이동
                                        window.location.href = "reportStateReject?reportId=${reportSingle.reportId}";
                                    }
                                });
                            });
                        }
                    });
                    if (document.getElementById("reportStateHold")) {
                        // User Hold 버튼을 클릭했을 때의 이벤트 처리
                        document.getElementById("reportStateHold").addEventListener("click", function (event) {
                            console.log("HOLD");
                            event.preventDefault(); // 이벤트 전파 중지
                            reportStateCheck(); // reportStateCheck() 함수 호출
                        });

                    }
                    if (document.getElementById("reportStateUnHold")) {
                        // User UnHold 버튼을 클릭했을 때의 이벤트 처리
                        document.getElementById("reportStateUnHold").addEventListener("click", function (event) {
                            event.preventDefault(); // 이벤트 전파 중지
                            window.location.href = "reportStateUnHold?reportId=${reportSingle.reportId}&memberId=${reportSingle.reportSuspector}";
                        });
                    }


                    function reportStateCheck() {
                        console.log("진입");
                        console.log($('#reportStateHoldForm').serialize());

                        var radioButtons = document.querySelectorAll('input[type="radio"][name="timeOutDuration"]');
                        var checked = false;

                        radioButtons.forEach(function (radio) {
                            if (radio.checked) {
                                checked = true;
                                console.log(checked);
                            }
                        });

                        if (!checked) {
                            swal("WARNING", "정지 기간을 선택해 주세요.", "error", {
                                button: "OK",
                            });
                            return false;
                        }

                        // 1. 신고를 당해 정지된 사용자가 있는지 확인 
                        $.ajax({
                            type: 'POST',
                            url: '/reportStateCheck',
                            data: $('#reportStateHoldForm').serialize(), // 폼 데이터 전송
                            success: function (result) {
                                if (result === 1) {
                                    // 정지된 사용자가 없으면 정지상태로 변경
                                    reportStateHold();
                                } else {
                                    swal("WARNING", "이미 정지된 사용자 입니다.", "error", {
                                        button: "OK",
                                    });
                                }
                            },
                            error: function () {
                                alert('서버 오류가 발생했습니다.');
                            }
                        });
                    }

                    function reportStateHold() {
                        // 폼 가져오기
                        var form = document.getElementById('reportStateHoldForm');

                        // 폼 제출 
                        form.submit();
                    }




                </script>


            </body>

            </html>