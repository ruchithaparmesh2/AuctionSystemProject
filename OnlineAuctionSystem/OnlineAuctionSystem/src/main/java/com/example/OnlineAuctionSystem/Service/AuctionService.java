package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Model.Auction;
import com.example.OnlineAuctionSystem.Model.Bid;
import com.example.OnlineAuctionSystem.Repository.AuctionRepository;
import com.example.OnlineAuctionSystem.Repository.BidRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    BidRepository bidRepository;

    public Auction saveAuction(Auction auction) {
        return auctionRepository.save(auction);
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    // Scheduler to log the highest bid for expired auctions
    @Scheduled(fixedRate = 60000) // Run every minute
    public void checkAndLogExpiredAuctions() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        List<Auction> auctions = auctionRepository.findAll();
        for (Auction auction : auctions) {
            String auctionEndDateTime = auction.getAuctionEndDate() + " " + auction.getAuctionEndTime();
            LocalDateTime auctionEnd = LocalDateTime.parse(auctionEndDateTime, formatter);

            if (LocalDateTime.now().isAfter(auctionEnd)) {
                // Fetch all bids for the auction
                List<Bid> bids = bidRepository.findByAuctionId(auction.getId());
                if (!bids.isEmpty()) {
                    // Find the highest bid
                    Bid highestBid = bids.stream()
                            .max((b1, b2) -> Double.compare(b1.getBidAmount(), b2.getBidAmount()))
                            .orElse(null);

                    if (highestBid != null) {
                        System.out.println("Auction Item: " + auction.getItemName());
                        System.out.println("Highest Bid: $" + highestBid.getBidAmount() + " by " + highestBid.getUserName());
                    }
                }

                // Optionally delete the auction from the database after logging
                // auctionRepository.delete(auction);
            }
        }
    }

    public List<Auction> getAuctionByName(String name) {
        return auctionRepository.getAuctionByName(name);
    }

    public Auction getAuctionByItemName(String itemName) {
        return auctionRepository.getAuctionByItemName(itemName);
    }

    public Auction deleteAuction(String itemName) {
        return auctionRepository.deleteItem(itemName);
    }
}
