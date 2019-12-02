import io.vavr.API;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.API.Right;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNull;

public class ForComperhensionTest {

    @Test
    public void simpleMonads() {
        Iterator<String> monads = For(
                Some("Some value"),
                Lazy(() -> "Lazy value"),
                Right("Correct value")
        ).yield((some, lazy, right) -> format("Some: %s\tlazy: %s\tright: %s", some, lazy, right));

        assertThat(monads.toJavaList(), hasSize(1));
    }

    @Test
    public void noneOption() {
        Iterator<String> empty = For(
                None(),
                Lazy(() -> "Lazy value"),
                Right("Correct value")
        ).yield((some, lazy, right) -> format("Some: %s\tlazy: %s\tright: %s", some, lazy, right));

        assertNull(empty.singleOption().getOrNull());
    }

    @Test
    public void leftEither() {
        Iterator<String> empty = For(
                Some("Some value"),
                Lazy(() -> "Lazy value"),
                Left("Incorrect value")
        ).yield((some, lazy, right) -> format("Some: %s\tlazy: %s\tright: %s", some, lazy, right));

        assertNull(empty.singleOption().getOrNull());
    }

    @Test
    public void supplier5Elements() {
        List<String> messages = For(
                Iterator.tabulate(5, value -> value + 1),
                Some("Hello")
        ).yield((index, hello) -> format("Index: %d\tText: %s", index, hello))
                .toList();

        assertThat(messages.toJavaList(), hasSize(5));
        messages.forEach(API::println);
    }

    @Test
    public void cartesianJoin() {
        List<String> messages = For(
                List("A1", "A2"),
                List("B1", "B2"),
                List("C1", "C2")
        ).yield((a, b, c) -> format("%s %s %s", a, b, c)).toList();

        messages.forEach(API::println);
        assertThat(messages.toJavaList(), hasSize(2 * 2 * 2));
    }

    @Test
    public void forComperhensionCode() {
        List<String> messages = Iterator.ofAll(List("A1", "A2")).flatMap((a) -> {
            return Iterator.ofAll(List("B1", "B2")).flatMap((b) -> {
                return Iterator.ofAll(List("C1", "C2")).map((c) -> {
                    return format("%s %s %s", a, b, c);
                });
            });
        }).toList();

        messages.forEach(API::println);
        assertThat(messages.toJavaList(), hasSize(2 * 2 * 2));
    }
}
