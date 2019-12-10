package com.otto.ProjectSpring;

import com.otto.ProjectSpring.dao.UserRepository;
import com.otto.ProjectSpring.entity.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class ProjectSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializer(UserRepository repository){
		return args -> {
			Optional<User> userOptional = repository.findByUsername("admin");
			if(userOptional.isEmpty()){
				User user = new User();
				user.setUsername("admin");
				user.setPassword("$2a$10$wdN0CXWAD7vJ0bIxOHasI.Y.5LJrYanLW/NaOFEhPNvcdaJUR4SfS");
				user.setAuthority("ROLE_ADMIN");
				user.setEnabled(true);
				repository.save(user);
			}
		};
	}
}
