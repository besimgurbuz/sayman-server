package com.sayman.server.accounting.service;

import com.sayman.server.accounting.dto.IncomeRequest;
import com.sayman.server.accounting.dto.IncomeResponse;
import com.sayman.server.accounting.exceptions.UnacceptableExpectedDateRange;
import com.sayman.server.accounting.mapper.IncomeMapper;
import com.sayman.server.accounting.repository.IncomeRepository;
import com.sayman.server.auth.model.User;
import com.sayman.server.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final IncomeMapper incomeMapper;

    @Transactional(readOnly = true)
    public List<IncomeResponse> getIncomes(String username) {
        return incomeRepository.getAllByUser_Username(username).stream().map(incomeMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public void createNewIncome(IncomeRequest incomeRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        int dateCompareResult = incomeRequest.getExpectedDateStart().compareTo(incomeRequest.getExpectedDateEnd());

        if (dateCompareResult > 0) { // means start is later then end
           throw new UnacceptableExpectedDateRange("Income start date cannot be later than end date.");
        }
        incomeRepository.save(incomeMapper.map(incomeRequest, user));
    }
}
