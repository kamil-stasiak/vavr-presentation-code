import io.vavr.Function2;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LiftingTest {

    public static Function2<Integer, Integer, Double> division = (dividend, divisor) -> (double) (dividend / divisor);

    @Test(expected = ArithmeticException.class)
    public void withRuntimeException() {
        division.apply(10, 0);
    }

    @Test
    public void liftOption() {
        Option<Double> optionEmpty = Function2.lift(division).apply(10, 0);
        assertTrue(optionEmpty.isEmpty());

        Option<Double> optionSome = Function2.lift(division).apply(10, 5);
        assertTrue(optionSome.isDefined());
    }
    
    @Test
    public void liftTry() {
        Try<Double> tryFalure = Function2.liftTry(division).apply(10, 0);
        assertTrue(tryFalure.isFailure());
        assertTrue(tryFalure.isEmpty());

        Try<Double> trySuccess = Function2.liftTry(division).apply(10, 2);
        assertTrue(trySuccess.isSuccess());
    }
}
