package com.dnb.sparkdemo;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Collection;
import java.util.List;

import com.dnb.sparkdemo.dto.User;
import com.dnb.sparkdemo.response.StatusResponse;
import com.dnb.sparkdemo.response.UserResponse;
import com.dnb.sparkdemo.services.UserService;
import com.dnb.sparkdemo.services.UserServiceImpl;
import com.google.gson.Gson;

public class SparkController {

	public static void main(String[] args) {
		
		UserService userService = new UserServiceImpl();
		 
		post("/adduser", (request, response) -> {
			User user = new Gson().fromJson(request.body(), User.class);
			userService.addUser(user);
			return new Gson().toJson(new UserResponse(StatusResponse.SUCCESS));
		});
		
		get("/getuser/:id", (request, response) -> {
			response.type("application/json");
			User userData = userService.getUser(Integer.parseInt(request.params(":id")));
			
			return new Gson().toJson(new UserResponse("User data of " + request.params(":id"), StatusResponse.SUCCESS, new Gson().toJsonTree(userData)));
		});
		
		get("/getusers", (request, response) -> {
			response.type("application/json");
			Collection<User> data = userService.getUsers();
			
			if(data.isEmpty()) {
				return new Gson().toJson(new UserResponse("All user data", StatusResponse.SUCCESS, new Gson().toJsonTree("empty")));
			}
			return new Gson().toJson(new UserResponse("All user data", StatusResponse.SUCCESS, new Gson().toJsonTree(data)));
		});
		
//		User user = new User();
//		user.firstName = "root";
//		user.id = "1";
//		userService.addUser(user);
//		
//		System.out.println(userService.getUser("1")); 
		
	}

}
