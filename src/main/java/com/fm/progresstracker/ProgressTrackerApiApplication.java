package com.fm.progresstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgressTrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgressTrackerApiApplication.class, args);
        System.out.println("Application Started -->");
    }

}
