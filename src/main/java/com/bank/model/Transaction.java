package com.bank.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {

    private UUID sender;
    private UUID receiver;
    private BigDecimal amount;
    private String message;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}
