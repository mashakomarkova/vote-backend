package ua.nure.diploma.vote.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {

    private String id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String status;
    private String userPicture;
    private Role role;
    private List<String> topics;
}
