package ua.nure.diploma.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ElectionWithUserDto {

    private String id;
    private String questionText;
    private String access;
    private String status;
    private String userId;
    private Date dateOfRegister;
    private String city;
    private String topic;
    private String country;
    private List<ChoiceDto> choices;
    private boolean hasUserParticipated;
}
