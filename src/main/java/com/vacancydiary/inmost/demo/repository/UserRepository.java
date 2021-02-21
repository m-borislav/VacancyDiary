package com.vacancydiary.inmost.demo.repository;

import com.vacancydiary.inmost.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstName(String firstname);
    User findByEmail(String email);
}
