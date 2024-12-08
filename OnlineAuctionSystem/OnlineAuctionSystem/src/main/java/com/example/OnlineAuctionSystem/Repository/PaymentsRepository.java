package com.example.OnlineAuctionSystem.Repository;

import com.example.OnlineAuctionSystem.Model.Payments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends MongoRepository<Payments,String> {
}
