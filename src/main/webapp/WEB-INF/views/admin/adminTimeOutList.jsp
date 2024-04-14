<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->

<head>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>adminTimeOutList</title>
  <meta name="description" content="Ela Admin - HTML5 Admin Template">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
  <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
  <link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
  <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
  <link rel="stylesheet" href="css/admin/assets/css/cs-skin-elastic.css">
  <link rel="stylesheet" href="css/admin/assets/css/lib/datatable/dataTables.bootstrap.min.css">
  <link rel="stylesheet" href="css/admin/assets/css/style.css">
  <style type='text/css'>
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

    #bootstrap-data-table td:nth-child(5) {
      text-align: center;
      /* State 컬럼의 모든 td 요소를 가운데 정렬 */
    }
  </style>
</head>

<body>
  <!-- Left Panel -->
  <chalKagTags:adminSidebar />
  <!-- /#left-panel -->
  <!-- Right Panel -->
  <div id="right-panel" class="right-panel">
    <!-- Header-->
    <header id="header" class="header">
      <div class="top-left">
        <div class="navbar-header">
          <a class="navbar-brand" href="adminMain"><img src="css/user/images/chalKagLogo.png" alt="Logo"></a>
          <a class="navbar-brand hidden" href="adminMain"><img src="css/admin/images/logo2.png" alt="Logo"></a>
          <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
        </div>
      </div>
    </header>
    <!-- /#header -->
    <!-- Content -->
    <div class="content">
      <!-- Animated -->
      <div class="animated fadeIn">
        <!-- topWidgets-->
        <chalKagTags:adminTopWidget />
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <strong class="card-title">Data Table</strong>
              </div>
              <div class="card-body">
                <table id="bootstrap-data-table" class="table table-striped table-bordered">
                  <thead>
                    <tr>
                      <th>No</th>
                      <th>TimeOut Email</th>
                      <th>TimeOut Nickname</th>
                      <th>Date</th>
                      <th></th>
                    </tr>
                  </thead>
                  <c:set var="counter" value="1" />
                  <!-- 반복문을 통해 데이터를 삽입 -->
                  <c:forEach items="${timeOutList}" var="timeOutData">
                    <tr>
                      <td>${counter}</td>
                      <td>${timeOutData.memberId}</td>
                      <td>${timeOutData.memberNickname}</td>
                      <td>${timeOutData.timeOutDate}</td>
                      <td><a href="unHold?memberId=${timeOutData.memberId}"
                          class="btn btn-success btn-sm">UNHOLD</a></td>
                      <c:set var="counter" value="${counter + 1}" />
                    </tr>
                  </c:forEach>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="clearfix"></div>
    <!-- Footer -->
    <footer class="site-footer">
      <div class="footer-inner bg-white">
          <div class="row">
              <div class="col-sm-6">
                  Copyright &copy; 2023 - 2024 INFINITY STONE . ALL RIGHT RESERVED.
              </div>
          </div>
      </div>
  </footer>
  </div><!-- /#right-panel -->
  <!-- Right Panel -->
  <!-- Scripts -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
  <script src="css/admin/assets/js/main.js"></script>
  <script src="css/admin/assets/js/lib/data-table/datatables.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/dataTables.buttons.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/jszip.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/vfs_fonts.js"></script>
  <script src="css/admin/assets/js/lib/data-table/buttons.html5.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/buttons.print.min.js"></script>
  <script src="css/admin/assets/js/lib/data-table/buttons.colVis.min.js"></script>
  <script src="css/admin/assets/js/init/datatables-init.js"></script>
</body>

</html>