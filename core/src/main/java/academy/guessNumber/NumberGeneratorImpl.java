package academy.guessNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class NumberGeneratorImpl implements INumberGenerator{

    // == Fields ==
    private final Random random = new Random();

    private final int maxNumber;
    private final int minNumber;

    // == Constructors ==

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == Public Methods ==
    @Override
    public int next() {
        //return random.nextInt(maxNumber);
        //return random.nextInt(maxNumber - minNumber) + minNumber;
        return random.ints(minNumber, maxNumber + 1).findFirst().getAsInt();
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
