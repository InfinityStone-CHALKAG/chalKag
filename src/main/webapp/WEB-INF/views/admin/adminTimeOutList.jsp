<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="chalKagTags" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <!doctype html>
        <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
        <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
        <!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
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
            <link rel="stylesheet"
                href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
            <link rel="stylesheet"
                href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet"
                href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
            <link rel="stylesheet"
                href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
            <link rel="stylesheet" href="css/admin/assets/css/cs-skin-elastic.css">
            <link rel="stylesheet" href="css/admin/assets/css/lib/datatable/dataTables.bootstrap.min.css">
            <link rel="stylesheet" href="css/admin/assets/css/style.css">

                type='text/css'>
            <style>
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
                    <li> <a href="adminTimeOutList"><i class="menu-icon fa fa-ban"></i>TimeOut List</a>
                    </li>
                    <li> <a href="adminWebContent"><i class="menu-icon fa fa-picture-o"></i>Web
                        Content</a>
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
                    <a class="navbar-brand" href="/adminMain"><img src="css/admin/images/logo.png" alt="Logo"></a>
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
                          <input class="form-control mr-sm-2" type="text" placeholder="Search ..." aria-label="Search">
                          <button class="search-close" type="submit"><i class="fa fa-close"></i></button>
                        </form>
                      </div>

                      <div class="dropdown for-notification">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="notification"
                          data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                            <span class="photo media-left"><img alt="avatar" src="css/admin/images/avatar/1.jpg"></span>
                            <div class="message media-body">
                              <span class="name float-left">Jonathan Smith</span>
                              <span class="time float-right">Just now</span>
                              <p>Hello, this is an example msg</p>
                            </div>
                          </a>
                          <a class="dropdown-item media" href="#">
                            <span class="photo media-left"><img alt="avatar" src="css/admin/images/avatar/2.jpg"></span>
                            <div class="message media-body">
                              <span class="name float-left">Jack Sanders</span>
                              <span class="time float-right">5 minutes ago</span>
                              <p>Lorem ipsum dolor sit amet, consectetur</p>
                            </div>
                          </a>
                          <a class="dropdown-item media" href="#">
                            <span class="photo media-left"><img alt="avatar" src="css/admin/images/avatar/3.jpg"></span>
                            <div class="message media-body">
                              <span class="name float-left">Cheryl Wheeler</span>
                              <span class="time float-right">10 minutes ago</span>
                              <p>Hello, this is an example msg</p>
                            </div>
                          </a>
                          <a class="dropdown-item media" href="#">
                            <span class="photo media-left"><img alt="avatar" src="css/admin/images/avatar/4.jpg"></span>
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
                      <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                        <img class="user-avatar rounded-circle" src="css/admin/images/admin.jpg" alt="User Avatar">
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
                                <div class="stat-text"><span class="count">1450000</span>
                                </div>
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
                          <tbody>
                            <tr>
                              <td>Tiger Nixon</td>
                              <td>System Architect</td>
                              <td>Edinburgh</td>
                              <td>$320,800</td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>Garrett Winters</td>
                              <td>Accountant</td>
                              <td>Tokyo</td>
                              <td>$170,750</td>
                              <td></td>
                            </tr>
                            <tr>
                              <td>Ashton Cox</td>
                              <td>Junior Technical Author</td>
                              <td>San Francisco</td>
                              <td>$86,000</td>
                              <td></td>
                            </tr>
                          </tbody>
                        </table>
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
                      Copyright &copy; 2018 Ela Admin
                    </div>
                    <div class="col-sm-6 text-right">
                      Designed by <a href="https://colorlib.com">Colorlib</a>
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
            <script
              src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
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