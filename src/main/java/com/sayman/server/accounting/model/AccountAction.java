package com.sayman.server.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class AccountAction {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "is_happened")
    private boolean isHappened = false;

    @Column(name = "happened_date")
    private LocalDateTime happenedDate;

    @Column(name = "action_date_range")
    @OneToOne
    private ExecutionDateRange executionDateRange;

    @Column(name = "is_regular")
    private boolean isRegular;

    private String name;

    private double amount;

    @Column(name = "label_color")
    private String labelColor;
}
