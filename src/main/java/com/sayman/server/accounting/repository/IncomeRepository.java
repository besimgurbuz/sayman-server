package com.sayman.server.accounting.repository;

import com.sayman.server.accounting.model.Income;
import com.sayman.server.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findAllByUser(User user);
    Optional<Income> findByUser_UsernameAndId(String username, Long id);
}
