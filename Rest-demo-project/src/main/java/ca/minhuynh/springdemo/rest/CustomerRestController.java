package ca.minhuynh.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.minhuynh.springdemo.entity.Customer;
import ca.minhuynh.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public List<Customer> getCustomer(){
		return customerService.getCustomers(); //thank for watching
	}
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		Customer theCustomer = customerService.getCustomer(customerId);
		if(theCustomer == null){
			throw new CustomerNotFoundException("Customer id not found " + customerId);
		}
		return theCustomer;
	}
	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer theCustomer){
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer theCustomer){
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customer/{customerId}")
	public String deleteCustomer(@PathVariable int customerId){
		Customer temp = customerService.getCustomer(customerId);
		
		if(temp == null){
			throw new CustomerNotFoundException("Customer id not found " + customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Deleted" + customerId;
	}
	
}
