package com.azka_cake_project.service;


import com.azka_cake_project.entity.Payment;
import com.azka_cake_project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    //GET ALL PAYMENTS
    public List<Payment> payments(){
        return paymentRepository.findAll();
    }


    //CREATE PAYMENTS
    public ResponseEntity<Payment> createPayment(Payment payment){
        return ResponseEntity.ok(paymentRepository.save(payment));
    }


    //UPDATE PAYMENTS
    public ResponseEntity<Payment> updatePayment(String id, Payment updatePayment){
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()){
            Payment payment = optional.get();
            payment.setMetodePembayaran(updatePayment.getMetodePembayaran());
            payment.setTanggalPembayaran(updatePayment.getTanggalPembayaran());
            payment.setJumlahPembayaran(updatePayment.getJumlahPembayaran());

            Payment savedPayment = paymentRepository.save(payment);
            return ResponseEntity.ok(savedPayment);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deletePayment(String id){
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()){
            paymentRepository.delete(optional.get());
            return ResponseEntity.ok("payment dengan id "+id+" berhasil dihapus");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("payment dengan id"+id+" gagal dihapus");
        }
    }




}
