package com.example.OnlineAuctionSystem.Repository;



import com.example.OnlineAuctionSystem.Model.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AuctionRepository extends MongoRepository<Auction, String> {
    @Query("{userName:?0}")
    List<Auction> getAuctionByName(String name);
    @Query("{itemName:?0}")
    Auction getAuctionByItemName(String itemName);
    @Query("{itemName:?0}")
    Auction deleteItem(String itemName);
}
