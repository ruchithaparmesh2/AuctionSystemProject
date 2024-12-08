package com.example.OnlineAuctionSystem.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Document("highestBids")
public class HighestBid {
    @Id
    private String itemName;
    private String userName;
    private double highestBidAmount;
    private String payment ; // Default value set to "no"


    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getHighestBidAmount() {
        return highestBidAmount;
    }

    public void setHighestBidAmount(double highestBidAmount) {
        this.highestBidAmount = highestBidAmount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }


    // Override toString() for better logging
    @Override
    public String toString() {
        return "HighestBid{" +
                "itemName='" + itemName + '\'' +
                ", userName='" + userName + '\'' +
                ", highestBidAmount=" + highestBidAmount +
                ", payment='" + payment + '\'' +
                '}';
    }
}
