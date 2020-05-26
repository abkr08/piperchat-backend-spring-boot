package com.example.piper.piperchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiperchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiperchatApplication.class, args);
		System.setProperty("aws.accessKeyId", "AKIAULBNIKW7ZEDP3V4T");
		System.setProperty("aws.secretAccessKey", "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");
	}

}
