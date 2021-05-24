package ua.nure.diploma.vote.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Choice {

    private String id;
    private String name;
    private List<String> userRates;
}