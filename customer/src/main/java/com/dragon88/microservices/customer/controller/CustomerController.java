package com.dragon88.microservices.customer.controller;

import java.util.List;
import java.util.logging.Logger;

import com.dragon88.microservices.customer.client.InvoiceServiceClient;
import com.dragon88.microservices.customer.dto.CustomerOrders;
import com.dragon88.microservices.customer.dto.InventoryCheckResultDTO;
import com.dragon88.microservices.customer.dto.Invoice;
import com.dragon88.microservices.customer.dto.OrderDetail;
import com.dragon88.microservices.customer.models.Customer;
import com.dragon88.microservices.customer.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dragon88.microservices.customer.repository.CustomerRepository;
import com.dragon88.microservices.customer.services.InventoryService;

/**
 * @author Trong Tran
 *
 */
@RestController
public class CustomerController {
    protected Logger logger = Logger.getLogger(CustomerController.class
            .getName());
	
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceServiceClient invoiceServiceClient;
	
	@Autowired
	InventoryService inventoryService;

	@RequestMapping(value="/customers", method= RequestMethod.GET, produces = "application/json")
	public Iterable<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}

	@RequestMapping(value="/customer", method= RequestMethod.GET, produces = "application/json")
	public Customer getCustomerById(@RequestParam("id") String id) throws Exception {
		return customerRepository.findById(Long.parseLong(id)).orElseThrow(Exception::new);
	}

	@RequestMapping(value="/customers/name/{name}", method= RequestMethod.GET, produces = "application/json")
	public List<Customer> getCustomerByName(@PathVariable("name") String customerName){        
		return customerRepository.findByCustomerName(customerName);
	}

	@RequestMapping(value="/customers/new/", method= RequestMethod.POST, produces = "application/json",consumes = "application/json")
	public Customer addCustomer(@RequestBody Customer customer){
		return customerRepository.save(customer);
	}

	@RequestMapping(value="/customers/{id}/orders", method= RequestMethod.GET, produces = "application/json")
	public CustomerOrders getCustomerOrders(@PathVariable("id") String id) throws Exception {
		//    System.out.println("Calling a load balanced Rest template");	
		//        return invoiceService.getInvoices(id);
		CustomerOrders customerOrders= new CustomerOrders();
		Customer customer = customerRepository.findById(Long.parseLong(id)).orElseThrow(Exception::new);
		customerOrders.setCustomer(customer);
		logger.info("Calling feign client to get invoices"+id);
		List<Invoice> invoiceList =  invoiceServiceClient.getInvoices(id);
		customerOrders.setInvoiceList(invoiceList);
		return customerOrders;
	}
	



/**
 * http://localhost:2222/customers/order
 * {
"customerId":1,
"modePayId":2,
"cashierName":"hanuman",
"items":[
	{
		"itemId":2,
		"quantity":5,
		"unitCost":10

	}
	]
}
 * @param orderDetail
 * @return
 */
	//TODO - design for failure
	@RequestMapping(value="/customers/order", method= RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	public OrderDetail placeOrder(@RequestBody OrderDetail orderDetail) {
	
		List<InventoryCheckResultDTO> inventoryCheckResultDTOs =	inventoryService.checkAvailability(orderDetail);
		for(InventoryCheckResultDTO in: inventoryCheckResultDTOs) {
			logger.info(in.toString());
		}
		// TODO - do something if the requested inventory is not there
		OrderDetail orderPlaced = invoiceService.createInvoice(orderDetail);
		inventoryService.updateInventory(orderPlaced);
		return orderPlaced;
	}	
}
