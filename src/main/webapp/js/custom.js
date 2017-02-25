/**
 * Created by isuru on 2/8/17.
 */

var table;

$(document).ready(function () {

  //initialize datatables plugin
  table = $('#activityTable').DataTable({
    "order": [[2, "asc"]],
    select: true,
    dom: 'Bfrtip',
    buttons: [
      {
        extend: 'selected',
        text: "Test",
        action: function ( e, dt, node, config ) {
          var rows = dt.rows( { selected: true } ).count();

          alert( 'There are '+rows+'(s) selected in the table' );
        }
      }
    ]
  });

  $(".datepicker").datepicker({
    onSelect: function () {
      table.draw();
    },
    changeMonth: true,
    changeYear: true,
    dateFormat: 'yy-mm-dd'
  });

  $("#start-date").datepicker("setDate", "2017-01-01");
  $("#end-date").datepicker("setDate", "2017-12-31");

  $('.timepicker').timepicker({
    'timeFormat': 'H:i:s',
    'minTime': '9:00am',
    'maxTime': '6:00pm'
  });
  $('.timepicker').timepicker('option', {useSelect: true});
  $(".ui-timepicker-select").addClass("form-control");

});

$(function () {
    if(window.location.hash === "#update"){
      showUpdateForm();
    }else if(window.location.hash === "#delete"){

    }else{
      showAddForm();
    }
});

$.fn.dataTable.ext.search.push(
  function (settings, data, dataIndex) {
    var startDate = Date.parse($("#start-date").val());
    var endDate = Date.parse($("#end-date").val());
    var date = Date.parse(data[2]);

    if (isNaN(startDate) || isNaN(endDate) || isNaN(date)) {
      console.log("date null");
      return true;
    }
    if (date >= startDate && date <= endDate) {
      console.log("s:" + startDate);
      console.log("e:" + endDate);
      console.log("d:" + date);
      return true;
    }
    return false;
  }
);


function searchCourse() {
  var selectedCourse = $("#courseSelector").val();
  console.log(selectedCourse);
  table.search(selectedCourse).draw();
}

function searchActivity() {
  var selectedActivity = $("#activitySelector").val();
  console.log(selectedActivity);
  table.columns(1).search(selectedActivity).draw();
}

function startDateFilter() {
  var startDate = $("#start-date").val();
}

function showAddForm() {

  if ($("#addFormDiv").hasClass("hidden")) {
    $("#addFormDiv").removeClass("hidden");
    $("#updateDiv").addClass("hidden");
    $("#deleteDiv").addClass("hidden");
    window.location.hash="add";
  }

  if ($("#btnAdd").hasClass("active-button")) {
    $("#btnAdd").removeClass("active-button");
  }

  if (!$("#btnUpdate").hasClass("active-button")) {
    $("#btnUpdate").addClass("active-button");
    $("#btnDelete").addClass("active-button");
  }
}

function showUpdateForm() {
  if ($("#updateDiv").hasClass("hidden")) {
    // $("#updateActivityForm").removeClass("hidden");
    // $("#updateActivityCourseSelect").removeClass("hidden");
    $("#updateDiv").removeClass("hidden");
    $("#deleteDiv").addClass("hidden");
    $("#addFormDiv").addClass("hidden");
    window.location.hash="update";
  }

  if (!$("#btnAdd").hasClass("active-button")) {
    $("#btnAdd").addClass("active-button");
    $("#btnDelete").addClass("active-button");
  }

  if ($("#btnUpdate").hasClass("active-button")) {
    $("#btnUpdate").removeClass("active-button");
  }
}

function showDeleteForm() {
  if($("#deleteDiv").hasClass("hidden")){
    $("#deleteDiv").removeClass("hidden");
    $("#updateDiv").addClass("hidden");
    $("#addFormDiv").addClass("hidden");
    $("#btnAdd").addClass("active-button");
    $("#btnUpdate").addClass("active-button");
    $("#btnDelete").removeClass("active-button");
    window.location.hash="delete";
  }
}

