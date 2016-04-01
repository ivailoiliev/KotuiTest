package com.kotui.db;



	import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kotui.bean.ImageBean;

import java.sql.ResultSet;


	// Notice, do not import com.mysql.jdbc.*
	// or you will have problems!

	public class PersistenceTest {
	    public static void main(String[] args) {
	 
	    			System.out.println("-------- MySQL JDBC Connection Testing ------------");
	    			try {
	    				Class.forName("com.mysql.jdbc.Driver");
	    			} catch (ClassNotFoundException e) {
	    				System.out.println("Where is your MySQL JDBC Driver?");
	    				e.printStackTrace();
	    				return;
	    			}
	    			System.out.println("MySQL JDBC Driver Registered!");
	    			Connection connection = null;
	    			try {
	    				connection = DriverManager
	    				.getConnection("jdbc:mysql://localhost:3306/kotui","root", "test");
	    				//jdbc:mysql://localhost:3306/kotui
	    				List<ImageBean> imageList = new ArrayList<ImageBean>();
	    				try {
	    					Statement statement = connection.createStatement();
	    					ResultSet rs = statement.executeQuery(SQL_Statements.getAllImages);
	    					
	    					while (rs.next()) {
	    						int id = rs.getInt("id");
	    						String name = rs.getString("name");
	    						String image = rs.getString("name"); 
	    						ImageBean imgBean = new ImageBean(id,name,image);
	    						imageList.add(imgBean);
	    					}
	    				} catch (SQLException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				System.out.println("End");
	    				
	    			} catch (SQLException e) {
	    				System.out.println("Connection Failed! Check output console");
	    				e.printStackTrace();
	    				return;
	    			}
	    			if (connection != null) {
	    				System.out.println("You made it, take control your database now!");
	    			} else {
	    				System.out.println("Failed to make connection!");
	    			}
	    		  }
	    }
	
	

