import io.vavr.control.Option;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

class OptionTest {

    private static final String ERROR_MESSAGE = "Ups coś poszło nie tak ¯\\_(ツ)_/¯";

    private static Person person = Person.builder().age(42).name(null).build();



    @Test
    @DisplayName("Optional :(")
    void personAgeLength() {
        String message = Optional.of(person)
                .map(Person::getName)
                .map(OptionsHelper::textLength) // null is OK
                .map(OptionsHelper::formatMessage)
                .orElse(ERROR_MESSAGE);

        assertEquals("Twoje imie ma 0 znaków", message);
    }

    @Test
    @DisplayName("Option :)")
    void personAgeLengthOption() {
        String message = Option.of(person)
                .map(Person::getName)
                .map(OptionsHelper::textLength) // null is OK
                .map(OptionsHelper::formatMessage)
                .getOrElse(ERROR_MESSAGE);

        assertEquals("Twoje imie ma 0 znaków", message);
    }
}