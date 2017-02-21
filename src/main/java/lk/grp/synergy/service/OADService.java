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
import lk.grp.synergy.model.Student;
import lk.grp.synergy.model.StudentCourse;
import lk.grp.synergy.service.security.Hasher;
import lk.grp.synergy.util.DateDeserializer;
import lk.grp.synergy.util.DateSerializer;
import lk.grp.synergy.util.TimeDeserializer;
import lk.grp.synergy.util.TimeSerializer;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by isuru on 1/15/17.
 */
@Path("/")
public class OADService {

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

//    @GET
//    @Path("activity/{date}/{from}/{to}")
//    @Produces("application/json")
//    public Response getCollisions(@PathParam("date") String date, @PathParam("from") String from, @PathParam("to") String to){
//        JsonObject jsonResponse = new JsonObject();
//
//        LocalDate activityDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(date));
//        LocalTime startTime = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm:ss").parse(from));
//        LocalTime endTime = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm:ss").parse(to));
//
//        Activity activity = new Activity();
//        activity.setDate(activityDate);
//        activity.setStartTime(startTime);
//        activity.setEndTime(endTime);
//
//        List<Integer> collisions = null;
//        try {
//            collisions = activityController.getCollisions(activity);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//        jsonResponse.addProperty("collisionCount",collisions.size());
//        jsonResponse.add("collisions",gson.toJsonTree(collisions));
//
//        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
//    }
//
//    @POST
//    @Path("activity/")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response addActivity(String payload){
//        JsonObject jsonResponse = new JsonObject();
//
//        Activity activity = gson.fromJson(payload, Activity.class);
//        try {
//            boolean b = activityController.addActivity(activity);
//            if(b){
//                jsonResponse.addProperty("responseCode","200");
//                jsonResponse.addProperty("description","Success");
//            }else{
//                jsonResponse.addProperty("responseCode","304");
//                jsonResponse.addProperty("description","Failed");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            jsonResponse.addProperty("responseCode","304");
//            jsonResponse.addProperty("description","Failed");
//        } catch (NamingException e) {
//            e.printStackTrace();
//            jsonResponse.addProperty("responseCode","304");
//            jsonResponse.addProperty("description","Failed");
//        }
//        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
//    }


}
