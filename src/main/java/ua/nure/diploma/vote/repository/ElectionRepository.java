package ua.nure.diploma.vote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.nure.diploma.vote.entity.Election;

import java.util.List;

public interface ElectionRepository extends MongoRepository<Election, String> {
    List<Election> findAllByUserId(String userId);
    Election findByIdAndUserId(String id, String userId);
    List<Election> findAllByTopic(String topic);
}
