import io.vavr.API;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.junit.Test;

import static io.vavr.API.*;
import static org.junit.Assert.assertEquals;

public class OptionTupleTest {

    @Test
    public void basicExample() {
        Option<String> tuple = Option.of(Tuple("Hello", "World"))
                .map(e -> e._1 + " " + e._2);

        assertEquals(tuple.get(), "Hello World");
    }

    @Test
    public void fromOptionTupleToOptionTuple() {
        Tuple2<String, String> tuple = Option.of(Tuple("Hello", "World"))
                .map(e -> Tuple(e._1.toUpperCase(), e._2.toUpperCase()))
                .get();

        assertEquals(tuple, API.Tuple("HELLO", "WORLD"));
    }

    @Test
    public void mappingWithHelperMethod() {
        Tuple2<String, String> tuple = Option.of(Tuple("Hello", "World"))
                .map(e -> e.map((hello, world) -> Tuple(hello.toUpperCase(), world.toUpperCase())))
                .map(Tuples.tuple((hello, world) -> Tuple(hello.toUpperCase(), world.toUpperCase())))
                .get();

        assertEquals(tuple, API.Tuple("HELLO", "WORLD"));
    }
}
