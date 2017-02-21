<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="lk.grp.synergy.control.ActivityControllerInterface" %>
<%@ page import="lk.grp.synergy.control.impl.ActivityController" %>
<%@ page import="lk.grp.synergy.control.impl.CourseController" %>
<%@ page import="lk.grp.synergy.control.impl.AdminCourseController" %>
<%@ page import="lk.grp.synergy.control.AdminCourseControllerInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.grp.synergy.model.Activity" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private Client client;
    private JsonParser jsonParser = new JsonParser();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Logger logger = LogManager.getLogger(this);

    private ActivityControllerInterface activityController = new ActivityController();
    private AdminCourseControllerInterface adminCourseController = new AdminCourseController();

%>
<%
    String baseURL = request.getServletContext().getInitParameter("rest-base-url");
    System.out.println(baseURL);
    int adminId = (Integer) request.getSession().getAttribute("adminId");
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie ie6 no-js" lang="en"> <![endif]-->
<!--[if IE 7 ]> <html class="ie ie7 no-js" lang="en"> <![endif]-->
<!--[if IE 8 ]> <html class="ie ie8 no-js" lang="en"> <![endif]-->
<!--[if IE 9 ]> <html class="ie ie9 no-js" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--><html class="no-js" lang="en"><!--<![endif]-->
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>OAD</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>

    <!--Bootstrap base CSS-->
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->

    <!--Custom CSS-->
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../css/sticky-footer-navbar.css">

    <!--Plugin CSS-->
    <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="../css/datatables.bootstrap3.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../css/bootstrap-select.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

</head>

<body>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <!-- navbar start -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle Navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <a class="navbar-brand" href="index.html">Online Activity Diary</a>
    </div>

    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="home">Home</a></li>
            <li><a href="activity-settings.jsp">Activity</a></li>
            <li><a href="signin">Sign out</a></li>
        </ul>
    </div>
</div>
<!-- navbar end -->

<!-- start of selector section -->
<div class="container selector-container">
    <!-- selectors row 1 start -->
    <div class="row select-row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <p class="first-title col-lg-4 col-md-4 col-sm-4">Course Code</p>
            <select class="selectpicker show-tick ol-lg-12 col-md-12 col-sm-12 col-xs-12" title="Course Code" id="courseSelector" onchange="searchCourse()">
                <option value="">All</option>
                <%
                    List<String> courseList = adminCourseController.getCourseList(adminId);
                    for (String courseCode: courseList) {
                %>
                <option value="<%=courseCode%>"><%=courseCode%>
                </option>
                <%
                    }
                    ;
                %>
            </select>
        </div>

        <%--<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">--%>
        <!--<p class="first-title col-lg-2 col-md-2 col-sm-2 col-xs-2">Type</p>-->
        <%--<div class="form-group ">--%>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <p class="first-title col-lg-4 col-md-4 col-sm-4">Activity Type</p>
            <select class="selectpicker show-tick col-lg-12 col-md-12 col-sm-12 col-xs-12" title="Activity" id="activitySelector" onchange="searchActivity()">
                <option value="">All</option>
                <option value="Day School">Day School</option>
                <option value="CAT">Continuous Assesment Test (CAT)</option>
                <option value="Lab">Lab Session</option>
                <option value="Viva">Viva Session</option>
                <option value="Final Examination">Final Examination</option>
            </select>
        </div>
        <%--</div>--%>
    </div>
    <!-- selectors row 1 end -->

    <!-- selectors row 2 start -->
    <div class="row select-row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <p class="first-title col-lg-2 col-md-2 col-sm-2 col-xs-2">From</p>
            <div class="dropdown col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <input type="text" class="datepicker col-lg-12 col-md-12 col-sm-12 col-xs-12" id="start-date" placeholder="From" onselect="searchDateRange()">
                <%--<i class="fa fa-calendar col-lg-2 col-md-2 col-sm-2 col-xs-2"></i>--%>
            </div>
        </div>

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <p class="first-title col-lg-2 col-md-2 col-sm-2 col-xs-2">To</p>
            <div class="dropdown col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <input type="text" class="datepicker col-lg-12 col-md-12 col-sm-12 col-xs-12" id="end-date"
                       placeholder="To">
                <%--<i class="fa fa-calendar col-lg-2 col-md-2 col-sm-2 col-xs-2"></i>--%>
            </div>
        </div>
    </div>
    <!-- selectors row 2 end -->
