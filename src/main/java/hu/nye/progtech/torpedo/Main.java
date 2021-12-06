package hu.nye.progtech.torpedo;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.game.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Main class, to run the program.
 */

@SpringBootApplication
@Component
public class Main implements CommandLineRunner {



    /* TODO
    - enable ships(true)
    - putting works with (A1 B10)
    - wrong ship name -> Not valid ship name
    - input checking for all inputs(what to do with wrong inputs)
    - write tests

     */

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
