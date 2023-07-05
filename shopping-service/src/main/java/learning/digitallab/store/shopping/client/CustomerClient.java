package learning.digitallab.store.shopping.client;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import learning.digitallab.store.shopping.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping(value = "/customers/{id}")
    @Retry(name = "customer-service", fallbackMethod = "getCustomerFallbackForRetry")
    @CircuitBreaker(name = "customer-service", fallbackMethod = "getCustomerFallbackForCircuitBreaker")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);

    default ResponseEntity<Customer> getCustomerFallbackForRetry(Exception e) {
        Customer customer = Customer.builder()
                .firstName("retry")
                .lastName("none")
                .email("none")
                .photoUrl("none")
                .build();
        return ResponseEntity.ok(customer);
    }

    default ResponseEntity<Customer> getCustomerFallbackForCircuitBreaker(CallNotPermittedException e) {
        Customer customer = Customer.builder()
                .firstName("circuitbreak")
                .lastName("none")
                .email("none")
                .photoUrl("none")
                .build();
        return ResponseEntity.ok(customer);
    }

}
