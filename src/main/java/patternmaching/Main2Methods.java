package patternmaching;

import io.vavr.collection.List;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

public class Main2Methods {
    public static void main(String[] args) {
//        List<Integer> list = List.of(2, 4, 6, 8);
        List<Integer> list = List.of(1, 4, 3, 5, 7);

        String result1 = machNumbersPM(list);
        System.out.println(result1);
        String result2 = matchNumbers1(list);
        System.out.println(result2);
    }

    private static String matchNumbers1(List<Integer> list) {
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
        return result2;
    }

    // else if is shorter than many if
    private static String matchNumbers3(List<Integer> list) {
        if (list.isEmpty()) {
            return "Emmpty";
        }
        if (list.forAll(Numbers::isOdd)) {
            return "All odds";
        }
        if (list.forAll(Numbers::isEven)) {
            return "All evens";
        }
        if (oneOddOneEvenAndSmallerThen11(list)) {
            return "At least one odd and one even and every number is smaller than 11";
        }
        return "Default";
    }

    private static String matchNumbers2(List<Integer> list) {
        if (list.isEmpty()) {
            return "Emmpty";
        } else if (list.forAll(Numbers::isOdd)) {
            return "All odds";
        } else if (list.forAll(Numbers::isEven)) {
            return "All evens";
        } else if (oneOddOneEvenAndSmallerThen11(list)) {
            return "At least one odd and one even and every number is smaller than 11";
        }
        return "Default";
    }

    private static String machNumbersPM(List<Integer> list) {
        return Match(list).of(
                Case($(List::isEmpty), "Empty"),
                Case($(forAll(Numbers::isOdd)), "All odds"),
                Case($(forAll(Numbers::isEven)), "All evens"),
                Case($(Main2Methods::oneOddOneEvenAndSmallerThen11), "At least one odd and one even and every number is smaller than 11"),
                Case($(), "Default"));
    }

    private static void main2() {
        java.util.List<Integer> list = Arrays.asList(1, 2, 3);
        boolean result = list.stream().allMatch(Numbers::isEven);
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
