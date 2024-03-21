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

                <!-- Owl Carousel을 위한 CSS CDN -->
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">

                <!-- Owl Carousel의 javascript CDN -->
                <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>





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
                        padding: 0rem;
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

                    /* ===================== 첫번째 텝 =====================*/
                    /* 메인 이미지 글 css */
                    .card-body .item h2 {
                        position: absolute;
                        bottom: 10px;
                        /* 하단 가장자리에서 시작 */
                        left: 10px;
                        /* 왼쪽 가장자리에서 시작 */
                        margin: 10px;
                        /* 이미지 가장자리로부터 약간 떨어져 있도록 여백 설정 */
                        color: white;
                        /* 텍스트 색상 */
                        padding: 5px;
                        /* 텍스트 주변에 여백 추가 */
                        text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
                        /* 검은색 텍스트 테두리 */
                    }


                    /* 슬라이드 관련 */
                    /* 캐러셀 공지 */
                    #notice.owl-carousel {
                        width: 500px;
                        height: 50px;
                        margin: 0 auto;
                    }

                    /* 캐러셀 이미지 */
                    #mainImg.owl-carousel {
                        width: 750px;
                        height: 550px;
                        margin: 0 auto;
                    }

                    /* 이미지가 캐러셀 크기에 맞춰 조정되도록 설정 */
                    .owl-carousel img {
                        width: 100%;
                        /* 너비를 캐러셀의 100%로 설정 */
                        height: 550px;
                        /* 높이를 고정 값으로 설정, 캐러셀 높이와 동일하게 */
                        object-fit: cover;
                        /* 이미지가 비율을 유지하면서 캐러셀의 지정된 크기를 채우도록 설정 */
                    }

                    .owl-dots {
                        display: none !important;
                        /* 캐러셀 아래의 점을 숨깁니다. */
                    }

                    /* ===================== 두번째 텝 =====================*/
                    .sponsored img {
                        width: 175px;
                        height: 175px;
                        margin: 10px;
                        cursor: pointer;
                    }

                    /* 숨겨진 파일 입력 필드 스타일 */
                    .fileInput {
                        display: none;
                    }

                    /* ===================== 세번째 텝 =====================*/

                </style>
            </head>

            <body>
                <!-- Left Panel -->
                <aside id="left-panel" class="left-panel">
                    <nav class="navbar navbar-expand-sm navbar-default">
                        <div id="main-menu" class="main-menu collapse navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li class="menu-title">MAIN</li>
                                <li class="active">
                                    <a href="adminMain"><i class="menu-icon fa fa-laptop"></i>Main</a>
                                </li>
                                <li class="menu-title">MANAGEMENT</li><!-- /.menu-title -->
                                <li> <a href="adminProfit"><i class="menu-icon fa fa-signal"></i>Profit</a> </li>
                                <li> <a href="adminReportList"><i class="menu-icon fa fa-warning (alias)"></i>Report</a>
                                </li>
                                <li> <a href="adminWebContent"><i class="menu-icon fa fa-picture-o"></i>Web Content</a>
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
                                <a class="navbar-brand" href="adminMain"><img src="css/admin/images/logo.png"
                                        alt="Logo"></a>
                                <a class="navbar-brand hidden" href="adminMain"><img src="css/admin/images/logo2.png"
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
                            <!-- /Widgets -->

                            <!--  Traffic  -->
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="box-title"> Manage Contents </h4>
                                        </div>
                                        <!-- 탭 버튼 영역 -->
                                        <ul class="tab">
                                            <li class="tab__item active">
                                                <a href="#tab1">Main Image</a>
                                            </li>
                                            <li class="tab__item">
                                                <a href="#tab2">Advertisement</a>
                                            </li>
                                            <li class="tab__item">
                                                <a href="#tab3">Premium Price</a>
                                            </li>

                                        </ul>
                                        <!-- 탭 내용 영역 -->
                                        <div class="tab__content-wrapper">
                                            <div id="tab1" class="tab__content active">
                                                <div class="row">
                                                    <div class="col-lg-8">

                                                        <div class="card-body" style="width: 0%">

                                                            <div class="owl-carousel owl-theme" id="notice">
                                                                <div class="item">
                                                                    <a href="#">
                                                                        <div class="badge">Notice!</div> Vestibulum ante
                                                                        ipsum primis in faucibus orci
                                                                    </a>
                                                                </div>
                                                                <div class="item">
                                                                    <a href="#">
                                                                        <div class="badge">Notice!</div> Ut rutrum
                                                                        sodales mauris ut suscipit
                                                                    </a>
                                                                </div>
                                                            </div>

                                                            <div class="owl-carousel owl-theme" id="mainImg">
                                                                <div class="item">
                                                                    <figure>
                                                                        <img src="css/user/images/news/test01.png"
                                                                            alt="Sample Article">
                                                                    </figure>
                                                                    <h2>Exercitation ullamco laboris nisi ut aliquip
                                                                    </h2>
                                                                </div>
                                                                <div class="item">
                                                                    <figure>
                                                                        <img src="css/user/images/news/img14.jpg"
                                                                            alt="Sample Article">
                                                                    </figure>
                                                                    <h2>Mauris elementum libero at pharetra aucto</h2>
                                                                </div>
                                                                <div class="item">
                                                                    <figure>
                                                                        <img src="css/user/images/news/img13.jpg"
                                                                            alt="Sample Article">
                                                                    </figure>
                                                                    <h2>Exercitation ullamco laboris nisi ut aliquip
                                                                    </h2>
                                                                </div>
                                                                <div class="item">
                                                                    <figure>
                                                                        <img src="css/user/images/news/img05.jpg"
                                                                            alt="Sample Article">
                                                                    </figure>
                                                                    <h2>Mauris elementum libero at pharetra aucto</h2>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="card-body">
                                                            <input type="file" id="imageInput" accept="image/*"
                                                                style="display: none;" multiple
                                                                onchange="updateCarouselImages()">
                                                            <div class="custom-change-mainImg"
                                                                style="margin-bottom: 20px;">
                                                                <input type="button" id="ImgSelectButton"
                                                                    value="Image Change" class="btn btn-outline-warning"
                                                                    style="width: 150px; 
                                                                        height: 40px; 
                                                                         font-size: 16px; 
                                                                        padding: 5px 10px;
                                                                        margin-right: 20px;">&nbsp;
                                                                <button type="button" id="ImgConfirmButton"
                                                                    class="btn btn-outline-success" style="width: 150px; 
                                                                        height: 40px; 
                                                                        font-size: 16px; 
                                                                        padding: 5px 10px;">Confirm</button>
                                                            </div>
                                                            <div class="custom-change-texts">
                                                                <button type="button" id="ImgSelectButton"
                                                                    class="btn btn-outline-warning" data-toggle="modal"
                                                                    data-target="#scrollmodal" style="width: 150px; 
                                                                                    height: 40px; 
                                                                                     font-size: 16px; 
                                                                                    padding: 5px 10px;
                                                                                    margin-right: 20px;">Text
                                                                    Change</button>

                                                            </div>
                                                        </div>
                                                    </div>


                                                    <div class="col-lg-4">
                                                        <div class="card-body">

                                                        </div>
                                                    </div>
                                                </div>

                                                첫번째 탭 내용
                                            </div>



                                            <div id="tab2" class="tab__content">
                                                <div class="row">
                                                    <div class="col-lg-8">
                                                        <div class="card-body">
                                                            <ul class="sponsored">
                                                                <li>
                                                                    <!-- 사용자가 이미지를 클릭하면, 이 속성을 사용하여 해당 이미지에 연결된 <input type="file" id="fileInput"> 요소를 식별하고, 이를 통해 파일 선택 창을 열어 사용자가 이미지 파일을 선택할 수 있게 만듭니다. -->
                                                                    <img src="css/user/images/sponsored.png"
                                                                        alt="Sponsored" data-input="fileInput1"></a>
                                                                    <input type="file" id="fileInput1" class="fileInput"
                                                                        accept="image/*">
                                                                    <img src="css/user/images/sponsored.png"
                                                                        alt="Sponsored" data-input="fileInput2"></a>
                                                                    <input type="file" id="fileInput2" class="fileInput"
                                                                        accept="image/*">
                                                                </li>
                                                                <li>
                                                                    <img src="css/user/images/sponsored.png"
                                                                        alt="Sponsored" data-input="fileInput3"></a>
                                                                    <input type="file" id="fileInput3" class="fileInput"
                                                                        accept="image/*">
                                                                    <img src="css/user/images/sponsored.png"
                                                                        alt="Sponsored" data-input="fileInput4"></a>
                                                                    <input type="file" id="fileInput4" class="fileInput"
                                                                        accept="image/*">
                                                                </li>
                                                                <li style="margin-top: 20px;">
                                                                    <img src="css/user/images/ads.png"
                                                                        alt="Sample Article" data-input="fileInput5"
                                                                        style="width: 750px;
                                                                    height: 80px; margin: 10px; cursor: pointer;"></a>
                                                                    <input type="file" id="fileInput5" class="fileInput"
                                                                        accept="image/*">
                                                                </li>
                                                            </ul>

                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <div class="card-body">

                                                        </div> <!-- /.card-body -->
                                                    </div>
                                                </div> <!-- /.row -->
                                                두번째 탭 내용
                                            </div>
                                            <div id="tab3" class="tab__content">
                                                <div class="row">
                                                    <div class="col-lg-8">
                                                        <div class="col-sm-6 col-lg-3">
                                                            <div class="card text-white bg-flat-color-1">
                                                                <div class="card-body">
                                                                    <div class="card-left pt-1 float-left" style="display: inline-block; vertical-align: top;">
                                                                        <h3 class="mb-0 fw-r">
                                                                            <span
                                                                                class="currency float-left mr-1">₩</span>
                                                                            <span class="count"
                                                                                id="PremiumPrice">23569</span>
                                                                        </h3>
                                                                        <p class="text-light mt-1 m-0">PREMIUM PRICEe
                                                                        </p>
                                                                    </div><!-- /.card-left -->

                                                                    <div class="card-right float-right text-right">
                                                                        <i
                                                                            class="icon fade-5 icon-lg pe-7s-diamond"></i>
                                                                    </div><!-- /.card-right -->
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="card-body">
                                                            <button type="button" id="PriceChangeButton"
                                                                class="btn btn-outline-warning" style="width: 150px; 
                                                                    height: 40px; 
                                                                    font-size: 16px; 
                                                                    padding: 5px 10px;
                                                                    margin-right: 20px;">Price Change</button>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <div class="card-body">

                                                        </div> <!-- /.card-body -->
                                                    </div>
                                                </div> <!-- /.row -->
                                                세번째 탭 내용
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- /.col-md-4 -->
                            </div>

                            <div class="modal fade" id="scrollmodal" tabindex="-1" role="dialog"
                                aria-labelledby="smallmodalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="container">
                                        <div class="login-content">
                                            <a href="index.html">
                                                <img class="align-content" src="css/admin/images/logo.png" alt="">
                                            </a>
                                            <div class="modal-content">
                                                <div class="login-form">
                                                    <form>
                                                        <div class="form-group">
                                                            <label>NOTICE</label>
                                                            <input type="text" class="form-control"
                                                                style="margin-bottom: 10px">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>MAIN IMAGE PHRASE</label>
                                                            <input type="text" class="form-control"
                                                                style="margin-bottom: 10px">
                                                            <input type="text" class="form-control"
                                                                style="margin-bottom: 10px">
                                                            <input type="text" class="form-control"
                                                                style="margin-bottom: 10px">
                                                            <input type="text" class="form-control">
                                                        </div>



                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">Cancel</button>
                                                            <button type="button" class="btn btn-primary"
                                                                data-dismiss="modal">Confirm</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div> <!-- .animated -->
                    </div> <!-- content -->

                    <!-- /.content -->
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
                    <!-- /.site-footer -->
                </div>
                <!-- /#right-panel -->

                <!-- Scripts -->
                <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
                <script
                    src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
                <script src="css/admin/assets/js/main.js"></script>


                <script src="js/admin/leftPanalActive.js"></script>
                <script src="js/admin/changeContent.js"></script>

                <script>
                    // ====================첫번째 텝======================
                    // 이미지 슬라이드 세팅
                    $(document).ready(function () {
                        var owl = $('.owl-carousel');
                        owl.owlCarousel({
                            items: 1, // 한번에 보여지는 이미지 수
                            loop: true, // 항목들을 무한으로 반복하여 보여줄지 여부
                            autoplay: true, // 자동으로 슬라이드 쇼를 시작할지 여부
                            autoplayTimeout: 3000, // 다음 이미지로 넘어가는 시간 (단위 : 밀리초)
                            autoplayHoverPause: true, // 마우스가 이미지에 위에 있을 때 자동 슬라이드를 일시중지 할지 여부

                        });
                    });



                    // 이미지 슬라이드 선택

                    document.getElementById('ImgSelectButton').addEventListener('click', function () {
                        document.getElementById('imageInput').click();
                    });

                    // 이미지 선택 시 실행할 함수

                    function updateCarouselImages() {
                        var input = document.getElementById('imageInput');
                        var fileList = input.files;
                        var owlCarouselDiv = document.getElementById('mainImg');

                        if (fileList.length === 0) {
                            return;
                        }

                        // 기존 캐러셀 내용 중 <h2> 태그의 내용을 배열로 저장
                        var h2Contents = [];
                        // loop 기능 사용시 관리자 개발도구를 확인해보면 복제된 요소 존재
                        // not(.cloned)를 사용해 복제된 요소 제외하고 선택
                        document.querySelectorAll('.owl-item:not(.cloned) .item h2').forEach(function (h2) {
                            h2Contents.push(h2.innerHTML);
                        });




                        owlCarouselDiv.innerHTML = '';

                        let imageLoadPromises = [];
                        for (let i = 0; i < fileList.length; i++) {
                            let promise = new Promise((resolve, reject) => {
                                var reader = new FileReader();
                                reader.onload = function (event) {
                                    // 이미지 로딩 완료 시 이미지 정보와 함께 Promise 해결
                                    resolve({
                                        src: event.target.result,
                                        h2Content: h2Contents[i],
                                        index: i // 순서 정보 포함
                                    });
                                }

                                // 파일 읽기
                                reader.readAsDataURL(fileList[i]);
                            });

                            // Promise 배열에 추가
                            imageLoadPromises.push(promise);
                        }

                        // 모든 이미지 로딩이 완료된 후 캐러셀에 순서대로 이미지 추가
                        // 이미지를 로드하고 캐러셀에 추가하는 과정에서 이미지의 순서를 보장
                        // 비동기적 로드시 이미지 로드의 완료 시점에 따라 순서가 바뀔 수 있음
                        Promise.all(imageLoadPromises).then((loadedImages) => {
                            // 이미지를 원래 순서대로 정렬
                            loadedImages.sort((a, b) => a.index - b.index).forEach(imageInfo => {
                                var newItem = document.createElement('div');
                                newItem.className = 'item';
                                newItem.innerHTML = '<figure><img src="' + imageInfo.src + '" alt="Selected Image"></figure>' +
                                    (imageInfo.h2Content ? '<h2>' + imageInfo.h2Content + '</h2>' : '');
                                owlCarouselDiv.appendChild(newItem);

                            });

                            resetOwlCarousel();
                        }).catch(error => {
                            console.error("이미지 로딩 중 오류 발생: ", error);
                        });
                        console.log(owlCarouselDiv);
                    }

                    function resetOwlCarousel() {
                        // 기존 캐러셀 인스턴스를 파괴하고 새로운 이미지들로 캐러셀 초기화
                        var owl = $('.owl-carousel');
                        owl.owlCarousel('destroy'); // 기존 캐러셀 파괴
                        owl.owlCarousel({ // 캐러셀 다시 생성
                            items: 1,
                            loop: true,
                            autoplay: true,
                            autoplayTimeout: 3000,
                            autoplayHoverPause: true,
                        });
                    }

                    // 기존에 있던 글을 모달창으로 불러옴
                    $(document).ready(function () {
                        // NOTICE 섹션의 텍스트를 입력 필드에 채워넣기, "badge" 부분 제외
                        $('#notice .item a').each(function (index) {
                            if (index < 2) { // 최대 2개의 입력 필드만 존재
                                // .badge 요소의 텍스트를 제외하고 가져오기
                                var noticeText = $(this).clone() // 요소 복제
                                    .children('.badge').remove().end() // .badge 요소 제거 후 원본 요소로 돌아가기
                                    .text().trim();
                                $('.form-group').eq(0).find('.form-control').eq(index).val(noticeText);
                            }
                        });

                        // MAIN IMAGE PHRASE 섹션의 텍스트를 입력 필드에 채워넣기
                        $('#mainImg .item h2').each(function (index) {
                            if (index < 4) { // 최대 4개의 입력 필드만 존재
                                var mainImgPhrase = $(this).text().trim();
                                $('.form-group').eq(1).find('.form-control').eq(index).val(mainImgPhrase);
                            }
                        });
                    });




                    // 'Confirm' 버튼에 클릭 이벤트 리스너를 추가합니다.
                    $(".btn-primary").click(function () {
                        // 각 입력 필드에서 텍스트를 가져옵니다.
                        var noticeText1 = $(".form-group").eq(0).find(".form-control").eq(0).val();
                        var noticeText2 = $(".form-group").eq(0).find(".form-control").eq(1).val();

                        var mainImageText1 = $(".form-group").eq(1).find(".form-control").eq(0).val();
                        var mainImageText2 = $(".form-group").eq(1).find(".form-control").eq(1).val();
                        var mainImageText3 = $(".form-group").eq(1).find(".form-control").eq(2).val();
                        var mainImageText4 = $(".form-group").eq(1).find(".form-control").eq(3).val();


                        //.owl-item 은 관리자 개발도구에서 나오는 class 명칭 cloned를 무시하기위해 사용
                        // 'cloned' 클래스를 가지지 않은 'notice' 섹션의 <a> 태그 내용을 업데이트합니다.
                        $(".owl-item:not(.cloned)").eq(0).find("a").html('<div class="badge">Notice!</div>' + noticeText1);
                        $(".owl-item:not(.cloned)").eq(1).find("a").html('<div class="badge">Notice!</div>' + noticeText2);

                        // 'cloned' 클래스를 가지지 않은 'mainImg' 섹션의 <h2> 태그 내용을 업데이트합니다.
                        $(".owl-item:not(.cloned)").eq(2).find("h2").text(mainImageText1);
                        $(".owl-item:not(.cloned)").eq(3).find("h2").text(mainImageText2);
                        $(".owl-item:not(.cloned)").eq(4).find("h2").text(mainImageText3);
                        $(".owl-item:not(.cloned)").eq(5).find("h2").text(mainImageText4);
                        resetOwlCarousel()
                    });
                    //============================ 두번째 텝 ================================
                    const images = document.querySelectorAll('.sponsored img');

                    // 각 이미지에 클릭 이벤트를 추가합니다.
                    images.forEach((image, index) => {
                        image.addEventListener('click', () => {
                            // 해당 이미지에 매칭되는 파일 입력 필드를 가져옵니다.
                            const fileInputId = image.getAttribute('data-input');
                            const fileInput = document.getElementById(fileInputId);

                            // 파일 입력을 클릭합니다.
                            fileInput.click();

                            // 파일이 선택되면 실행될 이벤트 리스너를 추가합니다.
                            fileInput.onchange = (event) => {
                                const file = event.target.files[0]; // 선택된 파일을 가져옵니다.
                                if (file) {
                                    // FileReader 객체를 사용하여 파일을 읽습니다.
                                    const reader = new FileReader();
                                    reader.onload = (e) => {
                                        // 읽기 작업이 완료되면 이미지의 src 속성을 변경합니다.
                                        image.src = e.target.result;
                                    };
                                    reader.readAsDataURL(file); // 파일을 Data URL 형식으로 읽습니다.
                                }
                            };
                        });
                    });


                    //============================ 세번째 텝 ================================

                    $(document).ready(function () {
                        $("#PriceChangeButton").click(function () {
                            if ($(this).hasClass("btn-outline-warning")) {
                                // 버튼이 'Price Change' 상태일 때
                                var currentPrice = $("#PremiumPrice").text();
                                $("#PremiumPrice").html('<input type="text" id="priceInput" value="' + currentPrice + '" style="border: 2px solid yellow; width: 100px; height: 30px; color: black; text-align: center;" />');
            $(this).text("Confirm").removeClass("btn-outline-warning").addClass("btn-outline-success");
                                $(this).text("Confirm").removeClass("btn-outline-warning").addClass("btn-outline-success");
                            } else {
                                // 버튼이 'Confirm' 상태일 때
                                var updatedPrice = $("#priceInput").val();
                                $("#PremiumPrice").text(updatedPrice);
                                $(this).text("Price Change").removeClass("btn-outline-success").addClass("btn-outline-warning");
                            }
                        });
                    });












                </script>

            </body>

            </html>