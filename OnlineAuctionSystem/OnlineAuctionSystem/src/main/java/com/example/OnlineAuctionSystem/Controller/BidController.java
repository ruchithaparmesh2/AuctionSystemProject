package com.example.OnlineAuctionSystem.Controller;

import com.example.OnlineAuctionSystem.Model.Bid;
import com.example.OnlineAuctionSystem.Service.BidService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Data
@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/{auctionId}")
    public List<Bid> getBidsByAuctionId(@PathVariable String auctionId) {
        return bidService.getBidsByAuctionId(auctionId);
    }

    @PostMapping
    public Bid placeBid(@RequestBody BidRequest bidRequest) {
        System.out.println("BidRequest received: " + bidRequest);
        return bidService.placeBid(
                bidRequest.getAuctionId(),
                bidRequest.getItemName(),
                bidRequest.getUserName(),
                bidRequest.getBidAmount()
        );
    }
   @GetMapping("/itemName/{itemName}")
    public List<Bid> getBidByItemName(@PathVariable String itemName)
   {
       return bidService.getBidByItemName(itemName);
   }
   @GetMapping("/name/{name}")
    public List<Bid> getBidByUserName(@PathVariable String name)
   {
       return bidService.getBidByUserName(name);
   }

}

 class BidRequest {

    private String auctionId;
    private String itemName;
    private String userName;
    private double bidAmount;

    // Getter and Setter for auctionId
    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    // Getter and Setter for itemName
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Getter and Setter for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and Setter for bidAmount
    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }
}

