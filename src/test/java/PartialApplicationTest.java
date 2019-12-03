import io.vavr.Function1;
import io.vavr.Function2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialApplicationTest {
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
