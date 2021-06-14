package ua.nure.diploma.vote.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.nure.diploma.vote.dto.ElectionDto;
import ua.nure.diploma.vote.entity.Choice;
import ua.nure.diploma.vote.entity.Election;
import ua.nure.diploma.vote.mapper.ElectionMapper;
import ua.nure.diploma.vote.repository.ElectionRepository;
import ua.nure.diploma.vote.service.ElectionService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    @Override
    public List<ElectionDto> findAllElections() {
        return electionMapper.mapToElectionDtoList(electionRepository.findAll())
                .stream()
                .filter(electionDto -> StringUtils.equalsIgnoreCase("ACTIVE",electionDto.getStatus()))
                .filter(electionDto -> StringUtils.equalsIgnoreCase("public", electionDto.getAccess()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ElectionDto> findOwnElections(String userId) {
        return electionMapper.mapToElectionDtoList(electionRepository.findAllByUserId(userId));
    }

    @Override
    public ElectionDto saveElection(ElectionDto electionDto) {
        electionDto.setId(UUID.randomUUID().toString());
        electionDto.setStatus("ACTIVE");
        electionDto.setDateOfRegister(new Date());
        Election election = electionMapper.mapToElection(electionDto);
        List<Choice> choices = election.getChoices();
        choices.forEach(choice -> {
            choice.setId(UUID.randomUUID().toString());
            choice.setUserRates(new ArrayList<>());
        });
        election.setChoices(choices);
        electionRepository.save(election);
        return electionDto;
    }

    @Override
    public void updateElection(ElectionDto electionDto) {
        Election election = electionMapper.mapToElection(electionDto);
        electionRepository.save(election);
    }

    @Override
    public void deleteElection(String electionId) {
        electionRepository.deleteById(electionId);
    }

    @Override
    public ElectionDto findElection(String electionId) {
        return electionMapper.mapToElectionDto(electionRepository.findById(electionId)
                .orElseGet(Election::new));
    }

    @Override
    public ElectionDto findOwnElection(String electionId, String userId) {
        Election election = electionRepository.findByIdAndUserId(electionId, userId);
        return electionMapper.mapToElectionDto(election);
    }

    @Override
    public List<ElectionDto> findByTopics(List<String> topics) {
        List<ElectionDto> summary = new ArrayList<>();
        Map<String, Integer> frequency = new HashMap<>();
        topics.forEach(topic -> frequency.put(topic, Collections.frequency(topics, topic)));
        frequency.forEach((topic, freq) -> {
            List<ElectionDto> electionDtos = electionMapper.mapToElectionDtoList(
                    electionRepository.findAllByTopic(topic));
            summary.addAll(electionDtos);
        });
        if (summary.isEmpty()) {
            summary.addAll(electionMapper.mapToElectionDtoList(electionRepository.findAll()));
        }
        int index = Math.min(summary.size(), 5);
        return summary.subList(0, index);
    }
}
