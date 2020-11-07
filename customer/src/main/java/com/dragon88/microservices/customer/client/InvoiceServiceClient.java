/**
 *
 */
package com.dragon88.microservices.customer.client;

import com.dragon88.microservices.customer.dto.Invoice;
import com.dragon88.microservices.customer.controller.CustomerController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author trongtb
 *
 */
// for feign client we do not need @HystrixCommand
@FeignClient(name = "invoice-service",
        fallback = InvoiceServiceClient.InvoiceClientFallback.class)
public interface InvoiceServiceClient {

    Logger logger = Logger.getLogger(CustomerController.class
            .getName());

    @RequestMapping(method = RequestMethod.GET, value = "/invoice/custid/{custid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Invoice> getInvoices(@PathVariable("custid") String custid);

    /**
     * Circuit Breaker code, This will run if Invoice-service is down
     *
     */
    @Component
    static class InvoiceClientFallback implements InvoiceServiceClient {
        @Override
        public List<Invoice> getInvoices(String custId) {
            List<Invoice> invoiceList = new ArrayList<Invoice>();
            logger.info("Service is down, please try later");
            return invoiceList;
        }
    }
}



