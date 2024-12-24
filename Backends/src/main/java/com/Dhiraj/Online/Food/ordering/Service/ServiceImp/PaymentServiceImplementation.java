package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.PaymentResponse;
import com.Dhiraj.Online.Food.ordering.Service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImplementation implements PaymentService {
    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse generatePaymentLink(Order order) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("You have to add the link of your app" + order.getId())
                .setCancelUrl("You have to add the link of your app")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) order.getTotalAmount() * 100) // Specify the order amount in cents
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("pizza burger")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);

        System.out.println("session _____ " + session);

        PaymentResponse res = new PaymentResponse();
        res.setPayment_url(session.getUrl());

        return res;
    }

}
