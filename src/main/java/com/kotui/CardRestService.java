package com.kotui;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.kotui.service.CardService;

@Path("images")
public class CardRestService {
	
		private CardService cards= new CardService();
		    /**
	     * Method handling HTTP GET requests. The returned object will be sent
	     * to the client as "text/plain" media type.
	     *
	     * @return String that will be returned as a text/plain response.
	     */
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public String getIt() {
	    	JSONObject jo =cards.getRequiredImages();
	        return jo.toString();
	    }

	    @POST
	    @Produces(MediaType.APPLICATION_JSON)
	    public String selectedImages() {
	    	JSONObject jo =cards.getRequiredImages();
	        return jo.toString();
	    }
	    
}
