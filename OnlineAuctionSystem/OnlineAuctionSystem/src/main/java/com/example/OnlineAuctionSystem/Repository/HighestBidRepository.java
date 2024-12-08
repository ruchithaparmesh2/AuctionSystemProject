package com.example.OnlineAuctionSystem.Repository;

import com.example.OnlineAuctionSystem.Model.HighestBid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HighestBidRepository extends MongoRepository<HighestBid,String> {
    @Query("{userName:?0}")
    List<HighestBid> getHighestBidByName(String name);



}
