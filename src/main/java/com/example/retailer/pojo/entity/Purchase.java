package com.example.retailer.pojo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long purchaseID;

    @Column(nullable = false)
    private long customerID;

    @Column(nullable = false)
    private double payAmount;

    private Date payTime = new Date();

    public Purchase(long customerID, double payAmount, Date payTime) {
        this.customerID = customerID;
        this.payAmount = payAmount;
        this.payTime = payTime;
    }

}
