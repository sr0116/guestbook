package com.imchobo.guestbook;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Log4j2
@EnableJpaAuditing
public class GuestbookApplication {
	@Value("${spring.application.name}")
	private String name;
	public static void main(String[] args) {
		SpringApplication.run(GuestbookApplication.class, args);
		log.info("어플리케이션 실행");
	}
}

