package com.clover.dao;

import com.clover.pojo.Cinema;
import com.clover.pojo.dto.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByDistrictAndFacilitiesContaining(String district, Facility facility);
}