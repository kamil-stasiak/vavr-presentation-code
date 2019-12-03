import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurringTest {

    public static Function3<String, Double, Integer, String> function3 =
            (message, floating, number)-> String.format("%s, %s, %s", message, floating, number);

    @Test
    public void test() {
        String hello_world = function3.apply("Hello world", -3.1, 42);
        assertEquals(hello_world, "Hello world, -3.1, 42");
    }

    @Test
    public void test2() {
        Function1<String, Function1<Double, Function1<Integer, String>>> curriedString = function3.curried();
        Function1<Double, Function1<Integer, String>> curriedDouble = curriedString.apply("Curried");
        Function1<Integer, String> curriedInteger = curriedDouble.apply(23.5);
        String curried1 = curriedInteger.apply(10);
        assertEquals(curried1, "Curried, 23.5, 10");

        String curried2 = function3.curried().apply("Curried").apply(23.5).apply(10);
        assertEquals(curried2, "Curried, 23.5, 10");
    }

    @Test
    public void test3() {
        Function2<String, String, String> greetings = (text, name) -> text + ", " + name;
        assertEquals(greetings.apply("Hello", "World!"), "Hello, World!");
        assertEquals(greetings.apply("Hi", "Bob!"), "Hi, Bob!");

        Function1<String, String> sayHello = greetings.apply("Hello");
        assertEquals(sayHello.apply("Bob!"), "Hello, Bob!");
        assertEquals(sayHello.apply("Mark!"), "Hello, Mark!");
    }
}
