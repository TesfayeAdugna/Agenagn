package com.agenagn.project;

import com.agenagn.project.security.User;
import com.agenagn.project.security.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepository,PasswordEncoder passwordEncoder, ItemRepository itemRepository) {
		return args -> {
			Long userList = userRepository.count();
			if(userList.intValue()== 0){ 
				User user = new User();
				user.setUsername("teshome");
				user.setPassword(passwordEncoder.encode("UGR/3281/12"));
				user.setFirstName("Teshome");
				user.setLastName("Nbret");
				user.setPhone("0932255228");
				user.setEmail("teshomenbret4@gmail.com");
				user.setRole("ROLE_ADMIN");
				userRepository.save(user);

				User user1 = new User();
				user1.setUsername("kenna");
				user1.setPassword(passwordEncoder.encode("UGR/0317/12"));
				user1.setFirstName("Kenna");
				user1.setLastName("Tefera");
				user1.setPhone("0930255228");
				user1.setEmail("kenna@gmail.com");
				user1.setRole("ROLE_ADMIN");
				userRepository.save(user1);

				User user2 = new User();
				user2.setUsername("endale");
				user2.setPassword(passwordEncoder.encode("UGR/7379/12"));
				user2.setFirstName("Endale");
				user2.setLastName("Yohannes");
				user2.setPhone("0922255228");
				user2.setEmail("endaleyohannes8@gmail.com");
				user2.setRole("ROLE_ADMIN");
				userRepository.save(user2);

				User user3 = new User();
				user3.setUsername("tesfaye");
				user3.setPassword(passwordEncoder.encode("UGR/4709/12"));
				user3.setFirstName("Tesfaye");
				user3.setLastName("Adugna");
				user3.setPhone("0912255228");
				user3.setEmail("adugatesfaye888@gmail.com");
				user3.setRole("ROLE_ADMIN");
				userRepository.save(user3);

				User user4 = new User();
				user4.setUsername("rediet");
				user4.setPassword(passwordEncoder.encode("UGR/1415/12"));
				user4.setFirstName("Rediet");
				user4.setLastName("Ferew");
				user4.setPhone("0912455228");
				user4.setEmail("rediet@gmail.com");
				user4.setRole("ROLE_ADMIN");
				userRepository.save(user4);
			}
		};
	}

}
