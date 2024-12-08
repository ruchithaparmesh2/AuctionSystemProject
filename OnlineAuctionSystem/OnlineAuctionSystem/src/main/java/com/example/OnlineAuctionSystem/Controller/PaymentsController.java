package com.example.OnlineAuctionSystem.Controller;

import com.example.OnlineAuctionSystem.Model.Payments;
import com.example.OnlineAuctionSystem.Service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    @Autowired
    PaymentsService paymentsService;
    @PostMapping("/add")
    public Payments addPayment(@RequestBody Payments payment)
    {
        return paymentsService.addPayment(payment);
    }
}
