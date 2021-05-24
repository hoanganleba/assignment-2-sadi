package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Provider;
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
public class ProviderRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProviderRepository providerRepository;

    @Before
    public void setUp() {
        // Set mock data
        Provider provider = new Provider();
        provider.setName("Hoang An");
        provider.setAddress("93 Dinh Tien Hoang");
        provider.setContactPerson("Vy");
        provider.setEmail("hoanganleba@gmail.com");
        provider.setFax("123");
        provider.setPhoneNumber("123456789");
        testEntityManager.persist(provider);
        testEntityManager.flush();
    }

    @Test
    public void whenFindByName_thenExecuteProvider() {
        Provider provider = providerRepository.findByName("Hoang An");
        assertThat(provider.getName()).isEqualTo("Hoang An");
        assertThat(provider.getAddress()).isEqualTo("93 Dinh Tien Hoang");
        assertThat(provider.getContactPerson()).isEqualTo("Vy");
        assertThat(provider.getFax()).isEqualTo("123");
        assertThat(provider.getEmail()).isEqualTo("hoanganleba@gmail.com");
        assertThat(provider.getPhoneNumber()).isEqualTo("123456789");
    }
}
