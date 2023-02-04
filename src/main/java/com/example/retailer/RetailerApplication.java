package com.example.retailer;

import com.example.retailer.pojo.entity.Purchase;
import com.example.retailer.repository.PurchaseRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableJpaAuditing
public class RetailerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailerApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDemoDataSet(PurchaseRepository repository) {
        return (args) -> {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            repository.save(new Purchase(1, 130, formatter.parse("2022-11-22")));
            repository.save(new Purchase(1, 140, formatter.parse("2022-11-03")));

            repository.save(new Purchase(1, 120, formatter.parse("2022-12-22")));
            repository.save(new Purchase(1, 100, formatter.parse("2022-12-05")));

            repository.save(new Purchase(1, 110, formatter.parse("2023-01-22")));
            repository.save(new Purchase(1, 150, formatter.parse("2023-01-06")));

            repository.save(new Purchase(1, 90, formatter.parse("2023-02-02")));
            repository.save(new Purchase(1, 40, formatter.parse("2023-02-07")));


            repository.save(new Purchase(2, 40, formatter.parse("2022-11-22")));
            repository.save(new Purchase(2, 60, formatter.parse("2022-11-08")));

            repository.save(new Purchase(2, 50, formatter.parse("2022-12-22")));
            repository.save(new Purchase(2, 80, formatter.parse("2022-12-09")));

            repository.save(new Purchase(2, 80, formatter.parse("2023-01-22")));
            repository.save(new Purchase(2, 100, formatter.parse("2023-01-03")));

            repository.save(new Purchase(2, 120, formatter.parse("2023-02-03")));
            repository.save(new Purchase(2, 140, formatter.parse("2023-02-02")));

        };
    }

}
