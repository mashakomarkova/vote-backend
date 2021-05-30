package ua.nure.diploma.vote.dto;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ChoiceDto {
    private String id;
    private String name;
    private List<String> userRates;
}
