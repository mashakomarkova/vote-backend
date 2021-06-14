package ua.nure.diploma.vote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.diploma.vote.dto.UserDto;
import ua.nure.diploma.vote.entity.User;
import ua.nure.diploma.vote.mapper.UserMapper;
import ua.nure.diploma.vote.repository.UserRepository;
import ua.nure.diploma.vote.service.UserService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String email) {
        return userMapper.mapToUserDto(userRepository.findByEmail(email));
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    @Override
    public UserDto getByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public void updateUser(UserDto userDto) {
        userRepository.save(userMapper.mapToUser(userDto));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }
}
