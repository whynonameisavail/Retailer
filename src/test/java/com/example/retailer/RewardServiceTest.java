package com.example.retailer;

import com.example.retailer.pojo.dto.RewardResult;
import com.example.retailer.pojo.entity.Purchase;
import com.example.retailer.repository.PurchaseRepository;
import com.example.retailer.service.RewardService;
import com.example.retailer.service.impl.RewardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    private RewardService rewardService;

    @Before
    public void setUp(){

        MockitoAnnotations.openMocks(this);
        rewardService = new RewardServiceImpl(purchaseRepository);
    }

    @Test
    public void rewardForCustomer() throws ParseException {

        LocalDate now = LocalDate.now();

        LocalDate localDate = now.minusMonths(2).withDayOfMonth(1).minusDays(1);

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(new Purchase(1, 120, formatter.parse("2023-01-23")));

        when(purchaseRepository.findByCustomerIDAfter(1, date)).thenReturn(purchaseList);

        RewardResult rewardResult = rewardService.rewardForCustomer(1, 3);
        Assertions.assertEquals(90, rewardResult.getTotal());
        Assertions.assertEquals(3, rewardResult.getMonths());
        Assertions.assertEquals(3, rewardResult.getMonthlyRewards().size());

    }

    @Test
    public void rewardForAllCustomers() throws ParseException {

        LocalDate now = LocalDate.now();

        LocalDate localDate = now.minusMonths(2).withDayOfMonth(1).minusDays(1);

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<Purchase> purchaseList1 = new ArrayList<>();
        purchaseList1.add(new Purchase(1, 120, formatter.parse("2023-01-23")));

        when(purchaseRepository.findByCustomerIDAfter(1, date)).thenReturn(purchaseList1);

        List<Purchase> purchaseList2 = new ArrayList<>();
        purchaseList2.add(new Purchase(1, 80, formatter.parse("2023-01-23")));
        when(purchaseRepository.findByCustomerIDAfter(2, date)).thenReturn(purchaseList2);

        List<Long> customers = new ArrayList<>();
        customers.add(1L);
        customers.add(2L);
        when(purchaseRepository.findAllCustomers()).thenReturn(customers);

        List<RewardResult> results = rewardService.rewardForAllCustomers(3);
        Assertions.assertEquals(customers.size(), results.size());
        Assertions.assertEquals(3, results.get(0).getMonths());
        Assertions.assertEquals(3, results.get(0).getMonthlyRewards().size());

        Assertions.assertEquals(90, results.get(0).getTotal());
        Assertions.assertEquals(30, results.get(1).getTotal());

    }

}
