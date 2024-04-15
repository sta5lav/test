package com.example.demo.controller;



import com.example.demo.dto.InputWalletDto;
import com.example.demo.dto.WalletDto;
import com.example.demo.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private  final WalletService service;

    @GetMapping("/wallets/{WALLET_UUID}")
    public WalletDto getData(@PathVariable UUID WALLET_UUID) {
        return service.getData(WALLET_UUID);
    }

    @PostMapping("/wallet")
    public WalletDto addingAndWithdrawingFromTheDeposit(@Valid @RequestBody InputWalletDto inputWalletDto) {
        return service.addingAndWithdrawingFromTheDeposit(inputWalletDto);
    }

}