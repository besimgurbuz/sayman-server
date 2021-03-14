package com.sayman.server.accounting.model;

import com.sayman.server.auth.model.User;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.lang.reflect.Field;
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
    private Boolean isHappened = false;

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
    private Boolean isRegular;

    private String name;

    private Double amount;

    @Column(name = "label_color")
    @Nullable
    private String labelColor;

    public Income replaceNullPropertiesWith(Income replaceIncome) {
        return Income.builder()
                .id(this.id != null ? this.id : replaceIncome.getId())
                .user(this.user != null ? this.user : replaceIncome.getUser())
                .isHappened(this.isHappened != null ? this.isHappened : replaceIncome.getIsHappened())
                .createdDate(this.createdDate != null ? this.createdDate : replaceIncome.getCreatedDate())
                .happenedDate(this.happenedDate != null ? this.happenedDate : replaceIncome.getHappenedDate())
                .expectedDateRange(this.expectedDateRange.replaceNullPropertiesWith(replaceIncome.getExpectedDateRange()))
                .isRegular(this.isRegular != null ? this.isRegular : replaceIncome.getIsRegular())
                .name(this.name != null ? this.name : replaceIncome.getName())
                .amount(this.amount != null ? this.amount : replaceIncome.getAmount())
                .labelColor(this.labelColor != null ? this.labelColor : replaceIncome.getLabelColor())
                .build();
    }
}
