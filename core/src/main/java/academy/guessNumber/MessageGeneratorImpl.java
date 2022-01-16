package academy.guessNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements IMessageGenerator{

    private static final Logger logger = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final IGame game;

    @Autowired
    public MessageGeneratorImpl(IGame game) {
        this.game = game;
    }

    @PostConstruct
    public void postLog() {

       logger.info("game = {}", game);
    }

    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() + " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        int number = game.getNumber();
        if(game.isGameWon()) {
            return "You guessed it! The number was " + number;
        }else if(game.isGameLost()) {
            return "You lost! The number was " + number;
        }else if(!game.isValidNumberRange()) {
            return "Invalid number range! You have " + game.getRemainingGuesses() + " guesses left!";
        }else if(game.getRemainingGuesses() == game.getGuessCount()) {
            return "What is your first guess?";
        }else {
            String direction = "Lower";

            if(game.getGuess() < number) {
                direction = "Higher";
            }

            return direction + "! You have " + game.getRemainingGuesses() + " guesses left!";
        }
    }
}
