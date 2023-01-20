package com.hanghae.bulletbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BulletBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulletBoxApplication.class, args);
    }
}
