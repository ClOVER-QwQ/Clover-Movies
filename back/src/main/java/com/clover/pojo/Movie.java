package com.clover.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    private String director;
    private String actors;
    private String genre;
    private Integer duration;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private Double rating;

    @Lob
    private String description;

    private String posterUrl;
    private String trailerUrl;
}
