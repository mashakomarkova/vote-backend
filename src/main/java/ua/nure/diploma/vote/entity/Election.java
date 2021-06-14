package ua.nure.diploma.vote.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Election {

    private String id;
    private String questionText;
    private String access;
    private String status;
    private String topic;
    private String userId;
    private Date dateOfRegister;
    private String city;
    private String country;
    private List<Choice> choices;
}
