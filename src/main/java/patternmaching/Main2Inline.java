package patternmaching;

import io.vavr.Predicates;
import io.vavr.collection.List;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

// https://www.baeldung.com/vavr-pattern-matching
// https://static.javadoc.io/io.vavr/vavr/0.9.2/io/vavr/Predicates.html
// Tutaj chciałbym pokazać zalety tej konstrukcji więc korzystam z vavrowych struktura danych

public class Main2Inline {
    public static void main(String[] args) {
//        Predicates.allOf();
//        Predicates.anyOf();
//        Predicates.exists();
//        Predicates.forAll((e) -> true);
//        Predicates.instanceOf()
//        Predicates.is()
//        Predicates.isIn()
//        Predicates.isNotNull()
//        Predicates.isNull()
//        Predicates.noneOf()

//        List<Integer> list = List.of(2, 4, 6, 8);
        List<Integer> list = List.of(1, 4, 3, 5, 7);

        // expression - returns a value
        String result1 = Match(list).of(
                Case($(List::isEmpty), "Empty"),
                Case($(forAll(Numbers::isOdd)), "All odds"),
                Case($(forAll(Numbers::isEven)), "All evens"),
                Case($(Main2Inline::oneOddOneEvenAndSmallerThen11), "At least one odd and one even and every number is smaller than 11"),
                Case($(), "Default"));

        String result2 = "Default";
        if (list.isEmpty()) {
            result2 = "Emmpty";
        } else if (list.forAll(Numbers::isOdd)) {
            result2 = "All odds";
        } else if (list.forAll(Numbers::isEven)) {
            result2 = "All evens";
        } else if (oneOddOneEvenAndSmallerThen11(list)) {
            result2 = "At least one odd and one even and every number is smaller than 11";
        }

        System.out.println(result1);
        System.out.println(result2);
    }

    private static void javaList() {
        java.util.List<Integer> list = Arrays.asList(1, 2, 3);
        boolean result = list.stream().allMatch(Numbers::isEven); // forAll in pure Java
    }

    private static boolean oneOddOneEvenAndSmallerThen11(List<Integer> list) {
        return exists(Numbers::isEven).test(list)
                && exists(Numbers::isOdd).test(list)
                && forAll(((Integer e) -> e <= 10)).test(list);
    }

    private static String sad(Integer number) {
//        if((number == 3) || (number == 6) || (number == 9)) {
        if (List.of(3, 6, 9).contains(number)) {
            return "1";
        } else if (number == 1) {
            return "2";
        } else {
            return "3";
        }
    }

    private static String happy(Integer number) {
        return Match(number).of(
                Case($(isIn(3, 6, 9)), "1"),
                Case($(is(1)), "2"),
                Case($(), "3"));
    }
}
