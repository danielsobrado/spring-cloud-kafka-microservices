package com.jds.jvmcc.productservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jds.jvmcc.productservice.entity.Payment;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@FeignClient(name = "payment-client", url = "http://localhost:8081/resource-server-jwt")
public interface PaymentClient {

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    List<Payment> getPayments();
}