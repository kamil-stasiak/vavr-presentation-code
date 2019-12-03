import io.vavr.Function1;
import io.vavr.Function3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Currying is the process of transforming a function that takes multiple arguments in a tuple as its argument,
// into a function that takes just a single argument and returns another function which accepts further arguments,
// one by one, that the original function would receive in the rest of that tuple.
public class CurringTest {

    public static Function3<String, Double, Integer, String> function3 =
            (message, floating, number)-> String.format("%s, %s, %s", message, floating, number);

    @Test
    public void standardUsage() {
        String hello_world = function3.apply("Hello world", -3.1, 42);
        assertEquals(hello_world, "Hello world, -3.1, 42");
    }

    @Test
    public void curringFunction3() {
        Function1<String, Function1<Double, Function1<Integer, String>>> curriedString = function3.curried();
        Function1<Double, Function1<Integer, String>> curriedDouble = curriedString.apply("Curried");
        Function1<Integer, String> curriedInteger = curriedDouble.apply(23.5);
        String curried1 = curriedInteger.apply(10);
        assertEquals(curried1, "Curried, 23.5, 10");

        String curried2 = function3.curried().apply("Curried").apply(23.5).apply(10);
        assertEquals(curried2, "Curried, 23.5, 10");
    }
}
