package ua.nure.diploma.vote.service;

import ua.nure.diploma.vote.dto.ChoiceDto;

public interface ChoiceService {
    ChoiceDto findChoiceById(String id);
}
