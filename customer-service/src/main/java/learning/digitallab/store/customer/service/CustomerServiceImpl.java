package learning.digitallab.store.customer.service;

import learning.digitallab.store.customer.repository.CustomerRepository;
import learning.digitallab.store.customer.entity.Customer;
import learning.digitallab.store.customer.entity.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        System.out.println("ERRROR 01");
        Customer customerDB = customerRepository.findByNumberID ( customer.getNumberID () );
        System.out.println("ERRROR 02");
        if (customerDB != null){
            System.out.println("ERRROR 03");
            return  customerDB;
        }
        System.out.println("ERRROR 04");
        customer.setStatus("CREATED");
        System.out.println("ERRROR 05");
        customerDB = customerRepository.save ( customer );
        System.out.println("ERRROR 06");
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null){
            return  null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return  customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB ==null){
            return  null;
        }
        customer.setStatus("DELETED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return  customerRepository.findById(id).orElse(null);
    }
}