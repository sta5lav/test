package com.example.demo;

import com.example.demo.controller.WalletController;
import com.example.demo.dto.InputWalletDto;
import com.example.demo.dto.WalletDto;
import com.example.demo.service.WalletService;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.UUID;

import static com.example.demo.enums.OperatoinType.DEPOSIT;
import static com.example.demo.enums.OperatoinType.WITHDRAW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WalletControllerTests {

    private final WalletService service = mock(WalletService.class);
    private final WalletController controller = new WalletController(service);

    @Test
    void getDataTest() {
        UUID uuid = UUID.randomUUID();
        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        when(service.getData(uuid)).thenReturn(new WalletDto(uuid, bigDecimal));
        WalletDto walletDto = controller.getData(uuid);
        assertEquals(bigDecimal, walletDto.getAmount());
    }

    @Test
    void testOperationDeposit() {
        UUID uuid = UUID.randomUUID();
        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        InputWalletDto dto = new InputWalletDto(uuid, DEPOSIT, bigDecimal);
        when(service.addingAndWithdrawingFromTheDeposit(dto)).thenReturn(new WalletDto(uuid, bigDecimal));
        WalletDto walletDto = controller.addingAndWithdrawingFromTheDeposit(dto);
        assertEquals(bigDecimal, walletDto.getAmount());
    }

    @Test
    void testOperationWithdrawal() {
        UUID uuid = UUID.randomUUID();
        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        InputWalletDto dto = new InputWalletDto(uuid, WITHDRAW, new BigDecimal("200"));
        when(service.addingAndWithdrawingFromTheDeposit(dto))
                .thenReturn(new WalletDto(uuid, bigDecimal.subtract(dto.getAmount())));
        WalletDto walletDto = controller.addingAndWithdrawingFromTheDeposit(dto);
        assertEquals(bigDecimal.subtract(dto.getAmount()), walletDto.getAmount());
    }

}
