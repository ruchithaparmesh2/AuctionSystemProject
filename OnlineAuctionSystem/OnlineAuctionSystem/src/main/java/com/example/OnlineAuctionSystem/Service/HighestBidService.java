package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Model.HighestBid;
import com.example.OnlineAuctionSystem.Repository.HighestBidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HighestBidService {
    @Autowired
    HighestBidRepository highestBidRepository;
    public HighestBid saveBids(HighestBid highestBid) {
        return highestBidRepository.save(highestBid);
    }

    public List<HighestBid> getHighestBidByName(String name) {
        return highestBidRepository.getHighestBidByName(name);
    }

    public Optional<HighestBid> getHighestBidByItemName(String itemName) {
        return highestBidRepository.findById(itemName);
    }
}
