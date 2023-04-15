package com.example.day_trade.Repositories;

import com.example.day_trade.Entities.Traders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Traders, String> { }
