package com.sayman.server.accounting.repository;

import com.sayman.server.accounting.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
