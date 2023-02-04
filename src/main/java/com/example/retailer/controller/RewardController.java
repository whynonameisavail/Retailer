
package com.example.retailer.controller;

import com.example.retailer.pojo.dto.RewardResult;
import com.example.retailer.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/reward/{customerID}")
    public ResponseEntity<RewardResult> rewardForCustomer(@PathVariable long customerID, @RequestParam(value = "months", defaultValue = "3") int months) {
        RewardResult rewardResult = rewardService.rewardForCustomer(customerID, months);
        return new ResponseEntity<>(rewardResult, HttpStatus.OK);
    }

    /**
     *
     * @param months recent months to compute
     * @return a reward points information for each customer
     */
    @GetMapping("/reward")
    public ResponseEntity<List<RewardResult>> rewardForAllCustomers(@RequestParam(value = "months", defaultValue = "3") int months) {
        List<RewardResult> results = rewardService.rewardForAllCustomers(months);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}

