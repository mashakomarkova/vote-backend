package ua.nure.diploma.vote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.nure.diploma.vote.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}