package io.pgs;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableEncryptableProperties
public class PGSApplication {
	public static void main(String[] args) {
		SpringApplicationBuilder app = new SpringApplicationBuilder(PGSApplication.class);
		app.web(WebApplicationType.SERVLET);
		app.run();
	}
}
