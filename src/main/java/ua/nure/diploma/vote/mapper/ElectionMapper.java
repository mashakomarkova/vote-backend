package ua.nure.diploma.vote.mapper;

import org.mapstruct.Mapper;
import ua.nure.diploma.vote.dto.ElectionDto;
import ua.nure.diploma.vote.dto.ElectionWithUserDto;
import ua.nure.diploma.vote.entity.Election;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElectionMapper {

    List<ElectionDto> mapToElectionDtoList(List<Election> elections);
    Election mapToElection(ElectionDto electionDto);
    ElectionDto mapToElectionDto(Election election);
    ElectionWithUserDto mapToElectionWithUserDto(ElectionDto election);

}
