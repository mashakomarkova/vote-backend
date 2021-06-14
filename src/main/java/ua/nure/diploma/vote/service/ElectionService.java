package ua.nure.diploma.vote.service;

import ua.nure.diploma.vote.dto.ElectionDto;

import java.util.List;

public interface ElectionService {

    List<ElectionDto> findAllElections();
    List<ElectionDto> findOwnElections(String userId);
    ElectionDto saveElection(ElectionDto electionDto);
    void updateElection(ElectionDto electionDto);
    void deleteElection(String electionId);
    ElectionDto findElection(String electionId);
    ElectionDto findOwnElection(String electionId, String userId);
    List<ElectionDto> findByTopics(List<String> topics);
}
