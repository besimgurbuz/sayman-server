package com.sayman.server.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequest {
    private Long id;
    private Boolean isHappened;
    @Nullable
    private LocalDateTime happenedDate;
    private LocalDateTime expectedDateStart;
    private LocalDateTime expectedDateEnd;
    private Boolean isRegular;
    private String name;
    private Double amount;
    private String labelColor;
}
