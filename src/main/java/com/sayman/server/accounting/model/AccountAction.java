package com.sayman.server.accounting.model;

import com.sayman.server.auth.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class AccountAction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "owner_username", referencedColumnName = "username")
    private User user;

    @Column(name = "is_happened")
    private boolean isHappened = false;

    @Column(name = "happened_date")
    private LocalDateTime happenedDate;

    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "expected_date_start")),
            @AttributeOverride(name = "end", column = @Column(name = "expected_date_end"))
    })
    private ExpectedDateRange expectedDateRange;

    @Column(name = "is_regular")
    private boolean isRegular;

    private String name;

    private double amount;

    @Column(name = "label_color")
    private String labelColor;
}
