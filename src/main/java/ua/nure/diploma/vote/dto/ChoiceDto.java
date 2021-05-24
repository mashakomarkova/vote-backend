package ua.nure.diploma.vote.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChoiceDto {
    private String id;
    private String name;
    private List<String> userRates;
}
