package net.croz.nrichdemobackend;

import net.croz.nrichdemobackend.main.service.BootstrapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class NrichDemoBackendApplication {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(NrichDemoBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initBootStrapData(BootstrapService bootStrapService) {
        return args -> bootStrapService.createBootstrapData();
    }
}
