package com.bank.Entity;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(columnDefinition = "TIMESTAMP")
    private Date creationDate;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

}
