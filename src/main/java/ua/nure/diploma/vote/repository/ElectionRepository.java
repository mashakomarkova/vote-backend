package ua.nure.diploma.vote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.nure.diploma.vote.entity.Election;

public interface ElectionRepository extends MongoRepository<Election, String> {
}
