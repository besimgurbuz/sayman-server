package com.sayman.server.accounting.repository;

import com.sayman.server.accounting.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
