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
    private Long sender;
  //  @NotNull
    private Long receiver;
    @Positive
    @NotNull
    private BigDecimal amount;
    @Size(min = 2, max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String message;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}
