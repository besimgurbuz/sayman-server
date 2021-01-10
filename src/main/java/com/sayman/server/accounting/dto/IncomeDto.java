package com.sayman.server.accounting.dto;

import com.sayman.server.accounting.model.AccountAction;
import com.sayman.server.accounting.model.Income;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeDto {
    private Boolean isHappened;
    private LocalDateTime happenedDate;
    private Date expectedDateStart;
    private Date expectedDateEnd;
    private Boolean isRegular;
    private String name;
    private Double amount;
    private String labelColor;

    public static IncomeDto fromIncome(Income income) {
        return IncomeDto.builder()
                .isHappened(income.isHappened())
                .happenedDate(income.getHappenedDate())
                .expectedDateStart(income.getExpectedDateRange().getStart())
                .expectedDateEnd(income.getExpectedDateRange().getEnd())
                .isRegular(income.isRegular())
                .name(income.getName())
                .amount(income.getAmount())
                .labelColor(income.getLabelColor())
                .build();
    }
}
