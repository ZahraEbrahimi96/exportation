package exportation.model.bl;

import lombok.Getter;
import exportation.controller.exceptions.NoPaymentFoundException;
import exportation.model.da.PaymentDa;
import exportation.model.entity.Payment;
import exportation.model.tools.CRUD;

import java.util.List;

public class PaymentBl implements CRUD<Payment> {
    @Getter
    private static PaymentBl paymentBl = new PaymentBl();

    private PaymentBl() {
    }

    //save
    @Override
    public Payment save(Payment payment) throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            paymentDa.save(payment);
            return payment;
        }
    }

    //edit
    @Override
    public Payment edit(Payment payment) throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            if (paymentDa.findById(payment.getId()) != null) {
                paymentDa.edit(payment);
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    //remove
    @Override
    public Payment remove(int id) throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            Payment payment = paymentDa.findById(id);
            if (payment != null) {
                paymentDa.remove(id);
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Payment> findAll() throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            List<Payment> perosnList = paymentDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

    //findById
    @Override
    public Payment findById(int id) throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            Payment payment = paymentDa.findById(id);
            if (payment != null) {
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }
        }
    }

}
