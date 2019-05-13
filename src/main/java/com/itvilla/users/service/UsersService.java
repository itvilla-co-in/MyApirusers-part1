package com.itvilla.users.service;



//public interface UsersService extends UserDetailsService{
public interface UsersService {
	UserDto createUser(UserDto userDetails);
	//UserDto getUserDetailsByEmail(String email);
	//UserDto getUserByUserId(String userId);
}
