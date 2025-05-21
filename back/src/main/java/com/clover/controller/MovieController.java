package com.clover.controller;

import com.clover.pojo.Movie;
import com.clover.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    // 添加获取所有电影的方法
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // 搜索选项（搜索电影）
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam String keyword) {
        return ResponseEntity.ok(movieService.searchMovies(keyword));
    }

    // 创建电影
    @PostMapping
    public ResponseEntity<Movie> createMovie(
            @RequestBody @Valid Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.createMovie(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}