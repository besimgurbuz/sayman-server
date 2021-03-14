package com.sayman.server.accounting.service;

import com.sayman.server.accounting.dto.IncomeRequest;
import com.sayman.server.accounting.dto.IncomeResponse;
import com.sayman.server.accounting.exceptions.NotFoundAccountItemException;
import com.sayman.server.accounting.exceptions.UnacceptableExpectedDateRangeException;
import com.sayman.server.accounting.mapper.IncomeMapper;
import com.sayman.server.accounting.model.Income;
import com.sayman.server.accounting.repository.IncomeRepository;
import com.sayman.server.auth.model.User;
import com.sayman.server.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sayman.server.accounting.helper.AccountingHelper.accountItemDateRangeValidator;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final IncomeMapper incomeMapper;

    @Transactional
    public List<IncomeResponse> getIncomes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return incomeRepository.findAllByUser(user).stream().map(incomeMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public IncomeResponse getIncomeById(String username, Long incomeId) {
        Income income = incomeRepository.findByUser_UsernameAndId(username, incomeId)
                .orElseThrow(() -> new NotFoundAccountItemException("Requested income could not be found"));
        return incomeMapper.mapToDto(income);
    }

    @Transactional
    public void createNewIncome(IncomeRequest incomeRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        if (accountItemDateRangeValidator(incomeRequest.getExpectedDateStart(), incomeRequest.getExpectedDateEnd())) { // means start is later then end
           throw new UnacceptableExpectedDateRangeException("Income start date cannot be later than end date.");
        }
        incomeRepository.save(incomeMapper.map(incomeRequest, user));
    }

    @Transactional
    public void updateIncomeById(IncomeRequest updateIncomeRequest, String username, Long incomeId) {
        Optional<Income> incomeOptional = incomeRepository.findByUser_UsernameAndId(username, incomeId);

        incomeOptional
                .orElseThrow(() -> new NotFoundAccountItemException("The income item requested to be updated could not be found."));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Income mapped = incomeMapper.map(updateIncomeRequest, user)
                .replaceNullPropertiesWith(incomeOptional.get());

        if (mapped.getExpectedDateRange().getStart() != null
                && mapped.getExpectedDateRange().getEnd() != null
                && accountItemDateRangeValidator(mapped.getExpectedDateRange().getStart(), mapped.getExpectedDateRange().getEnd())) {
            throw new UnacceptableExpectedDateRangeException("Income start date cannot be later than end date.");
        }

        incomeRepository.save(mapped);
    }

    @Transactional
    public void deleteIncomeById(String username, Long incomeId) {
        Optional<Income> incomeOptional = incomeRepository.findByUser_UsernameAndId(username, incomeId);

        Income income = incomeOptional
                .orElseThrow(() -> new NotFoundAccountItemException("The income item requested to be deleted could not be found."));

        incomeRepository.delete(income);
    }
}
