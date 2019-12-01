package for_comperhension;

import io.vavr.API;
import io.vavr.collection.Iterator;
import io.vavr.control.Option;

import static io.vavr.API.*;
import static java.lang.String.*;

public class ForExample {
    public static void main(String[] args) {
//        Iterator<String> messages = For(
//                Iterator.tabulate(5, value -> value + 1),
//                Iterator.continually(Math::random),
//                Some("Hello")
//        ).yield((index, random, hello) -> format("Index: %d\tRandom: %s\t Text: %s", index, random, hello));
//
//        messages.take(3).forEach(API::println);
//
//        Iterator<String> monads = For(
//                Some("Some value"),
//                Lazy(() -> "Lazy value"),
//                Right("Correct value")
//        ).yield((some, lazy, right) -> format("Some: %s\tlazy: %s\tright: %s", some, lazy, right));
//
//        System.out.println(monads.head());

        Iterator<String> nothing = For(
                None(),
                Lazy(() -> "Lazy value"),
                Right("Correct value")
        ).yield((some, lazy, right) -> {
            System.out.println("It is not printed :(");
            return format("Some: %s\tlazy: %s\tright: %s", some, lazy, right);});

        nothing.take(1).forEach(API::println);
    }

    static Option<String> getSomeData() {
        return Some("Hello");
    }
}
