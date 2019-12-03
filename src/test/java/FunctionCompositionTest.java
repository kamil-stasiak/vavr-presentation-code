import io.vavr.API;
import io.vavr.Function1;
import io.vavr.control.Option;
import lombok.Value;
import org.junit.Test;

import java.util.function.Function;

import static java.util.Objects.nonNull;

@Value
class PersonWithHands {
    Hand left;
    Hand right;
}

@Value
class Hand {
    Finger thumb;
    Finger index;
    Finger middle;
    Finger right;
    Finger little;
}

@Value
class Finger {
    Nail nail;
}

@Value
class Nail {
    String text;
}

public class FunctionCompositionTest {

    public static PersonWithHands person = new PersonWithHands(new Hand(new Finger(new Nail("OK")), new Finger(new Nail("")), new Finger(new Nail("")), new Finger(new Nail("")), new Finger(new Nail(""))), new Hand(null, null, null, null, null));

    @Test
    public void leftHandDig() {
        String orNull = Option.of(person)
                .map(PersonWithHands::getLeft)
                .map(Hand::getThumb)
                .map(Finger::getNail)
                .map(Nail::getText)
                .getOrNull();

        System.out.println(orNull);
    }

    @Test
    public void leftHandDig2() {
        Function1<PersonWithHands, Hand> getHand = PersonWithHands::getLeft;
        Function1<PersonWithHands, String> getNailText = getHand.andThen(Hand::getThumb).andThen(Finger::getNail).andThen(Nail::getText);

        String orNull = Option.of(person).map(getNailText).getOrNull();

        System.out.println(orNull);
    }

    @Test
    public void rightHandDig() {
        String orNull = Option.of(person)
                .flatMap(this::maybe)
                .map(PersonWithHands::getRight)
                .flatMap(this::maybe)
                .map(Hand::getThumb)
                .flatMap(this::maybe)
                .map(Finger::getNail)
                .flatMap(this::maybe)
                .map(Nail::getText)
                .flatMap(this::maybe)
                .getOrNull();

        System.out.println(orNull);
    }

    private <T> Option<T> maybe(T e) {
        return nonNull(e)
                ? API.Some(e)
                : API.None();
    }

    @Test
    public void rightHandDigBetterFlatMap() {
        String orNull = Option.of(person)
                .flatMap(option(PersonWithHands::getRight))
                .flatMap(option(Hand::getThumb))
                .flatMap(option(Finger::getNail))
                .flatMap(option(Nail::getText))
                .getOrNull();

        System.out.println(orNull);
    }

    private <T1, T2> Function<T1, Option<T2>> option(Function<T1, T2> fun) {
        return (input) -> nonNull(input)
                ? Option.some(fun.apply(input))
                : Option.none();
    }

    @Test
    public void rightHandDigBetterFlatMap2() {
        String orNull = Option.of(person)
                .flatMap(this::getText)
                .getOrNull();

        System.out.println(orNull);
    }

    private Option<String> getText(PersonWithHands person) {
        return Option.of(person)
                .flatMap(option(PersonWithHands::getRight))
                .flatMap(option(Hand::getThumb))
                .flatMap(option(Finger::getNail))
                .flatMap(option(Nail::getText));
    }

}
