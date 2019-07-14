package com.nico.pagantis;

import com.nico.pagantis.respository.AccountRepository;
import com.nico.pagantis.respository.DummyAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MXBean;
import java.util.Random;

@Configuration
public class Config {

    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public AccountRepository getAccountRepository() {
        return new DummyAccountRepository();
    }
}
