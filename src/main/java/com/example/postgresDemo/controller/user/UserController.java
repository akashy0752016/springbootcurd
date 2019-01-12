package com.example.postgresDemo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresDemo.model.User;
import com.example.postgresDemo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RequestMapping(value="/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getAllUsers() {
		return  new ResponseEntity<String>(new Gson().toJson(userService.loadAllUser()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getUserById(@PathVariable Long id) {
		return new ResponseEntity<String>(new Gson().toJson(userService.findUserById(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveUser(@RequestBody User user) {
		return new ResponseEntity<String>(new Gson().toJson(userService.insert(user)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateUser(@RequestBody User user) {
		return new ResponseEntity<String>(new Gson().toJson(userService.update(user)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable Long id) {
		JsonObject jObject = new JsonObject();
		jObject.addProperty("status", "User deleted success fully.");
		userService.delete(id);
		return new ResponseEntity<String>(jObject.toString(), HttpStatus.OK);
	}
}
