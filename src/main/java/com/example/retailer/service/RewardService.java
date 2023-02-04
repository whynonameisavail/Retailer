package com.example.retailer.service;


import com.example.retailer.pojo.dto.RewardResult;

import java.util.List;

public interface RewardService {
    RewardResult rewardForCustomer(long customerID, int numMonths);
    List<RewardResult> rewardForAllCustomers(int numMonths);
}
