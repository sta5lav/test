package com.example.demo.dto;



import com.example.demo.enums.OperatoinType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputWalletDto {

    @NotNull
    private UUID walletId;

    @NotNull
    private OperatoinType operatoinType;

    @Positive
    private BigDecimal amount;

}
