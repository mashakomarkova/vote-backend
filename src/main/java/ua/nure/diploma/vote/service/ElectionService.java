package ua.nure.diploma.vote.service;

import ua.nure.diploma.vote.dto.ElectionDto;

import java.util.List;

public interface ElectionService {

    List<ElectionDto> findAllElections();
    ElectionDto saveElection(ElectionDto electionDto);
    void deleteElection(String electionId);
}
