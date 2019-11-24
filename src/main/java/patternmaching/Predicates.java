package patternmaching;

import java.util.function.Predicate;

class Predicates {
    static <T> Predicate<T> not(Predicate<T> predicate) {
        return (T e) -> !predicate.test(e);
    }
}
