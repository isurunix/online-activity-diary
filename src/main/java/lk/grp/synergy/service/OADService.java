package lk.grp.synergy.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.control.impl.StudentControllerImpl;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by isuru on 1/15/17.
 */
@Path("/")
public class OADService {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private StudentControllerInterface studentController = new StudentControllerImpl();

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
            return Response.ok(student).build();
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("userId",userId);
        jsonResponse.addProperty("errorCode","404");
        jsonResponse.addProperty("error","Resource Not Authorized");

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }
}
