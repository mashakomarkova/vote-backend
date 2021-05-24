package ua.nure.diploma.vote.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String userPicture;
    private RoleDto role;
}