package lk.grp.synergy.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.RandomStringUtils;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by isuru on 1/11/17.
 */
@Path("/test")
public class TestService {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json")
    public Response testService(){
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("responseId", RandomStringUtils.randomAlphanumeric(12));
        responseJson.addProperty("message","Test JRebel");

        return Response.ok(gson.toJson(responseJson), MediaType.APPLICATION_JSON_TYPE).build();
    }
}
