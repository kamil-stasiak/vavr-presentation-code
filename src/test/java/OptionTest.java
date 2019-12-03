import io.vavr.API;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.Iterator;

import static io.vavr.API.*;
import static org.junit.Assert.assertTrue;

public class OptionTest {

    @Test
    public void listOfOptionToOption() {
        List<Option<String>> list = List(Some("Hello"), Some("World"), Some("Bob"));
        Option<Seq<String>> optionOfMessages = Option.sequence(list);
        assertTrue(optionOfMessages.isDefined());
        optionOfMessages.forEach(API::println);
    }

    @Test
    public void listOfSomeAndNoneToOptionNone() {
        List<Option<String>> list = List(Some("Hello"), None(), None(), Some("World"), Some("Bob"));
        Option<Seq<String>> empty = Option.sequence(list);
        assertTrue(empty.isEmpty());
        empty.forEach(API::println);
    }

    @Test
    public void peekOnSome() {
        Option<String> some = Option.of("Message").peek(API::println);
        assertTrue(some.isDefined());
    }

    @Test
    public void peekOnNone() {
        Option<Object> none = None().peek(API::println);
        assertTrue(none.isEmpty());
    }

    @Test
    public void listOfOption() {
        List<Option<?>> list = List(Some(42), Some(24), None());
        list.forEach(API::println);
        List<?> objects = list.flatMap(o -> o);
        objects.forEach(API::println);
//        System.out.println(None().iterator().hasNext());
    }
}
