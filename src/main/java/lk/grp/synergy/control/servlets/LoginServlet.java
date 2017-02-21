package lk.grp.synergy.control.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lk.grp.synergy.control.AdminControllerInterface;
import lk.grp.synergy.control.impl.AdminControllerImpl;
import lk.grp.synergy.model.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by isuru on 2/6/17.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/admin/login"})
public class LoginServlet extends HttpServlet {

    Client client = ClientBuilder.newClient();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jsonParser = new JsonParser();
    Logger logger = LogManager.getLogger(LoginServlet.class);
    private AdminControllerInterface adminController = new AdminControllerImpl();


    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Received POST request");
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");

        String localAddr = req.getLocalName();
        int localPort = req.getLocalPort();
        if(localPort!=80){
            localAddr+=(":"+localPort);
        }

        String url = "http://"+localAddr+"/oad/admin/";

        JsonObject payload = new JsonObject();
        payload.addProperty("userId",username);
        payload.addProperty("isLogged",false);

        try {
            Admin admin = null;
            if(username != null && pwd!=null){
                boolean isLogged = false;

                isLogged = adminController.isValidLogin(new Admin(0,username,pwd));


                if(isLogged){
                    admin = adminController.getAdmin(username);
                    req.getSession().setAttribute("adminId",admin.getId());
                    req.getSession().setAttribute("isLogged",isLogged);
                    resp.sendRedirect(url+"home");
                }else{
                    resp.sendRedirect(url+"signin");
                }
            }else{
                resp.sendRedirect(url+"signin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        String localAddr = req.getLocalName();
        int localPort = req.getLocalPort();
        if(localPort!=80){
            localAddr+=(":"+localPort);
        }
        String url = "http://"+localAddr+"/oad/admin/";
        resp.sendRedirect(url+"signin");
    }
}
