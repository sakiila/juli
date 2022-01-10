package juli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author baobo
 * @date 2022/1/10 19:48
 */
@SpringBootApplication
@Slf4j
public class MainApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        log.info("MainApplication running");
    }
}
