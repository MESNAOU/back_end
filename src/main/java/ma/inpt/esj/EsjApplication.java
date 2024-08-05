package ma.inpt.esj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class EsjApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsjApplication.class, args);

    }

}