$("#addNewActivityForm").submit(function (e) {

  e.preventDefault();

  var aDate = $("#activityDate").val();
  var startTime = $("#startTime").val();
  var endTime = $("#endTime").val();

  $.get("http://localhost:8080/oad/oad-api/activity/" + aDate + "/" + startTime + "/" + endTime, function (data, status) {
    var collisionCount = data.collisionCount;
    console.log(collisionCount);
    if (collisionCount > 0) {
      console.log("Open Confirm Dialog");
      openConfirmDialog(collisionCount);

    } else {
      saveActivity();
      console.log(data);
    }
  });
});

$("#updateActivityForm").submit(function (e) {
  e.preventDefault();
  console.log("Update Form Submitted");
  var courseCode = $("#u_courseCode").val();
  var aType = $("#u_activityType").val();
  var aDate = $("#u_activityDate").val();
  var name = $("#u_name").val();
  var startTime = $("#u_startTime").val();
  var endTime = $("#u_endTime").val();
  var center = $("#u_center").val();
  var group = $("#u_group").val();
  var activityId = activityList[currentActivity].activityId;

  $.ajax({
    type: "PUT",
    url: "http://localhost:8080/oad/oad-api/activity/" + activityId,
    data: JSON.stringify({
      "activityId": activityId,
      "activityType": aType,
      "name": name,
      "date": aDate,
      "startTime": startTime,
      "endTime": endTime,
      "venue": center,
      "group": group,
      "courseCode": courseCode
    }),
    contentType: 'application/json',
    dataType: 'application/json',
    success: function () {
      var resCode = postData.responseCode;
      console.log(postData);
      if (resCode === 200) {
        alert("Successfully Updated");
        location.reload();
      } else {
        console.log(postData);
      }
    }
  });
});

$("#deleteActivityForm").submit(function (e) {
  e.preventDefault();
  var activityId = activityList[currentActivity].activityId;
  $.ajax({
    method: "DELETE",
    url: "http://localhost:8080/oad/oad-api/activity/" + activityId,
  })
    .done(function (data) {
      alert("Success");
    })
    .fail(function (err) {
      alert("Failed");
      console.log("Failed: "+err);
    });
})

function openConfirmDialog(collisionCount) {
  var courseCode = $("#courseCode").val();
  var aType = $("#activityType").val();
  var aDate = $("#activityDate").val();
  var name = $("#name").val();
  var startTime = $("#startTime").val();
  var endTime = $("#endTime").val();
  var center = $("#center").val();
  var group = $("#group").val();

  $("#dialog-confirm").html("<p>This activity clashes with " + collisionCount + " other activities.<br/> Continue ? </p>");
  $("#dialog-confirm").dialog({
    resizable: false,
    modal: true,
    title: "Confirm Add Activity",
    height: 150,
    width: 400,
    buttons: {
      "Yes": function () {
        saveActivity();
        $(this).dialog('close');
      },
      "No": function () {
        $(this).dialog('close');
      }
    }
  });
}

function saveActivity() {
  var courseCode = $("#courseCode").val();
  var aType = $("#activityType").val();
  var aDate = $("#activityDate").val();
  var name = $("#name").val();
  var startTime = $("#startTime").val();
  var endTime = $("#endTime").val();
  var center = $("#center").val();
  var group = $("#group").val();

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/oad/oad-api/activity/",
    data: JSON.stringify({
      "activityId": 0,
      "activityType": aType,
      "name": name,
      "date": aDate,
      "startTime": startTime,
      "endTime": endTime,
      "venue": center,
      "group": group,
      "courseCode": courseCode
    }),
    contentType: 'application/json',
    dataType: 'application/json',
    success: function (data) {
      var resCode = data.responseCode;
      console.log(data);
      if (resCode === 200) {
        alert("Successfully Updated");
        location.reload();
      } else {
        console.log(data);
      }
    }
  });
}


function updateActivity() {

}

