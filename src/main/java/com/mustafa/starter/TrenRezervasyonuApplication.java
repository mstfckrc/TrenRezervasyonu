package com.mustafa.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mustafa")
public class TrenRezervasyonuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrenRezervasyonuApplication.class, args);
    }

}
