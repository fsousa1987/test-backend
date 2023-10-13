package com.github.fsousa1987.effecti.testbackend.domain.repository;

import com.github.fsousa1987.effecti.testbackend.domain.entity.BidsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiddingRepository extends JpaRepository<BidsEntity, Long> {
}
