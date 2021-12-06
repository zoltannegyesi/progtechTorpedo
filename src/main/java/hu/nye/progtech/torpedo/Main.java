package hu.nye.progtech.torpedo;

import hu.nye.progtech.torpedo.service.game.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * Main class, to run the program.
 */

@SpringBootApplication
@Component
public class Main implements CommandLineRunner {


    /**
     * Run the program,
     * and create the ApplicationContext for Spring.
     */
    @Autowired
    private GameController gameController;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) {
        gameController.start();
    }
}
