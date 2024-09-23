package springdata.technest22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Technest22Application {

    public static void main(String[] args) {
        SpringApplication.run(Technest22Application.class, args);
    }

}
