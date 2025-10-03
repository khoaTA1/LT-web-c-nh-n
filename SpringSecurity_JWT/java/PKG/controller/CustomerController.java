package PKG.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import PKG.entity.Customer;

@RestController
@EnableMethodSecurity
public class CustomerController {
	final private List<Customer> customerList = List.of(Customer.builder().id("001").name("Trương Anh Khoa").email("Khoa1@gmail.com").build(), 
														Customer.builder().id("002").name("Trương Khoa").email("khoa2@gmail.com").build());
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Xin chào, hãy đăng nhập theo role trong config và thay đổi url theo controller để kiểm thử phân quyền.");
	}

	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList() {
		List<Customer> list = this.customerList;
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
		List<Customer> customers = this.customerList.stream().filter(customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
}
