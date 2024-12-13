package com.azka_cake_project.controller;


import com.azka_cake_project.entity.Payment;
import com.azka_cake_project.service.PaymentService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/payments")
    public List<Payment> payments(){
        return paymentService.payments();
    }

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        return paymentService.createPayment(payment);
    }

    @PutMapping("/upadate")
    public ResponseEntity<Payment> updatePayment(@PathVariable String id, @RequestBody Payment payment){
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable String id){
        return paymentService.deletePayment(id);
    }

}
