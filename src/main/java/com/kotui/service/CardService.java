package com.kotui.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kotui.bean.ImageBean;
import com.kotui.db.Persistence;

public class CardService {
	
	
	 public JSONObject getRequiredImages() {
		 List<String>  img =  Persistence.get().getAllImages();
		 JSONObject jo = new JSONObject();
		 JSONArray jArr = new JSONArray(img.toArray());
		 jo.put("data", jArr);
		 return jo;
	 }

	 public JSONObject postSelectedImages() {
		 List<String>  img =  Persistence.get().getAllImages();
		 JSONObject jo = new JSONObject();
		 JSONArray jArr = new JSONArray(img.toArray());
		 jo.put("data", jArr);
		 return jo;
	 }

	 

}
