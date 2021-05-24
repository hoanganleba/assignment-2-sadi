package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        // Set mock data
        Customer customer = new Customer();
        customer.setName("Hoang An");
        customer.setAddress("93 Dinh Tien Hoang");
        customer.setContactPerson("Vy");
        customer.setEmail("hoanganleba@gmail.com");
        customer.setFax("123");
        customer.setPhoneNumber("123456789");
        testEntityManager.persist(customer);
        testEntityManager.flush();
    }

    @Test
    public void whenFindByName_thenExecuteCustomer() {
        Customer customer = customerRepository.findByName("Hoang An");
        assertThat(customer.getName()).isEqualTo("Hoang An");
        assertThat(customer.getAddress()).isEqualTo("93 Dinh Tien Hoang");
        assertThat(customer.getContactPerson()).isEqualTo("Vy");
        assertThat(customer.getFax()).isEqualTo("123");
        assertThat(customer.getEmail()).isEqualTo("hoanganleba@gmail.com");
        assertThat(customer.getPhoneNumber()).isEqualTo("123456789");
    }
}
