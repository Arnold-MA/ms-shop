package learning.digitallab.store.customer.repository;

import learning.digitallab.store.customer.entity.Customer;
import learning.digitallab.store.customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {
    Customer findByNumberID(String numberID);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByRegion(Region region);
}
