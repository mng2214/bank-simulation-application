package com.bank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
 // @NotNull
  private AccountDTO sender;
//  @NotNull
  private AccountDTO receiver;
  @Positive
  @NotNull
  private BigDecimal amount;
  @NotNull
  @Size(min = 2,max = 250)
  @Pattern(regexp = "^[a-zA-Z0-9]*$")
  private String message;
  private Date createDate;

}
