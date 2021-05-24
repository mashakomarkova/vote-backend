package ua.nure.diploma.vote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.nure.diploma.vote.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}