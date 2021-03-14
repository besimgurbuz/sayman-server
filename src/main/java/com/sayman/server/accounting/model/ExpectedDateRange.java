package com.sayman.server.accounting.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ExpectedDateRange {
    private LocalDateTime start;
    private LocalDateTime end;

    public ExpectedDateRange replaceNullPropertiesWith(ExpectedDateRange replacement) {
        return ExpectedDateRange.builder()
                .start(this.start != null ? this.start : replacement.getStart())
                .end(this.end != null ? this.end : replacement.getEnd())
                .build();
    }
}
