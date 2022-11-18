package com.swift.loadfile.loadbigfile1;

import com.swift.loadfile.loadbigfile1.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 * Created by wenwen on 2017/4/11.
 * version 1.0
 */
@SpringBootApplication

public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner init(final StorageService storageService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                storageService.deleteAll();
                storageService.init();
            }
        };
    }

}