</div>
<!-- end of selector section -->

<!-- start of table -->
<div class="container">
    <table id="activityTable" width="100%" class="table table-striped table-bordered table-responsive">
        <thead>
        <tr>
            <th>Course</th>
            <th>Activity</th>
            <th>Date</th>
            <th>Time</th>
            <th>Centre</th>
        </tr>
        </thead>
        <tbody>

            <%
                List<Activity> allActivities = activityController.getAllActivities();
                for (Activity activity : allActivities) {
                    String courseCode = activity.getCourseCode();
                    String name = activity.getName();
                    String date = (DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(activity.getDate());
                    String startTime = (DateTimeFormatter.ofPattern("HH:mm:ss")).format(activity.getStartTime());
                    String endTime = (DateTimeFormatter.ofPattern("HH:mm:ss")).format(activity.getEndTime());
                    String venue = activity.getVenue();
            %>
            <tr>
                <td><%=courseCode%></td>
                <td><%=name%></td>
                <td><%=date%></td>
                <td><%=startTime+"-"+endTime%></td>
                <td><%=venue%></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <!-- start of buttons -->
    <div class="container buttons-container">
        <button class="pull-right bottom-button" data-toggle="modal" data-target="#myModal">Add New</button>
        <button class="pull-right bottom-button" >Edit</button>
    </div>
    <!-- end of buttons -->

</div>
<!-- end of table -->


<!-- start of footer -->
<footer class="footer">
    <div class="container footer-container">
        <div class="row">
            <!-- <div class="footer-text col-lg-6 col-md-6 col-sm-12 col-xs-12"> -->
            <p><a href="http://www.ou.ac.lk/home/" target="_blank">The Open University of Sri Lanka</a></p>
            <!-- </div> -->
            <!-- <div class="footer-text col-lg-6 col-md-6 col-sm-12 col-xs-12"> -->
            <p>Solution by <a href="https://en.wikipedia.org/wiki/Mahinda_Rajapaksa" target="_blank">Synergy</a>&copy
            </p>
            <!-- </div> -->
        </div>
    </div>
</footer>
<!-- end of footer -->

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <form id="addNewActivityForm">
                    <div class="form-group">
                        <select class="selectpicker show-tick ol-lg-12 col-md-12 col-sm-12 col-xs-12 form-control" title="Course Code" id="courseCode" onchange="searchCourse()" autofocus>
                            <%
                                for (String courseCode: courseList) {
                            %>
                            <option value="<%=courseCode%>"><%=courseCode%>
                            </option>
                            <%
                                }
                                ;
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="selectpicker show-tick col-lg-12 col-md-12 col-sm-12 col-xs-12 form-control" title="Activity" id="activityType">
                            <option value="DAY_SCHOOL">Day School</option>
                            <option value="CAT">Continuous Assessment Test (CAT)</option>
                            <option value="LAB">Lab Session</option>
                            <option value="VIVA">Viva Session</option>
                            <option value="FE">Final Examination</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" class="datepicker form-control" id="activityDate" placeholder="Activity Date">
                    </div>
                    <div class="form-group">
                        <input type="text"  id="startTime" placeholder="Start Time" class="timepicker form-control">
                    </div>
                    <div class="form-group">
                        <input type="text"  id="endTime" placeholder="End Time" class="timepicker form-control">
                    </div>
                    <div class="form-group">

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" >Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<!--Plugin JS-->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/datatables.min.js"></script>
<script src="../js/datatables.bootstrap3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="../js/jquery-timepicker.min.js"></script>

<!--Bootstrap JS-->
<script src="../js/bootstrap.min.js"></script>

<!--Custom JS-->
<script src="../js/custom.js"></script>
</body>
</html>