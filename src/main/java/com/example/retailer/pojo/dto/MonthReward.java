package com.example.retailer.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthReward {

    private String month;
    private double rewardPoint;
}
