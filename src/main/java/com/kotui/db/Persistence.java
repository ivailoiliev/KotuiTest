package com.kotui.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Persistence {

	private static Persistence perInstance;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_SCHEMA = "kotui";
	private static final String DB_URL_SCHEMA = DB_URL + DB_SCHEMA;

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "test";

	private Connection connection;
	
	public static Persistence get(){
		   if(perInstance == null || perInstance.connectionCheck()){
			   try {
				perInstance = new Persistence();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		  return  perInstance;
	   }

	private Persistence() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection =  DriverManager.getConnection(DB_URL_SCHEMA,USER,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	private boolean connectionCheck() {
		try {
			return this.connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("finally")
	public List<String> getAllImages() {
		List<String> imageList = new ArrayList<String>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SQL_Statements.getAllRndImages);

			while (rs.next()) {
				JSONObject jo = new JSONObject();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String image = rs.getString("img");
				jo.put("id", id);
				jo.put("name", name);
				jo.put("image", image);

				imageList.add(jo.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return imageList;
		}
	}
	
	@SuppressWarnings("finally")
	public JSONObject addPlayer(String name, String status) {
		String query = SQL_Statements.prepareUSerQUery(name, status);
		JSONObject jo = new JSONObject();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
				int id = rs.getInt("id");
				String uName = rs.getString("name");
				String uStatus = rs.getString("status");
				jo.put("id", id);
				jo.put("name", uName);
				jo.put("status", uStatus);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return jo;
		}
	}

	@SuppressWarnings("finally")
	public JSONObject selectPlayer(int id) {
		String query = SQL_Statements.preparePlayerSelectById(id);
		JSONObject jo = new JSONObject();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
				int uId = rs.getInt("id");
				String uName = rs.getString("name");
				String uStatus = rs.getString("status");
				jo.put("id", uId);
				jo.put("name", uName);
				jo.put("status", uStatus);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return jo;
		}
	}
	
	
}
