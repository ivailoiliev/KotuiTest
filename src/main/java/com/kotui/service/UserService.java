package com.kotui.service;

import org.json.JSONObject;

import com.kotui.bean.PlayerBean;
import com.kotui.db.Persistence;

public class UserService {

	
	
	public JSONObject addUserToDB(int userId, String userName) {

		JSONObject user = null;
		if (userId != 0) {
			user = Persistence.get().selectPlayer(userId);
		} else if (user == null) {
			user = Persistence.get().addPlayer(userName, "waiting");
		}
		return user;
	}

	public void heartBeat(){
		
	}
	
}
