package com.sayman.server.accounting.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Income")
public class Income extends AccountAction {
}
