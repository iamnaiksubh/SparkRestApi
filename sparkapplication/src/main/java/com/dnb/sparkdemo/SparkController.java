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
			
			if(userData == null) {
				return new Gson().toJson(new UserResponse("User data of " + request.params(":id"), StatusResponse.SUCCESS, new Gson().toJsonTree("empty")));
			}
			
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
		
		post("/updateuser", (request, response) -> {
			User user = new Gson().fromJson(request.body(), User.class);
			Boolean isUpdated = userService.updateUser(user);
			
			if (isUpdated) {
				return new Gson().toJson(new UserResponse(StatusResponse.SUCCESS));
			}
			return new Gson().toJson(new UserResponse(StatusResponse.ERROR));
		});
		
		post("/deleteuser/:id", (request, response) -> {
		    response.type("application/json");
		    
		    Boolean isDeleted = userService.deleteUser(Integer.parseInt(request.params(":id")));
		    
		    if (isDeleted) {
				return new Gson().toJson(new UserResponse("User Deleted Successfully", StatusResponse.SUCCESS));
			}
			return new Gson().toJson(new UserResponse("User Id Doesn't Exist", StatusResponse.ERROR));
		});
		
	}

}
