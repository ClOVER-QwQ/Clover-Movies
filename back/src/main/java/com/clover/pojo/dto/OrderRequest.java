package com.clover.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private Long screeningId;
    private List<String> seats;
}