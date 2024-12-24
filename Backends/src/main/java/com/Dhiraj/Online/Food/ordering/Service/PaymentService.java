package com.Dhiraj.Online.Food.ordering.Service;

import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponse generatePaymentLink(Order order) throws StripeException;
}
