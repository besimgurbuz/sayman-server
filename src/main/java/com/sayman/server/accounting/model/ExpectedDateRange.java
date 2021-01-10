package com.sayman.server.accounting.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ExpectedDateRange {
    private Date start;

    private Date end;
}
