package ua.nure.diploma.vote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.diploma.vote.dto.ElectionDto;
import ua.nure.diploma.vote.mapper.ElectionMapper;
import ua.nure.diploma.vote.repository.ElectionRepository;
import ua.nure.diploma.vote.service.ElectionService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    @Override
    public List<ElectionDto> findAllElections() {
        return electionMapper.mapToElectionDtoList(electionRepository.findAll());
    }

    @Override
    public ElectionDto saveElection(ElectionDto electionDto) {
        electionDto.setId(UUID.randomUUID().toString());
        electionDto.setStatus("ACTIVE");
        electionDto.setDateOfRegister(new Date());

        electionRepository.save(electionMapper.mapToElection(electionDto));
        return electionDto;
    }

    @Override
    public void deleteElection(String electionId) {
        electionRepository.deleteById(electionId);
    }
}
