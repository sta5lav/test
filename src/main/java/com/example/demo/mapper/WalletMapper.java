package com.example.demo.mapper;


import com.example.demo.dto.WalletDto;
import com.example.demo.entity.Wallet;
import org.springframework.stereotype.Component;


@Component
public class WalletMapper {

    public WalletDto toWalletDto(Wallet wallet) {
        return new WalletDto(wallet.getWalletId(), wallet.getAmount());
    }
}