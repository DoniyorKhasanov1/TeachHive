package org.example.teachhive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.example.teachhive.util.LogUtil.logApplicationStartup;
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "org.example.teachhive.repository")
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class TeachhiveApplication {

	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TeachhiveApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        System.out.println("Starting Application ...");
        logApplicationStartup(environment);
	}

}
