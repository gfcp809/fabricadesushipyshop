package com.fabricadesushipyshop.fabricadesushipyshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SushiRepository extends JpaRepository<Sushi, Long> {
}