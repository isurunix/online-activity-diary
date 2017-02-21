<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.client.WebTarget" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.grp.synergy.control.AdminCourseControllerInterface" %>
<%@ page import="lk.grp.synergy.control.impl.AdminCourseController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private AdminCourseControllerInterface adminCourseController = new AdminCourseController();
    private Client client;
    private JsonParser jsonParser = new JsonParser();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Logger logger = LogManager.getLogger(this);
%>
<%
    String baseURL = request.getServletContext().getInitParameter("rest-base-url");
    int adminId = (int) request.getSession().getAttribute("adminId");
    client = ClientBuilder.newClient();
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie ie6 no-js" lang="en"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie ie7 no-js" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie ie8 no-js" lang="en"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie ie9 no-js" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--><html class="no-js" lang="en"><!--<![endif]-->
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>OAD</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="" />
    <meta name="keywords" content="" />

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
    <link rel="stylesheet" href="../css/jquery.timepicker.css">
    <%--<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">--%>

    <%--<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>--%>
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

        <a class="navbar-brand" href="index.jsp">Online Activity Diary</a>
    </div>

    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="home">Home</a></li>
            <li class="active"><a href="#">Activity</a></li>
            <li><a href="login">Sign out</a></li>
        </ul>
    </div>
</div>
<!-- navbar end -->

<div class="container settings-body">
    <div class="row">
        <!-- sidebar start -->
        <div class="col-lg-2 col-md-2 col-sm-2">
            <button id="btnAdd" class="btn btn-default large-button" onclick="showAddForm()">Add Activity</button>
            <button  id="btnUpdate" class="btn btn-default large-button  active-button" onclick="showUpdateForm()">Update Activity</button>
        </div>
        <!-- sidebar end -->

        <!-- start of fields -->
        <%
            List<String> courseList = adminCourseController.getCourseList(adminId);
        %>
        <div class="col-lg-10 col-md-10 col-sm-10 settings-content">
            <form id="addNewActivityForm">
                <div class="form-group">
                    <select class="selectpicker show-tick form-control" title="Course Code" id="courseCode" onchange="searchCourse()" autofocus>
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
                    <select class="selectpicker show-tick  form-control" title="Activity Type" id="activityType">
                        <option value="DAY_SCHOOL">Day School</option>
                        <option value="CAT">Continuous Assessment Test (CAT)</option>
                        <option value="LAB">Lab Session</option>
                        <option value="VIVA">Viva Session</option>
                        <option value="FE">Final Examination</option>
                        <option value="PRESENTATION">Presentation</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text"  id="name" placeholder="Activity" class="form-control">
                </div>
                <div class="form-group">
                    <input type="text" class="datepicker form-control" id="activityDate" placeholder="Activity Date">
                </div>
                <div class="form-group">
                    <input type="text" id="startTime" placeholder="Start Time" class="timepicker form-control">
                </div>
                <div class="form-group">
                    <input type="text"  id="endTime" placeholder="End Time" class="timepicker form-control">
                </div>
                <div class="form-group">
                    <input type="text"  id="center" placeholder="Center" class="form-control">
                </div>
                <div class="form-group">
                    <input type="text"  id="group" placeholder="Group" class="form-control">
                </div>
                <div class="form-group">
                    <input id="formAddBtn" type="submit" class="btn btn-default pull-right" value="Add Activity">
                </div>
            </form>

            <form id="updateActivityForm" class="hidden">
                <div class="form-group">
                    <select class="selectpicker show-tick form-control" title="Course Code" id="u_courseCode" onchange="searchCourse()" autofocus>
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
                    <select class="selectpicker show-tick  form-control" title="Activity" id="activity">
                        <option value="DAY_SCHOOL">Day School</option>
                        <option value="CAT">Continuous Assessment Test (CAT)</option>
                        <option value="LAB">Lab Session</option>
                        <option value="VIVA">Viva Session</option>
                        <option value="FE">Final Examination</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" class="datepicker form-control" id="u_activityDate" placeholder="Activity Date">
                </div>
                <div class="form-group">
                    <input type="text" id="u_startTime" placeholder="Start Time" class="timepicker form-control">
                </div>
                <div class="form-group">
                    <input type="text"  id="u_endTime" placeholder="End Time" class="timepicker form-control">
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-default pull-right" value="Update Activity">
                </div>
            </form>

        </div>
        <!-- end of fields -->
    </div>
</div>

<!-- start of footer -->
<footer class="footer">
    <div class="container footer-container">
        <div class="row">
            <!-- <div class="footer-text col-lg-6 col-md-6 col-sm-12 col-xs-12"> -->
            <p><a href="http://www.ou.ac.lk/home/" target="_blank">The Open University of Sri Lanka</a></p>
            <!-- </div> -->
            <!-- <div class="footer-text col-lg-6 col-md-6 col-sm-12 col-xs-12"> -->
            <p>Solution by <a href="https://en.wikipedia.org/wiki/Mahinda_Rajapaksa" target="_blank">Synergy</a>&copy</p>
            <!-- </div> -->
        </div>
    </div>
</footer>
<!-- end of footer -->

<%--Confirm Dialog--%>
<div id="dialog-confirm">
</div>
<%--End Conform Dialog--%>

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