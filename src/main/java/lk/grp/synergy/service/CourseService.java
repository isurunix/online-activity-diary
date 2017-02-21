package lk.grp.synergy.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lk.grp.synergy.control.ActivityControllerInterface;
import lk.grp.synergy.control.CourseControllerInterface;
import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.control.StudentCourseControllerInterface;
import lk.grp.synergy.control.impl.ActivityController;
import lk.grp.synergy.control.impl.CourseController;
import lk.grp.synergy.control.impl.StudentControllerImpl;
import lk.grp.synergy.control.impl.StudentCourseController;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;
import lk.grp.synergy.util.DateDeserializer;
import lk.grp.synergy.util.DateSerializer;
import lk.grp.synergy.util.TimeDeserializer;
import lk.grp.synergy.util.TimeSerializer;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by isuru on 2/21/17.
 */
@Path("/course")
public class CourseService {
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new DateSerializer())
            .registerTypeAdapter(LocalDate.class, new DateDeserializer())
            .registerTypeAdapter(LocalTime.class, new TimeSerializer())
            .registerTypeAdapter(LocalTime.class, new TimeDeserializer())
            .create();

    private JsonParser jsonParser = new JsonParser();

    private StudentControllerInterface studentController = new StudentControllerImpl();
    private CourseControllerInterface courseController = new CourseController();
    private ActivityControllerInterface activityController = new ActivityController();
    private StudentCourseControllerInterface studentCourseController = new StudentCourseController();

    @GET
    @Path("/{code}")
    @Produces("application/json")
    public Response getCourse(@PathParam("code") String courseCode){
        Course course = null;
        try {
            course = courseController.getCourse(courseCode.toUpperCase());
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
        jsonResponse.addProperty("responseCode","404");
        jsonResponse.addProperty("description","Resource Not Found");

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{courseCode}/activity")
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
