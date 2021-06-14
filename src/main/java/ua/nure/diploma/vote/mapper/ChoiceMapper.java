package ua.nure.diploma.vote.mapper;

import org.mapstruct.Mapper;
import ua.nure.diploma.vote.dto.ChoiceDto;
import ua.nure.diploma.vote.entity.Choice;

@Mapper(componentModel = "spring")
public interface ChoiceMapper {
    ChoiceDto mapToChoiceDto(Choice election);
}
