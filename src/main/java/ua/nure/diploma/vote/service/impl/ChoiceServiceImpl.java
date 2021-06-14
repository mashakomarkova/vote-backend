package ua.nure.diploma.vote.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.diploma.vote.dto.ChoiceDto;
import ua.nure.diploma.vote.entity.Choice;
import ua.nure.diploma.vote.mapper.ChoiceMapper;
import ua.nure.diploma.vote.repository.ChoiceRepository;
import ua.nure.diploma.vote.service.ChoiceService;

@RequiredArgsConstructor
@Service
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final ChoiceMapper choiceMapper;

    @Override
    public ChoiceDto findChoiceById(String id) {
        return choiceMapper.mapToChoiceDto(choiceRepository.findById(id).orElseGet(Choice::new));
    }
}
