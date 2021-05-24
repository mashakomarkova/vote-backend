package ua.nure.diploma.vote.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private String id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String userPicture;
    private Role role;
}
