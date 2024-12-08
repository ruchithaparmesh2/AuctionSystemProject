package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Model.Bid;
import com.example.OnlineAuctionSystem.Repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public List<Bid> getBidsByAuctionId(String auctionId) {
        return bidRepository.findByAuctionId(auctionId);
    }

    public Bid placeBid(String auctionId, String itemName, String userName, double bidAmount) {
        Bid bid = new Bid();
        bid.setAuctionId(auctionId);
        bid.setItemName(itemName);
        bid.setUserName(userName);
        bid.setBidAmount(bidAmount);
        bid.setBidTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        return bidRepository.save(bid);
    }

    public List<Bid> getBidByItemName(String itemName) {
      return  bidRepository.getBidByItemName(itemName);

    }

    public List<Bid> getBidByUserName(String name) {
        return bidRepository.getBidByUserName(name);
    }
}
