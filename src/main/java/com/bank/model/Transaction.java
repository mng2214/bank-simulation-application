package com.bank.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {
    @NotNull
    private UUID sender;
    @NotNull
    private UUID receiver;
    @Positive
    @NotNull
    private BigDecimal amount;
    @Size(min = 2, max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String message;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}
