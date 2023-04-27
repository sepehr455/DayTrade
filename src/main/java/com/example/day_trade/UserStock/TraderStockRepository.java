package com.example.day_trade.UserStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderStockRepository extends JpaRepository<TraderStock, Long> {
    boolean existsByTraderUserIdAndStockStockId(Long traderId, Long stockId);
    Optional<TraderStock> findByTraderUserIdAndStockStockId(Long traderId, Long stockId);
}