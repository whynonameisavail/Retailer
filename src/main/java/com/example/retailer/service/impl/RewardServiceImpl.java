package com.example.retailer.service.impl;

import com.example.retailer.Utility;
import com.example.retailer.pojo.dto.MonthReward;
import com.example.retailer.pojo.entity.Purchase;
import com.example.retailer.pojo.dto.RewardResult;
import com.example.retailer.repository.PurchaseRepository;
import com.example.retailer.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.time.LocalDate;

@Service
public class RewardServiceImpl implements RewardService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public RewardServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public RewardResult rewardForCustomer(long customerID, int numMonths) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Map<String, Double> rewardMap = new HashMap<>();
        List<String> monthString = new ArrayList<>();
        for (int i = 0; i < numMonths; i++) {
            LocalDate date = now.minusMonths(i);
            String monthStr = date.format(formatter);
            monthString.add(monthStr);
            rewardMap.put(monthStr, 0.0);
        }

        List<Purchase> purchases = purchaseRepository.findByCustomerID(customerID);
        for (Purchase purchase : purchases) {
            String dateStr = purchase.getPayTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate().format(formatter);

            if (rewardMap.containsKey(dateStr)) {
                double award = Utility.computeReward(purchase.getPayAmount());
                rewardMap.put(dateStr, rewardMap.get(dateStr) + award );
            }
        }

        double total = 0;
        List<MonthReward> rewards = new ArrayList<>();
        for (String monthStr : monthString) {
            double reward = rewardMap.get(monthStr);
            rewards.add(new MonthReward(monthStr, reward));
            total += reward;
        }

        Collections.reverse(rewards);
        return new RewardResult(customerID, numMonths, total, rewards);
    }

    public List<RewardResult> rewardForAllCustomers(int numMonths) {
        List<Long> customers = purchaseRepository.findAllCustomers();
        List<RewardResult> results = new ArrayList<>();
        for (long customerID : customers) {
            results.add(rewardForCustomer(customerID, numMonths));
        }
        return results;
    }
}
