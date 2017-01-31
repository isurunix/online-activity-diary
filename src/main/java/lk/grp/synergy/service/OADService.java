package lk.grp.synergy.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lk.grp.synergy.control.ActivityControllerInterface;
import lk.grp.synergy.control.CourseControllerInterface;
import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.control.impl.ActivityController;
import lk.grp.synergy.control.impl.CourseController;
import lk.grp.synergy.control.impl.StudentControllerImpl;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/15/17.
 */
@Path("/")
public class OADService {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private StudentControllerInterface studentController = new StudentControllerImpl();
    private CourseControllerInterface courseController = new CourseController();
    private ActivityControllerInterface activityController = new ActivityController();

    @GET
    @Path("student/{id}")
    @Produces("application/json")
    public Response getStudentDetails(@PathParam("id") String userId){

        Student student = null;
        try {
            student = studentController.getStudent(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if(student != null){
            return Response.ok(gson.toJson(student)).build();
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("userId",userId);
        jsonResponse.addProperty("errorCode","404");
        jsonResponse.addProperty("error","Resource Not Found");

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("student/{id}/course")
    @Produces("application/json")
    public Response getCourseList(@PathParam("id") String studentId) throws NamingException {
        List<Course> courseList = null;
        try {
            courseList = studentController.getCourseList(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }

        if(courseList != null){
            String json = gson.toJson(courseList);
            return Response.status(200).entity(json).build();
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("errorCode","404");
        jsonResponse.addProperty("error","Resource Not Found");

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("course/{code}")
    @Produces("application/json")
    public Response getCourse(@PathParam("code") String courseCode){
        Course course = null;
        try {
            course = courseController.getCourse(courseCode);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().build();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if(course!=null){
            return Response.status(200).entity(gson.toJson(course)).build();
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("errorCode","404");
        jsonResponse.addProperty("error","Resource Not Found");

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("course/{courseCode}/activity")
    @Produces("application/json")
    public Response getAllActivitiesForCourse(@PathParam("courseCode") String courseCode){
        List<Activity> activities = null;
        try {
            Course course = courseController.getCourse(courseCode);
            activities = activityController.getAllActivities(course);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if(activities != null && activities.size()!=0){
            return Response.status(200).entity(gson.toJson(activities)).build();
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("errorCode","404");
        jsonResponse.addProperty("error","Resource Not Found");

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }
}
