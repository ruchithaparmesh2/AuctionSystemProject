package com.example.OnlineAuctionSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payments {
    @Id
    private String id;
    private String itemName;
    private String userName;
    private String ownerName;
    private Integer bidAmount;
}
