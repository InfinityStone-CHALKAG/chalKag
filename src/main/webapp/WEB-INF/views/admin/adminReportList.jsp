<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <title>Ela Admin - HTML5 Admin Template</title>
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

                        #bootstrap-data-table th:nth-child(1) {
                            width: 2% !important;
                        }

                        #bootstrap-data-table th:nth-child(2) {
                            width: 3% !important;
                        }

                        #bootstrap-data-table th:nth-child(3) {
                            width: 14% !important;
                        }

                        #bootstrap-data-table th:nth-child(4) {
                            width: 14% !important;
                        }

                        #bootstrap-data-table th:nth-child(5) {
                            width: 16% !important;
                        }

                        #bootstrap-data-table th:nth-child(6) {
                            width: 34% !important;
                        }

                        #bootstrap-data-table th:nth-child(7) {
                            width: 7% !important;
                        }

                        #bootstrap-data-table th:nth-child(8) {
                            width: 7% !important;
                        }

                        #bootstrap-data-table td:nth-child(8) {
                            text-align: center;
                            /* State 컬럼의 모든 td 요소를 가운데 정렬 */
                        }

                        #bootstrap-data-table td {
                            overflow: hidden;
                            white-space: nowrap;
                            /* 텍스트가 넘칠 경우 줄바꿈을 방지합니다. */
                            text-overflow: ellipsis !important;
                            /* 넘치는 텍스트가 있을 경우 ...으로 표시합니다. */
                        }

                        #trashIcon:hover,
                        #openedMailIcon:hover {
                            position: relative;
                        }

                        #trashIcon:hover:after,
                        #openedMailIcon:hover:after {
                            background-color: #000000;
                            background-color: rgba(0, 0, 0, 0.8);

                            -webkit-box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
                            -moz-box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
                            box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);

                            -webkit-border-radius: 5px;
                            -moz-border-radius: 5px;
                            border-radius: 5px;

                            color: #FFFFFF;
                            font-size: 12px;
                            content: attr(tooltip);

                            margin-bottom: 10px;
                            top: 130%;
                            padding: 7px 12px;
                            position: absolute;
                            width: max-content;
                            min-width: 50px;
                            max-width: 300px;
                            word-wrap: break-word;

                            z-index: 9999;
                        }

                        #trashIcon:hover:after {
                            left: -200%;
                            /* trashIcon의 left 값 */
                        }

                        #openedMailIcon:hover:after {
                            left: -250%;
                            /* openedMailIcon의 left 값 */
                        }
                    </style>
                </head>

                <body>
                 <!-- Left Panel -->
                    
                 <chalKagTags:adminSidebar/>
                  
                 <!-- /#left-panel -->
                 <!-- Right Panel -->
                 <div id="right-panel" class="right-panel">
                     <!-- Header-->
                     <header id="header" class="header">
                         <div class="top-left">
                             <div class="navbar-header">
                                 <a class="navbar-brand" href="adminMain"><img src="css/user/images/chalKagLogo.png"
                                         alt="Logo"></a>
                                 <a class="navbar-brand hidden" href="adminMain"><img
                                         src="css/admin/images/logo2.png" alt="Logo"></a>
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

                            <chalKagTags:adminTopWidget/>
                                <div class="row">

                                    <div class="col-md-12">
                                        <div class="card">
                                            <div class="card-header">
                                                <div class="col-sm-12 col-md-6">

                                                    <strong class="card-title">Report
                                                        List </strong>&nbsp;
                                                    <label id="checkboxCount" style="margin-bottom:0%;"> </label>
                                                    <label id="trashIcon" style="margin-bottom:0%; margin-left:3%;"
                                                        tooltip="Delete"></label>
                                                    <label id="openedMailIcon" style="margin-bottom:0%; margin-left:1%;"
                                                        tooltip="Mark as read"> </label>

                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <table id="bootstrap-data-table"
                                                    class="table table-striped table-bordered"
                                                    style="table-layout: fixed;">
                                                    <thead>
                                                        <tr>
                                                            <th id="checkbox" style="width:0%"><input type="checkbox"
                                                                    id="selectAll">
                                                            </th>
                                                            <th>No</th>
                                                            <th>Reporter</th>
                                                            <th>Suspector Email</th>
                                                            <th>Suspector Nickname</th>
                                                            <th>Report Content</th>
                                                            <th>Date</th>
                                                            <th>State</th>
                                                        </tr>
                                                    </thead>
                                                    <c:set var="counter" value="1" />
                                                    <!-- 반복문을 통해 데이터를 삽입 -->
                                                    <c:forEach items="${reportList}" var="reportData">
                                                        <tr>
                                                            <c:if test="${reportData.reportState eq 'UNREAD'}">
                                                                <td><input type="checkbox" name="reportId"
                                                                        value="${reportData.reportId}"></td>
                                                                <td><strong>${counter}</strong></td>
                                                                <td><strong>${reportData.memberId}</strong></td>
                                                                <td><strong>${reportData.reportSuspector}</strong></td>
                                                                <td><strong>${reportData.suspectorNickname}</strong>
                                                                </td>
                                                                <td><strong><a
                                                                            href="adminReportSingle?reportId=${reportData.reportId}">${reportData.reportContent}</a></strong>
                                                                </td>
                                                                <td><strong>${fn:substring(reportData.reportDate, 0,
                                                                        10)}</strong></td>
                                                                <td><strong class="badge badge-primary"
                                                                        style="font-size:16px;">${reportData.reportState}</strong>
                                                                    - </td>
                                                            </c:if>
                                                            <c:if test="${reportData.reportState ne 'UNREAD'}">
                                                                <td><input type="checkbox" name="reportId"
                                                                        value="${reportData.reportId}"></td>
                                                                <td>${counter}</td>
                                                                <td>${reportData.memberId}</td>
                                                                <td>${reportData.reportSuspector}</td>
                                                                <td>${reportData.suspectorNickname}</td>
                                                                <td><a
                                                                        href="adminReportSingle?reportId=${reportData.reportId}">${reportData.reportContent}</a>
                                                                </td>
                                                                <td>${fn:substring(reportData.reportDate, 0, 10)}</td>
                                                                <c:if test="${reportData.reportState eq 'READ'}">
                                                                    <td>
                                                                        <div class="badge badge-success"
                                                                            style="font-size:16px;">
                                                                            ${reportData.reportState}</div>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${reportData.reportState eq 'HOLD'}">
                                                                    <td>
                                                                        <div class="badge badge-danger"
                                                                            style="font-size:16px;">
                                                                            ${reportData.reportState}</div>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${reportData.reportState eq 'REJECT'}">
                                                                    <td>
                                                                        <div class="badge badge-secondary"
                                                                            style="font-size:16px;">
                                                                            ${reportData.reportState}</div>
                                                                    </td>
                                                                </c:if>
                                                            </c:if>
                                                            <c:set var="counter" value="${counter + 1}" />
                                                        </tr>
                                                    </c:forEach>
                                                </table>
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
                                        Copyright &copy; 2023 - 2024 INFINITY STONE . ALL RIGHT RESERVED.
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
                    <script src="css/user/scripts/sweetalert/dist/sweetalert.min.js"></script>

                    <script src="js/admin/leftPanalActive.js"></script>

                    <script>
                        // <th> 요소를 가져옵니다.



                        // checkbox TH 요소 선택
                        var checkboxTh = document.getElementById('checkbox');
                        checkboxTh.classList.remove('sorting_asc', 'sorting', 'sorting_desc', 'sorting_asc_disabled', 'sorting_desc_disabled');
                        // checkboxTh 요소에 대한 jQuery 객체를 만듭니다.
                        var $checkboxTh = $(checkboxTh);

                        // click 이벤트 리스너 제거
                        $checkboxTh.off('click.DT');

                        // keypress 이벤트 리스너 제거
                        $checkboxTh.off('keypress.DT');

                        // selectstart 이벤트 리스너 제거
                        $checkboxTh.off('selectstart.DT');

                        // 다른 th를 눌러도 checkbox쪽 column은 변하지 않도록 설정
                        document.addEventListener('DOMContentLoaded', function () {
                            var allThExceptCheckbox = document.querySelectorAll('th:not(#checkbox_th)');

                            allThExceptCheckbox.forEach(function (th) {
                                th.addEventListener('click', function () {

                                    checkboxTh.classList.remove('sorting_asc', 'sorting', 'sorting_desc', 'sorting_asc_disabled', 'sorting_desc_disabled');
                                });
                                //체크박스 체크시 재정렬 불가
                                th.addEventListener('mousedown', function (event) {
                                    if (th !== checkboxTh) {
                                        if (uncheckAllCheckboxesAlert()) {
                                            event.preventDefault();
                                            console.log("정렬 중지");
                                        }
                                    }
                                });
                            });
                        });

                        document.querySelector('select[name="bootstrap-data-table_length"]').addEventListener('mousedown', function (event) {
                            if (uncheckAllCheckboxesAlert()) {
                                event.preventDefault(); // 옵션 변경 이벤트 중지
                                console.log("옵션 변경 중지");
                            }
                        });

                        $(document).on('mousedown', '.pagination .page-link', function (e) {
                            if (uncheckAllCheckboxesAlert()) {
                                e.preventDefault(); // 페이지 이동 이벤트 중지
                                console.log("페이지 이동 중지");
                            }
                        });

                        //현재 페이지에 로드된 체크박스 수
                        var checkboxes = document.getElementsByName('reportId');
                        // 체크된 개수를 저장할 변수 초기화
                        var checkedCount = 0;

                        // th의 체크박스 클릭 시
                        document.getElementById('selectAll').addEventListener('click', function () {
                            // 체크박스 상태를 가져옴
                            var isChecked = this.checked;
                            // 모든 체크박스를 가져와서 상태 변경
                            for (var i = 0; i < checkboxes.length; i++) {
                                checkboxes[i].checked = isChecked;
                            }

                            // 체크된 개수 다시 계산
                            checkedCount = document.querySelectorAll('input[name="reportId"]:checked').length;
                            // HTML에 체크된 개수를 동적으로 출력
                            document.getElementById('checkboxCount').textContent = 'Checked : ' + checkedCount;
                            addIcons();
                            if (checkedCount <= 0) {
                                document.getElementById('checkboxCount').textContent = '';
                                document.getElementById("trashIcon").innerHTML = '';
                                document.getElementById("openedMailIcon").innerHTML = '';
                            }
                        });


                        function setupCheckboxListeners() {
    // 각 체크박스 요소에 대해 이벤트 리스너 추가
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            // 체크된 개수 다시 계산
            checkedCount = document.querySelectorAll('input[name="reportId"]:checked').length;
            // HTML에 체크된 개수를 동적으로 출력
            document.getElementById('checkboxCount').textContent = 'Checked : ' + checkedCount;
            addIcons();

            if (checkedCount <= 0) {
                document.getElementById('checkboxCount').textContent = '';
                document.getElementById("trashIcon").innerHTML = '';
                document.getElementById("openedMailIcon").innerHTML = '';
            }
        });
    });
}


