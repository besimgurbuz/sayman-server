package com.sayman.server.accounting.model;

import com.sayman.server.auth.model.User;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "incomes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "owner_username", referencedColumnName = "username")
    private User user;

    @Column(name = "is_happened")
    private boolean isHappened = false;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "happened_date")
    @Nullable
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
    @Nullable
    private String labelColor;
}
