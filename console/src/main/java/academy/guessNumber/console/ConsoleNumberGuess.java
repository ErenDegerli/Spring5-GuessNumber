package academy.guessNumber.console;

import academy.guessNumber.IGame;
import academy.guessNumber.IMessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    private final IMessageGenerator messageGenerator;
    private final IGame game;

    @Autowired
    public ConsoleNumberGuess(IMessageGenerator messageGenerator, IGame game) {
        this.messageGenerator = messageGenerator;
        this.game = game;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        logger.info("start() --> Container is ready for use.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
