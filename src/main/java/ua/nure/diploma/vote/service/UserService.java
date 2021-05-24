package ua.nure.diploma.vote.service;

import ua.nure.diploma.vote.dto.UserDto;

public interface UserService {
    UserDto getUser(String email);
    void createUser(UserDto user);
    UserDto getByEmailAndPassword(String email, String password);
}
