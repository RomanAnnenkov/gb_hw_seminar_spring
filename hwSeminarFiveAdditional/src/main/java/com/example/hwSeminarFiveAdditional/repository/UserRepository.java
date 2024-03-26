package com.example.hwSeminarFiveAdditional.repository;

import com.example.hwSeminarFiveAdditional.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
