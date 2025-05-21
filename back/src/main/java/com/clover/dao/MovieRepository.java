package com.clover.dao;

import com.clover.pojo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:keyword%")
    List<Movie> searchByTitle(@Param("keyword") String keyword);


    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE CONCAT('%', LOWER(:keyword), '%')")
    List<Movie> findByTitleContainingIgnoreCase(@Param("keyword") String keyword);
}