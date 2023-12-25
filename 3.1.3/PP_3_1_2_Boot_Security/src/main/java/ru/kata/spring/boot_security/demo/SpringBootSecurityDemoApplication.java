package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception
	{
		// Inserting the data in the mysql table.
		Role adminRole = new Role(1L, "ADMIN");
		Role userRole = new Role(2L, "USER");
		roleRepository.save(adminRole);
		roleRepository.save(userRole);
		User user = new User(1L, "USER", "USER", "20", "user@mail.com", "$2y$10$R1ugk7BiZ9pS0JRq8S1MNeTiPQ4UXcI0jAoG90trLdqFtqFhzraaS", new HashSet<Role>(List.of(userRole)));
		User admin = new User(2L, "ADMIN", "ADMIN", "20", "admin@mail.com", "$2y$10$tESVrGznZNeDWYr4AXGxHu/7D5HIdPhDeahG2ZmEs.IEqlUY0ltkm", new HashSet<Role>(Arrays.asList(userRole,adminRole)));

		userRepository.save(user);
		userRepository.save(admin);
	}
}


