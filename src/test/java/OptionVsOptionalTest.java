import io.vavr.control.Option;
import org.junit.Test;

import javax.print.DocFlavor;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

public class OptionVsOptionalTest {
    private static final String ERROR_MESSAGE = "Ups coś poszło nie tak ¯\\_(ツ)_/¯";

    private static Person person = Person.builder().age(42).name(null).build();

    @Test
    public void optional() {
        String message = Optional.of(person)
                .map(Person::getName)
                .map(StringsHelper::textLength)
                .map(length -> format("Twoje imie ma %s znaków", length))
                .orElse(ERROR_MESSAGE);

        assertEquals(ERROR_MESSAGE, message);
    }

    @Test
    public void option() {
        String message = Option.of(person)
                .map(Person::getName)
                .map(StringsHelper::textLength)
                .map(length -> format("Twoje imie ma %s znaków", length))
                .getOrElse(ERROR_MESSAGE);

        assertEquals("Twoje imie ma 0 znaków", message);
    }
}