package com.sayman.server.accounting.controller;

import com.sayman.server.accounting.dto.IncomeDto;
import com.sayman.server.accounting.model.Income;
import com.sayman.server.accounting.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountingController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping(value = "/incomes")
    @PreAuthorize("hasAuthority('calender:read')")
    public ResponseEntity<List<IncomeDto>> getIncomes(@AuthenticationPrincipal String activeUsername) {
        List<IncomeDto> incomes = incomeService.getIncomes(activeUsername);

        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }

    @PostMapping(value = "/incomes")
    @PreAuthorize("hasAuthority('calender:write')")
    public ResponseEntity<IncomeDto> createNewIncome(@AuthenticationPrincipal String activeUsername,
                                                  @RequestBody Income income) {
        IncomeDto newIncome = incomeService.createNewIncome(income);
        return new ResponseEntity<>(newIncome, HttpStatus.CREATED);
    }
}
