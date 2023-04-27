package com.example.day_trade.UserStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderStockRepository extends JpaRepository<TraderStock, Long> {
    boolean existsByTraderUserIdAndStockStockId(Long traderId, Long stockId);

//    @Modifying
//    @Query(value = "UPDATE trader_stock SET quantity = quantity + :quantity WHERE user_id = :traderId AND stock_id = :stockId", nativeQuery = true)
//    void addQuantityToTraderStock(@Param("quantity") int quantity, @Param("traderId") Long traderId, @Param("stockId") Long stockId);

    Optional<TraderStock> findByTraderUserIdAndStockStockId(Long traderId, Long stockId);

}