var activityList = null;
var nextActivity = 1;
var currentActivity = 0;
function getActivitiesForCourse() {
  var courseCode = $("#u_courseCode").val();
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/oad/oad-api/course/" + courseCode + "/activity",
  })
    .done(function (data) {
      activityList = data;
      $("#u_activityType").val(activityList[0].activityType);
      $("#u_activityDate").val(activityList[0].date);
      $("#u_name").val(activityList[0].name);
      $("#u_startTime").val(activityList[0].startTime);
      $("#u_endTime").val(activityList[0].endTime);
      $("#u_center").val(activityList[0].venue);
      $("#u_group").val(activityList[0].group);

    })
    .fail(function () {
      console.log("ERROR");
    });
}

function getActivitiesForCourse_d() {
  var courseCode = $("#d_courseCode").val();
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/oad/oad-api/course/" + courseCode + "/activity",
  })
    .done(function (data) {
      activityList = data;
      $("#d_activityType").val(activityList[0].activityType);
      $("#d_activityDate").val(activityList[0].date);
      $("#d_name").val(activityList[0].name);
      $("#d_startTime").val(activityList[0].startTime);
      $("#d_endTime").val(activityList[0].endTime);
      $("#d_center").val(activityList[0].venue);
      $("#d_group").val(activityList[0].group);

    })
    .fail(function () {
      console.log("ERROR");
    });
}

function getNextActivity() {
  if(typeof(activityList) !== "undefined" && activityList !== null){
    if(currentActivity<activityList.length){
      currentActivity = currentActivity + 1;
      $("#u_activityType").val(activityList[currentActivity].activityType);
      $("#u_activityDate").val(activityList[currentActivity].date);
      $("#u_name").val(activityList[currentActivity].name);
      $("#u_startTime").val(activityList[currentActivity].startTime);
      $("#u_endTime").val(activityList[currentActivity].endTime);
      $("#u_center").val(activityList[currentActivity].venue);
      $("#u_group").val(activityList[currentActivity].group);
    }
  }
}

function getNextActivity_d() {
  if(typeof(activityList) !== "undefined" && activityList !== null){
    if(currentActivity<activityList.length){
      currentActivity = currentActivity + 1;
      $("#d_activityType").val(activityList[currentActivity].activityType);
      $("#d_activityDate").val(activityList[currentActivity].date);
      $("#d_name").val(activityList[currentActivity].name);
      $("#d_startTime").val(activityList[currentActivity].startTime);
      $("#d_endTime").val(activityList[currentActivity].endTime);
      $("#d_center").val(activityList[currentActivity].venue);
      $("#d_group").val(activityList[currentActivity].group);
    }
  }
}

function previousActivity() {
  if(typeof(activityList) !== "undefined" && activityList !== null) {
    if (currentActivity > 0) {
      currentActivity = currentActivity - 1;
      $("#u_activityType").val(activityList[currentActivity].activityType);
      $("#u_activityDate").val(activityList[currentActivity].date);
      $("#u_name").val(activityList[currentActivity].name);
      $("#u_startTime").val(activityList[currentActivity].startTime);
      $("#u_endTime").val(activityList[currentActivity].endTime);
      $("#u_center").val(activityList[currentActivity].venue);
      $("#u_group").val(activityList[currentActivity].group);
    }
  }
}

function previousActivity_d() {
  if(typeof(activityList) !== "undefined" && activityList !== null) {
    if (currentActivity > 0) {
      currentActivity = currentActivity - 1;
      $("#d_activityType").val(activityList[currentActivity].activityType);
      $("#d_activityDate").val(activityList[currentActivity].date);
      $("#d_name").val(activityList[currentActivity].name);
      $("#d_startTime").val(activityList[currentActivity].startTime);
      $("#d_endTime").val(activityList[currentActivity].endTime);
      $("#d_center").val(activityList[currentActivity].venue);
      $("#d_group").val(activityList[currentActivity].group);
    }
  }
}


