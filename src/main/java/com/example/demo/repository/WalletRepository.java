package com.example.demo.repository;


import com.example.demo.entity.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID>{

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findByWalletId(UUID uuid);

}
