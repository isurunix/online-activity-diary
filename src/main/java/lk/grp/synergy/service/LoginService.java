package lk.grp.synergy.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.control.impl.StudentControllerImpl;
import lk.grp.synergy.model.Student;
import lk.grp.synergy.service.security.Hasher;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.CryptoPrimitive;
import java.sql.SQLException;

/**
 * Created by isuru on 1/13/17.
 */
@Path("/login")
public class LoginService {

    private JsonParser jsonParser = new JsonParser();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private StudentControllerInterface studentController = new StudentControllerImpl();
    private BASE64Encoder base64Encoder = new BASE64Encoder();

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(String payload){
        JsonObject jsonPayload = jsonParser.parse(payload).getAsJsonObject();
        String userId = jsonPayload.get("userId").getAsString();
        String pwd = jsonPayload.get("password").getAsString();
        String hash = Hasher.hash(pwd);

        Student student = null;
        try {
            student = studentController.getStudent(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("userId",userId);
        if(student!= null && hash.equalsIgnoreCase(student.getPasswordHash())){
            jsonResponse.addProperty("authKey",base64Encoder.encode((userId+":"+hash).getBytes()));
        }else{
            jsonResponse.addProperty("errorCode","403");
            jsonResponse.addProperty("error","Not Authorized");
        }

        return Response.ok(gson.toJson(jsonResponse), MediaType.APPLICATION_JSON).build();
    }
}
