package com.example.retailer;

import com.example.retailer.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RetailerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Before
    public void clearBeforeTests() throws Exception {
        purchaseRepository.deleteAll();
    }

    @Test
    public void computeReward() {

        Assertions.assertEquals(250, Utility.computeReward(200));
        Assertions.assertEquals(90, Utility.computeReward(120));
        Assertions.assertEquals(50, Utility.computeReward(100));
        Assertions.assertEquals(30, Utility.computeReward(80));
        Assertions.assertEquals(0, Utility.computeReward(50));
        Assertions.assertEquals(0, Utility.computeReward(10));

    }

    @Test
    public void createPurchase() throws Exception {

        mockMvc.perform(post("/purchases").content(
                "{\"customerID\": 1, \"payAmount\": 190}")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("purchases/")));
    }


    @Test
    public void findByCustomerID() throws Exception {

        mockMvc.perform(post("/purchases").content(
                "{\"customerID\": 1, \"payAmount\": 190}")).andExpect(
                status().isCreated());

        mockMvc.perform(
                get("/purchases/search/findByCustomerID?id={id}", 1)).andExpect(
                status().isOk()).andExpect(
                jsonPath("$._embedded.purchases[0].customerID").value(
                        1));
    }


    @Test
    public void rewardForCustomer() throws Exception {

        mockMvc.perform(post("/purchases").content(
                "{\"customerID\": 100, \"payAmount\": 190}")).andExpect(
                status().isCreated());

        mockMvc.perform(get("/reward?customerID=100")).andExpect(
                status().isOk()).andExpect(
                jsonPath("$[0].total").value(
                        230));

    }

    @Test
    public void rewardForCustomerWithParameter() throws Exception {

        mockMvc.perform(post("/purchases").content(
                "{\"customerID\": 200, \"payAmount\": 190}")).andExpect(
                status().isCreated());

        mockMvc.perform(get("/reward?customerID=200&months={months}", 4)).andExpect(
                status().isOk()).andExpect(
                jsonPath("$[0].months").value(
                        4));

    }

    @Test
    public void rewardForAllCustomers() throws Exception {

        mockMvc.perform(post("/purchases").content(
                "{\"customerID\": 1, \"payAmount\": 190}")).andExpect(
                status().isCreated());

        mockMvc.perform(get("/reward")).andExpect(
                status().isOk());

    }

}
