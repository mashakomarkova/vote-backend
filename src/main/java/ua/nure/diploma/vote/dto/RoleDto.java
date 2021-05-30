package ua.nure.diploma.vote.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class RoleDto {

    private String id;
    private String name;
}