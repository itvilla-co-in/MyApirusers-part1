package com.itvilla.users.ui.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvilla.users.service.UserDto;
import com.itvilla.users.service.UsersService;
import com.itvilla.users.ui.entity.UserInput;
import com.itvilla.users.ui.entity.UserOutput;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/status")
	public String status()
	{
		return "Working  " + "Testing load balancer" + env.getProperty("local.server.port");
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	//public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody UserInput userDetails)
	public ResponseEntity<UserOutput> createUser(@RequestBody UserInput userDetails)
	{
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		System.out.println("its coming here " + userDetails.getEmail());
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = usersService.createUser(userDto);
		
		UserOutput returnValue = modelMapper.map(createdUser, UserOutput.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		 
	}
	
	
	
}
