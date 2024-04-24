package com.bank.model;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    private Long userId;
    private AccountStatus accountStatus;

}
