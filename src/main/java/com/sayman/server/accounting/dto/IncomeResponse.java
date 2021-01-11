package com.sayman.server.accounting.dto;

import com.sayman.server.accounting.model.Income;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeResponse {
    private Long id;
    private String username;
    private Boolean isHappened;
    private LocalDateTime happenedDate;
    private LocalDateTime expectedDateStart;
    private LocalDateTime expectedDateEnd;
    private Boolean isRegular;
    private String name;
    private Double amount;
    private String labelColor;
}
