package ua.nure.diploma.vote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.nure.diploma.vote.entity.Choice;

public interface ChoiceRepository extends MongoRepository<Choice, String> {
}
