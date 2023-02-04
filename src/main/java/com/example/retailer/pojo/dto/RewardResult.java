package com.example.retailer.pojo.dto;

import com.example.retailer.pojo.dto.MonthReward;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RewardResult {

    private long customerID;
    private int months;
    private double total;
    private List<MonthReward> monthlyRewards;
}
