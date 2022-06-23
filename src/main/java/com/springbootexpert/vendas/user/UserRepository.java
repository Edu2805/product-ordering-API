package com.springbootexpert.vendas.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserData, UUID> {

    Optional<UserData> findByLogin(String login);
}
