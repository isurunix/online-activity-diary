/**
 * Created by isuru on 2/8/17.
 */


var table;

$(document).ready(function(){

    //initialize datatables plugin
    table = $('#activityTable').DataTable({
        "order": [[ 2, "asc" ]]
    });

    $(".datepicker").datepicker({
        onSelect: function () {
            table.draw();
        },
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd'
    });

    $( "#start-date" ).datepicker( "setDate", "2017-01-01" );
    $( "#end-date" ).datepicker( "setDate", "2017-12-31" );

    $('.timepicker').timepicker({
        'timeFormat': 'H:i:s',
        'minTime': '9:00am',
        'maxTime': '6:00pm'
    });
    $('.timepicker').timepicker('option', { useSelect: true });
    $(".ui-timepicker-select").addClass("form-control");

    // $( function() {
    //     $( "#dialog-confirm" ).dialog({
    //         resizable: false,
    //         height: "auto",
    //         width: 400,
    //         modal: true,
    //         buttons: {
    //             "Delete all items": function() {
    //                 $( this ).dialog( "close" );
    //             },
    //             Cancel: function() {
    //                 $( this ).dialog( "close" );
    //             }
    //         }
    //     });
    // } );
});

$.fn.dataTable.ext.search.push(
    function( settings, data, dataIndex ) {
        var startDate = Date.parse($("#start-date").val());
        var endDate = Date.parse($("#end-date").val());
        var date = Date.parse(data[2]);

        if(isNaN(startDate) || isNaN(endDate) || isNaN(date)) {
            console.log("date null");
            return true;
        }
        if(date >= startDate && date <= endDate) {
            console.log("s:"+startDate);
            console.log("e:"+endDate);
            console.log("d:"+date);
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
    if(!$("#updateActivityForm").hasClass("hidden")){
        $("#updateActivityForm").addClass("hidden")
    }

    if($("#addNewActivityForm").hasClass("hidden")) {
        $("#addNewActivityForm").removeClass("hidden");
    }

    if($("#btnAdd").hasClass("active-button")) {
        $("#btnAdd").removeClass("active-button")
    }

    if(!$("#btnUpdate").hasClass("active-button")){
        $("#btnUpdate").addClass("active-button")
    }
}

function showUpdateForm() {
    if($("#updateActivityForm").hasClass("hidden")){
        $("#updateActivityForm").removeClass("hidden")
    }

    if(!$("#addNewActivityForm").hasClass("hidden")) {
        $("#addNewActivityForm").addClass("hidden");
    }

    if(!$("#btnAdd").hasClass("active-button")) {
        $("#btnAdd").addClass("active-button")
    }

    if($("#btnUpdate").hasClass("active-button")){
        $("#btnUpdate").removeClass("active-button")
    }
}

$("#addNewActivityForm").submit(function (e) {

    e.preventDefault();

    var aDate = $("#activityDate").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();

    $.get("http://localhost:8080/oad/oad-api/activity/"+aDate+"/"+startTime+"/"+endTime,function (data, status) {
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

function openConfirmDialog(collisionCount) {
    var courseCode = $("#courseCode").val();
    var aType = $("#activityType").val();
    var aDate = $("#activityDate").val();
    var name = $("#name").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var center = $("#center").val();
    var group = $("#group").val();

    $("#dialog-confirm").html("<p>This activity clashes with "+collisionCount+" other activities.<br/> Continue ? </p>");
    $("#dialog-confirm").dialog({
        resizable: false,
        modal: true,
        title: "Confirm Add Activity",
        height: 150,
        width: 400,
        buttons: {
            "Yes": function () {
                
                // $.ajax({
                //     type: "POST",
                //     url: "http://localhost:8080/oad/oad-api/activity/",
                //     data: JSON.stringify({
                //         "activityId": 0,
                //         "activityType": aType,
                //         "name": name,
                //         "date": aDate,
                //         "startTime": startTime,
                //         "endTime": endTime,
                //         "venue": center,
                //         "group": group,
                //         "courseCode": courseCode
                //     }),
                //     contentType: 'application/json',
                //     dataType: 'application/json'
                // }).done(function (data) {
                //     var resCode = data.responseCode;
                //     if (resCode == 200) {
                //         alert("Successfully Updated");
                //         location.reload();
                //     } else {
                //         console.log(data);
                //     }
                // });
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
        data: JSON.stringify({"activityId": 0, "activityType": aType, "name": name, "date": aDate, "startTime": startTime, "endTime": endTime, "venue": center, "group": group, "courseCode": courseCode}),
        contentType: 'application/json',
        dataType: 'application/json',
        success: function (postData) {
            var resCode = postData.responseCode;
            console.log(postData);
            if (resCode == 200) {
                alert("Successfully Updated");
                location.reload();
            } else {
                console.log(postData);
            }
        }
    });
    location.reload();
}


