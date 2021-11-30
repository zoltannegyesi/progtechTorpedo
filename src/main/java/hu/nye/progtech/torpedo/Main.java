package hu.nye.progtech.torpedo;

import hu.nye.progtech.torpedo.service.game.GameController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class, to run the program.
 */

public class Main {



    /* TODO
    - if wrong input on shooting, can shoot again without ai shooting
    - shooting out of range should not work
    - shooting on 10 should work
    - wrong shipname -> Not valid ship name
    - shouldn't put , after last ship
    - input checking for all inputs(what to do with wrong inputs)
    - write out if an input is wrong(putting)
    - write tests
    - work with db (save, get)

     */

    /**
     * Run the program,
     * and create the ApplicationContext for Spring.
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("hu.nye.progtech.torpedo");
        GameController gameController = context.getBean(GameController.class);
        gameController.start();
    }
}
