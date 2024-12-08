package com.example.OnlineAuctionSystem.Controller;

import com.example.OnlineAuctionSystem.Model.HighestBid;
import com.example.OnlineAuctionSystem.Repository.HighestBidRepository;
import com.example.OnlineAuctionSystem.Service.HighestBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/highestBids")
@CrossOrigin(origins = "http://localhost:4200")

public class HighestBidController {
    @Autowired
    HighestBidService highestBidService;
    @PostMapping
    public HighestBid submitHighestBid(@RequestBody HighestBid highestBid) {
        // Log the highest bid data
        System.out.println("Received highest bid: " + highestBid);
        return highestBidService.saveBids(highestBid);
    }
    @GetMapping("/itemName/{itemName}")
    public Optional<HighestBid> getHighestBidByItemName(@PathVariable String itemName)
    {
        return highestBidService.getHighestBidByItemName(itemName);
    }
    @GetMapping("/name/{name}")
    public List<HighestBid> getHighestBidByName(@PathVariable String name) {
        return highestBidService.getHighestBidByName(name);
    }

        @Autowired
        private HighestBidRepository highestBidRepository;

    @PutMapping("/updatePayment")
    public ResponseEntity<String> updatePayment(@RequestParam String itemName, @RequestParam String payment) {
        Optional<HighestBid> highestBidOptional = highestBidRepository.findById(itemName);
        if (highestBidOptional.isPresent()) {
            HighestBid highestBid = highestBidOptional.get();
            highestBid.setPayment(payment);
            highestBidRepository.save(highestBid);
            return ResponseEntity.ok("Payment field updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }

}







