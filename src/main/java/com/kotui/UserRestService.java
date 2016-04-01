package com.kotui;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.kotui.bean.PlayerBean;
import com.kotui.service.CardService;
import com.kotui.service.UserService;

@Path("login")
public class UserRestService {

	private UserService players= new UserService();
    /**
 * Method handling HTTP GET requests. The returned object will be sent
 * to the client as "text/plain" media type.
 *
 * @return String that will be returned as a text/plain response.
 */
@GET
@Produces(MediaType.APPLICATION_JSON)
public String heartBeat(@FormParam("userId") String userId, @FormParam("userName") String userName) {
	JSONObject jo =null;
    return jo.toString();
}

@POST
@Produces(MediaType.APPLICATION_JSON)
public String addUser(@FormParam("userId") int userId, @FormParam("userName") String userName) {
	JSONObject jo =players.addUserToDB(userId,userName);
    return jo.toString();
}
	
}
