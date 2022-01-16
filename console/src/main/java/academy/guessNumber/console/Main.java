package academy.guessNumber.console;

import academy.guessNumber.config.GameConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(com.sun.tools.javac.Main.class);
    //private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        logger.info("Guess The Number Game");

        // create the context(container)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        // close context (container) effectively
        context.close();

    }
}
