package com.sayman.server.accounting.model;

import com.sayman.server.auth.model.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity(name = "expenses")
public class Expense extends AccountAction {
}
