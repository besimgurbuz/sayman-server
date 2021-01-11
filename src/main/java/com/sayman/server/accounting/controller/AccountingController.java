package com.sayman.server.accounting.controller;

import com.sayman.server.accounting.dto.IncomeRequest;
import com.sayman.server.accounting.dto.IncomeResponse;
import com.sayman.server.accounting.exceptions.UnacceptableExpectedDateRange;
import com.sayman.server.accounting.service.IncomeService;
import com.sayman.server.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountingController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping(value = "/incomes")
    @PreAuthorize("hasAuthority('calender:read')")
    public ResponseEntity<List<IncomeResponse>> getIncomes(@AuthenticationPrincipal String activeUsername) {
        List<IncomeResponse> incomes = incomeService.getIncomes(activeUsername);

        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }

    @PostMapping(value = "/incomes")
    @PreAuthorize("hasAuthority('calender:write')")
    public ResponseEntity<?> createNewIncome(@AuthenticationPrincipal String activeUsername,
                                             @RequestBody IncomeRequest income) {
        try {
            incomeService.createNewIncome(income, activeUsername);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (UnacceptableExpectedDateRange e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
