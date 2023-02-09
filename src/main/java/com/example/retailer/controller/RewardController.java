
package com.example.retailer.controller;

import com.example.retailer.pojo.dto.RewardResult;
import com.example.retailer.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RewardController {

    private final RewardService rewardService;
    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }


    /**
     *
     * @param customerID customer ID
     * @param months recent months to compute, default is 3
     * @return the reward result for the customer, containing reward for each month and total reward
     */
    @GetMapping("/reward")
    public ResponseEntity<List<RewardResult>> rewardForCustomer( @RequestParam(value = "customerID", defaultValue = "-1") long customerID, @RequestParam(value = "months", defaultValue = "3") int months) {
        if (customerID == -1) {
            // give reward for all customers
            List<RewardResult> results = rewardService.rewardForAllCustomers(months);
            return new ResponseEntity<>(results, HttpStatus.OK);

        } else {
            // give reward for the specfic customer
            RewardResult rewardResult = rewardService.rewardForCustomer(customerID, months);
            List<RewardResult> results = new ArrayList<>();
            results.add(rewardResult);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

    }
}

