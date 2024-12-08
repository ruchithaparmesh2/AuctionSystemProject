package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Model.Payments;
import com.example.OnlineAuctionSystem.Repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService {
    @Autowired
    PaymentsRepository paymentsRepository;

    public Payments addPayment(Payments payment) {
        return paymentsRepository.save(payment);
    }
}
