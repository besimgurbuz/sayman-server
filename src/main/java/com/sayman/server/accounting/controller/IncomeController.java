package com.sayman.server.accounting.controller;

import com.sayman.server.accounting.dto.IncomeRequest;
import com.sayman.server.accounting.dto.IncomeResponse;
import com.sayman.server.accounting.exceptions.AccountingException;
import com.sayman.server.accounting.exceptions.NotFoundAccountItemException;
import com.sayman.server.accounting.exceptions.UnacceptableExpectedDateRangeException;
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
@RequestMapping(value = "/api/v1/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    @PreAuthorize("hasAuthority('calender:read')")
    public ResponseEntity<List<IncomeResponse>> getIncomes(@AuthenticationPrincipal String activeUsername) {
        List<IncomeResponse> incomes = incomeService.getIncomes(activeUsername);

        return new ResponseEntity<>(incomes, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('calender:read')")
    public ResponseEntity<IncomeResponse> getIncomeById(@AuthenticationPrincipal String activeUsername,
                                                        @PathVariable Long id) {
        IncomeResponse incomeResponse = incomeService.getIncomeById(activeUsername, id);

        return new ResponseEntity<>(incomeResponse, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('calender:write')")
    public ResponseEntity<?> updateIncomeById(@AuthenticationPrincipal String activeUsername,
                                              @RequestBody IncomeRequest incomeRequest,
                                              @PathVariable Long id) {
        try {
            incomeService.updateIncomeById(incomeRequest, activeUsername, id);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (AccountingException e) {
            HttpStatus status = null;
            if (NotFoundAccountItemException.class.equals(e.getClass())) {
                status = HttpStatus.NOT_FOUND;
            } else if (UnacceptableExpectedDateRangeException.class.equals(e.getClass())) {
                status = HttpStatus.BAD_REQUEST;
            }

            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), status);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('calender:write')")
    public ResponseEntity<?> deleteIncomeById(@AuthenticationPrincipal String activeUsername,
                                              @PathVariable Long id) {
        try {
            incomeService.deleteIncomeById(activeUsername, id);
        } catch (NotFoundAccountItemException e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('calender:write')")
    public ResponseEntity<?> createNewIncome(@AuthenticationPrincipal String activeUsername,
                                             @RequestBody IncomeRequest income) {
        try {
            incomeService.createNewIncome(income, activeUsername);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (UnacceptableExpectedDateRangeException e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
