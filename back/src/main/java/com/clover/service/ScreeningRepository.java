package com.clover.service;

import com.clover.pojo.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
