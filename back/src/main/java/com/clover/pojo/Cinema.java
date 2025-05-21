package com.clover.pojo;

import com.clover.pojo.dto.Facility;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String city;
    private String district;
    private String phone;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Facility> facilities;
}

