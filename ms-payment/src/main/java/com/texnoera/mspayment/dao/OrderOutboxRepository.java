package com.texnoera.mspayment.dao;

import com.texnoera.mspayment.dao.entity.OrderOutboxEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OrderOutboxRepository {

    public OrderOutboxEntity getByStatus(String status) {
        return new OrderOutboxEntity();
    }

    public void save(OrderOutboxEntity orderOutboxEntity){
    }

}
