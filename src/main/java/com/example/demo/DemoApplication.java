package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author Yuta Takayama
 */
@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//  @Autowired
//  JdbcTemplate jdbcTemplate;

  @Override // アプリ起動時に実行される。
  public void run(String... strings) throws Exception {
      /*
      jdbcTemplate.execute("CREATE TABLE customers(" +
              "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
  
      jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES ('John','Woo')");
      */
  }
}