package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Customer;
import com.example.assignment2sadi.model.Sale;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SaleRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        Customer customer = new Customer();
        customer.setName("Hoang An");
        customer.setAddress("93 Dinh Tien Hoang");
        customer.setContactPerson("Vy");
        customer.setEmail("hoanganleba@gmail.com");
        customer.setFax("123");
        customer.setPhoneNumber("123456789");
        Sale sale = new Sale();
        sale.setCustomer(customer);

        testEntityManager.persist(sale);
        testEntityManager.flush();
    }

    @Test
    public void whenFindByName_thenExecuteProvider() {


    }
}
