package ua.nure.diploma.vote.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String userPicture;
    private RoleDto role;
}