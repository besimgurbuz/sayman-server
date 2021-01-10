package com.sayman.server.accounting.repository;

import com.sayman.server.accounting.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    public List<Income> getAllByUser_Username(String username);
}
