package com.example.OnlineAuctionSystem.Controller;

import com.example.OnlineAuctionSystem.Model.Auction;
import com.example.OnlineAuctionSystem.Service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
@CrossOrigin(origins = "http://localhost:4200") // Ensure that the Angular frontend can access this endpoint
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @GetMapping
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    // Endpoint to save the auction data
    @PostMapping("/add")
    public Auction addAuction(@RequestBody Auction auction) {
        return auctionService.saveAuction(auction);
    }
    @GetMapping("/name/{name}")
    public List<Auction> getAuctionByName(@PathVariable String name)
    {
        return auctionService.getAuctionByName(name);
    }
    @GetMapping("/itemName/{itemName}")
    public Auction getAuctionByItemName(@PathVariable String itemName)
    {
        return auctionService.getAuctionByItemName(itemName);
    }
    @DeleteMapping("/deleteItem/{itemName}")
    public Auction deleteAuction(@PathVariable String itemName)
    {
        return auctionService.deleteAuction(itemName);
    }

}
