package com.sayman.server.accounting.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "incomes")
public class Income extends AccountAction {
}
