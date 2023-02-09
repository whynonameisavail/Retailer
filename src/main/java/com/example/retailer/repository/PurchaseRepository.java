package com.example.retailer.repository;


import com.example.retailer.pojo.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    /**
     *
     * @param id customer ID
     * @return all purchases made by this customer
     */
    List<Purchase> findByCustomerID(long id);

    @Query("SELECT new Purchase(purchaseID, customerID, payAmount, payTime) FROM Purchase WHERE customerID = ?1 AND payTime >= ?2")
    List<Purchase> findByCustomerIDAfter(long id, Date date);

    @Query("SELECT DISTINCT customerID FROM Purchase")
    List<Long> findAllCustomers();
}
