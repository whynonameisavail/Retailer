package com.example.retailer;

public class Utility {

    /**
     *
     * @param payAmount pay amount
     * @return the reward points
     */
    public static double computeReward(double payAmount) {

        if (payAmount > 100) {
            return 2 * (payAmount - 100) + 50;
        } else if (payAmount > 50) {
            return payAmount - 50;
        } else{
            return 0.0;
        }
    }
}
