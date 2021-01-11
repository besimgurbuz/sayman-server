package com.sayman.server.accounting.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ExpectedDateRange {
    private LocalDateTime start;
    private LocalDateTime end;
}
