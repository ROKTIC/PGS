package io.pgs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PGSApplication {
	public static void main(String[] args) {
		SpringApplicationBuilder app = new SpringApplicationBuilder(PGSApplication.class);
		app.web(WebApplicationType.SERVLET);
		//app.build().addListeners(new ApplicationPidFileWriter("C:/works/pid/shutdown.pid"));
		app.run();
	}

	/*@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName : beanNames) {
				//System.out.println(beanName);
			}
		};
	}*/

}
