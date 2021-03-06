package ua.nure.diploma.vote.dto;

import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class ElectionDto {

    private String id;
    private String questionText;
    private String access;
    private String status;
    private String userId;
    private Date dateOfRegister;
    private String city;
    private String country;
    private String topic;
    private List<ChoiceDto> choices;
}