// 페이지 로드 시와 pagination으로 페이지가 변경될 때 이벤트 리스너 다시 연결
document.addEventListener("DOMContentLoaded", setupCheckboxListeners);
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("page-link")) {
        // pagination 클릭 시 이벤트 리스너 다시 연결
        setupCheckboxListeners();
    }
});

                        function uncheckAllCheckboxesAlert() {
                            var checkboxes = document.getElementsByName('reportId');
                            var isChecked = false;
                            console.log("1+" + isChecked);

                            // 모든 체크박스를 확인하며 체크 여부를 확인합니다.
                            for (var i = 0; i < checkboxes.length; i++) {
                                if (checkboxes[i].checked) {
                                    isChecked = true;
                                    console.log("2" + isChecked);
                                    break;
                                }
                            }

                            // 하나 이상이 체크되어 있으면 alert을 띄우고 true 반환
                            if (isChecked) {
                                swal("WARNING", "체크박스를 해제 해주세요.", "error", {
                                    button: "OK",
                                });
                                return true;
                            } else {
                                return false; // 선택된 체크박스가 없으면 false 반환하여 이벤트 계속 진행
                            }
                        }

                        // HTML에서 reportId로 이름이 지정된 모든 체크박스 요소 가져오기

                        //아이콘 동적 출력
                        function addIcons() {
                            // 아이콘을 표시할 요소들의 ID를 사용하여 가져옵니다.
                            var trashIcon = document.getElementById("trashIcon");
                            var openedMailIcon = document.getElementById("openedMailIcon");

                            // HTML 폼에서 선택된 모든 체크박스의 값을 배열로 가져오기
                            var selectedReportIds = []; // 선택된 보고서 ID를 저장할 배열

                            // 모든 체크된 체크박스 요소를 선택하고 각각의 값을 배열에 추가
                            $('input[type=checkbox][name=reportId]:checked').each(function () {
                                selectedReportIds.push($(this).val());
                            });
                            -
                                // 선택된 보고서 ID들을 출력하여 확인
                                console.log(selectedReportIds);

                            // 여기에 열린 메일 아이콘 클릭 시 실행될 동작을 작성합니다.
                            // 예를 들어, 클릭 시 메일 열기 함수를 호출하거나 다른 작업을 수행할 수 있습니다.
                            console.log("열린 메일 아이콘을 클릭했습니다.");

                            // 휴지통 아이콘 엘리먼트를 생성합니다.
                            var trashIconElement = document.createElement("a");
                            trashIconElement.href = "selectedStateUpdate?selectedReportIds=" + selectedReportIds + "&state=toDeleted";
                            trashIconElement.innerHTML = '<i class="fa fa-trash-o"></i>';
                           

                            // 열린 메일 아이콘 엘리먼트를 생성합니다.
                            var openedMailIconElement = document.createElement("a");
                            openedMailIconElement.href = "selectedStateUpdate?selectedReportIds=" + selectedReportIds + "&state=toRead";
                            openedMailIconElement.innerHTML = '<i class="fa fa-envelope-open-o"></i>';

                            // 기존 내용을 지웁니다.
                            trashIcon.innerHTML = '';
                            openedMailIcon.innerHTML = '';

                            // 새로운 아이콘을 라벨에 추가합니다.
                            trashIcon.appendChild(trashIconElement);
                            openedMailIcon.appendChild(openedMailIconElement);
                        }




                    </script>

                </body>

                </html>