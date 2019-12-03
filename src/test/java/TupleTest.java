import io.vavr.Tuple3;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static io.vavr.API.Tuple;
import static org.junit.Assert.assertEquals;

public class TupleTest {

    @Test
    public void mapAllValues() {
        String welcome = Tuple("  Hello   ", "  World           ")
                .map(hello -> hello.trim(), world -> world.trim())
                .map((hello, world) -> Tuple(hello.toUpperCase(), world.toUpperCase()))
                .apply((hello, world) -> hello + " " + world);

        assertEquals(welcome, "HELLO WORLD");
    }


    @Test
    public void mapSingleValue() {
        String welcome = Tuple("Hello", "World")
                .map1(String::toUpperCase)
                .map2(String::toLowerCase)
                .swap()
                .apply((left, right) -> left + " " + right);

        assertEquals(welcome, "world HELLO");
    }

    @Test
    public void simpleProperties() {
        Tuple3<Integer, Integer, Integer> tuple = Tuple(1, 2, 3);

        assertEquals(tuple._1, Integer.valueOf(1));
        assertEquals(tuple._2(), Integer.valueOf(2));
        assertEquals(tuple.arity(), 3);
        // only Tuple2 has swap() method
        // assertEquals(tuple.swap(), 3);
    }

    @Test
    public void update() {
        Tuple3<Integer, Integer, Integer> original = Tuple(1, 2, 3);

        Tuple3<Integer, Integer, Integer> update1 = original.update1(100);
        assertEquals(update1._1, Integer.valueOf(100));
        assertEquals(update1._2, Integer.valueOf(2));
        assertEquals(update1._3, Integer.valueOf(3));

        assertEquals(original._1, Integer.valueOf(1));
        assertEquals(original._2, Integer.valueOf(2));
        assertEquals(original._3, Integer.valueOf(3));

        Tuple3<Integer, Integer, Integer> allUpdated = original.update1(1000).update2(2000).update3(3000);

        assertEquals(allUpdated._1, Integer.valueOf(1000));
        assertEquals(allUpdated._2, Integer.valueOf(2000));
        assertEquals(allUpdated._3, Integer.valueOf(3000));
    }

    @Test
    public void toJavaTuple() {
        Map.Entry<String, String> javaTupleFromTuple = Tuple("Hello", "World").toEntry();
        AbstractMap.SimpleEntry<String, String> simpleEntry = new AbstractMap.SimpleEntry<>("Hello", "World");

        assertEquals(javaTupleFromTuple, simpleEntry);
    }
}
