package ua.nure.diploma.vote.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ua.nure.diploma.vote.config.jwt.JwtTokenProvider;
import ua.nure.diploma.vote.dto.AuthenticationRequestDto;
import ua.nure.diploma.vote.dto.ElectionDto;
import ua.nure.diploma.vote.dto.RoleDto;
import ua.nure.diploma.vote.dto.UserDto;
import ua.nure.diploma.vote.service.UserService;
import ua.nure.diploma.vote.util.Util;

import java.util.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @GetMapping ("/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        userDto.setRole(RoleDto.builder().id("1").name("ROLE_CLIENT").build());
        userDto.setPassword(new Util().encryptString(userDto.getPassword()));
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
        userDto.setPassword(new Util().encryptString(userDto.getPassword()));
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin("*")
    @PostMapping("/logIn")
    public ResponseEntity<Map<Object, Object>> signIn(@RequestBody String data) {
        AuthenticationRequestDto requestDto = new Gson().fromJson(data, AuthenticationRequestDto.class);
        String username = requestDto.getUsername();
       // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        UserDto user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found username");
        }
        String token = jwtTokenProvider.createToken(username, Collections.singletonList(user.getRole()));
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<UserDto> viewAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/changeStatus/{email}")
    public ResponseEntity<String> changeStatus(@PathVariable String email) {
        UserDto userDto = userService.getUser(email);
        if ("ACTIVE".equals(userDto.getStatus())) {
            userDto.setStatus("BLOCKED");
        } else {
            userDto.setStatus("ACTIVE");
        }
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/grantRole/{email}")
    public ResponseEntity<String> grantRole(@PathVariable String email) {
        UserDto userDto = userService.getUser(email);
        if ("ROLE_CLIENT".equals(userDto.getRole().getName())) {
            userDto.setRole(RoleDto.builder().id("1").name("ROLE_ADMIN").build());
        } else {
            userDto.setRole(RoleDto.builder().id("2").name("ROLE_CLIENT").build());
        }

        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }


}