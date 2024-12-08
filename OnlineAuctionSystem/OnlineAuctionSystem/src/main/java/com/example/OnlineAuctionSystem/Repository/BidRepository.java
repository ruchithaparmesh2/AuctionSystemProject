package com.example.OnlineAuctionSystem.Repository;

import com.example.OnlineAuctionSystem.Model.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BidRepository  extends MongoRepository<Bid,String> {
    List<Bid> findByAuctionId(String auctionId);
    @Query("{itemName:?0}")
    public List<Bid> getBidByItemName(String itemName);
    @Query("{userName:?0}")
    public List<Bid> getBidByUserName(String name);

}
