package com.example.assignment2sadi;

import com.example.assignment2sadi.repository.CustomerRepositoryTest;
import com.example.assignment2sadi.repository.ProviderRepositoryTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerRepositoryTest.class,
        ProviderRepositoryTest.class
})
public class Assignment2SadiApplicationTests {

    @Test
    void contextLoads() {
    }

}
