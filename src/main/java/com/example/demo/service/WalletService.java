package com.example.demo.service;

import com.example.demo.dto.InputWalletDto;
import com.example.demo.dto.WalletDto;
import com.example.demo.entity.Wallet;
import com.example.demo.mapper.WalletMapper;
import com.example.demo.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


import static com.example.demo.enums.OperatoinType.DEPOSIT;
import static com.example.demo.enums.OperatoinType.WITHDRAW;
import static java.math.BigDecimal.ZERO;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;
    private final WalletMapper mapper;


    @Transactional
    public WalletDto addingAndWithdrawingFromTheDeposit(InputWalletDto inputWalletDto) {
        log.info("Data received: {}", inputWalletDto);
        Wallet wallet = findWallet(inputWalletDto.getWalletId());

        if (DEPOSIT.equals(inputWalletDto.getOperatoinType())) {
            wallet.setAmount(inputWalletDto.getAmount().add(inputWalletDto.getAmount()));
            log.info("A deposit has been added: {}", wallet);
        }
        if (WITHDRAW.equals(inputWalletDto.getOperatoinType())) {
            if (wallet.getAmount().subtract(inputWalletDto.getAmount()).compareTo(ZERO) < 0) {
                throw new RuntimeException("Insufficient funds");
            } else {
                wallet.setAmount(wallet.getAmount().subtract(inputWalletDto.getAmount()));
                log.info("Withdrawal: {}", wallet);
            }
        }
        return mapper.toWalletDto(wallet);
    }

    private Wallet findWallet(UUID walletId) {
        Optional<Wallet> wallet = repository.findByWalletId(walletId);
        if (wallet.isPresent()) {
            return wallet.get();
        } else {
            throw new RuntimeException("The wallet was not found");
        }
    }

    public WalletDto getData(UUID walletId) {
        Wallet wallet = findWallet(walletId);
        log.info("Getiing data: {}", wallet);
        return mapper.toWalletDto(wallet);
    }


}