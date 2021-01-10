package com.sayman.server.accounting.service;

import com.sayman.server.accounting.dto.IncomeDto;
import com.sayman.server.accounting.model.Income;
import com.sayman.server.accounting.repository.IncomeRepository;
import com.sayman.server.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Transactional(readOnly = true)
    public List<IncomeDto> getIncomes(String username) {

        return incomeRepository.getAllByUser_Username(username).stream().map(IncomeDto::fromIncome).collect(Collectors.toList());
    }

    @Transactional
    public IncomeDto createNewIncome(Income income) {
        return IncomeDto.fromIncome(incomeRepository.save(income));
    }
}
