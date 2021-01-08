package com.sayman.server.accounting.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExecutionDateRange {
    @Id
    @GeneratedValue
    private Long id;

    private Date start;

    private Date end;
}
