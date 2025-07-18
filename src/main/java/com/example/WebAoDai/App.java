package com.example.WebAoDai;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class App {

	@Value("dnoitd3ju")
	private String cloudName;

	@Value("192679756966669")
	private String apiKey;

	@Value("k3ljQtWhB9K70fR5yUNucgZu3xY")
	private String apiSecret;

	@Bean
	public Cloudinary cloudinaryConfig() {
		Cloudinary cloudinary = null;
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", cloudName);
		config.put("api_key", apiKey);
		config.put("api_secret", apiSecret);
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}



	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
