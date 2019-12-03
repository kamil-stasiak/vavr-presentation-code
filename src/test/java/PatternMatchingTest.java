import io.vavr.collection.List;
import org.junit.Test;
import patternmaching.Numbers;
import patternmaching.domain.GG;
import patternmaching.domain.Mail;
import patternmaching.domain.Message;
import patternmaching.domain.Phone;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;
import static io.vavr.Predicates.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

// Pattern matching
// Należy podjąć decyzję na podstawie wzorca - typu albo danych
// Compile time: switch-case is good enough
// Runtime: bunch of if-else statement
// 1. this file - Match().of() - Case($())
// 2. Predicates
// 3. Scala like destructing (magic)

public class PatternMatchingTest {

    @Test
    public void test1() {
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
        } else if (oneOddOneEvenAndSmallerThan11(list)) {
            result2 = "At least one odd and one even and every number is smaller than 11";
        }
        return result2;
    }

    // else if is shorter than many if
    private static String matchNumbers2(List<Integer> list) {
        if (list.isEmpty()) {
            return "Emmpty";
        }
        if (list.forAll(Numbers::isOdd)) {
            return "All odds";
        }
        if (list.forAll(Numbers::isEven)) {
            return "All evens";
        }
        if (oneOddOneEvenAndSmallerThan11(list)) {
            return "At least one odd and one even and every number is smaller than 11";
        }
        return "Default";
    }

    private static String matchNumbers3(List<Integer> list) {
        if (list.isEmpty()) {
            return "Emmpty";
        } else if (list.forAll(Numbers::isOdd)) {
            return "All odds";
        } else if (list.forAll(Numbers::isEven)) {
            return "All evens";
        } else if (oneOddOneEvenAndSmallerThan11(list)) {
            return "At least one odd and one even and every number is smaller than 11";
        }
        return "Default";
    }

    private static String machNumbersPM(List<Integer> list) {
        return Match(list).of(
                Case($(List::isEmpty), "Empty"),
                Case($(forAll(Numbers::isOdd)), "All odds"),
                Case($(forAll(Numbers::isEven)), "All evens"),
                Case($(PatternMatchingTest::oneOddOneEvenAndSmallerThan11), "At least one odd and one even and every number is smaller than 11"),
                Case($(), "Default"));
    }

    private static void filterInPureJava() {
        java.util.List<Integer> list = Arrays.asList(1, 2, 3);
        boolean result = list.stream().allMatch(Numbers::isEven);
    }

    private static boolean oneOddOneEvenAndSmallerThan11(List<Integer> list) {
        return exists(Numbers::isEven).test(list)
                && exists(Numbers::isOdd).test(list)
                && forAll(((Integer e) -> e <= 10)).test(list);
    }
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

    private static String happy(Integer number) {
        return Match(number).of(
                Case($(isIn(3, 6, 9)), "1"),
                Case($(is(1)), "2"),
                Case($(), "3"));
    }

    final List<Message> messages = List.of(
            new Message("+48123456789", "Message 1"), // phone
            new Message("example@mail.com", "Message 2"), // mail
            new Message("example@mail.com", "Message 3"),  // mail
            new Message("+48123456789", "Message 4"), // phone
            new Message("17242367", "Message 5"), // gg
            new Message("example@mail.com", "Message 6"),  // mail
            new Message("17242367", "Message 5"), // gg
            new Message("+48123456789", "Message 7")); // phone


    @Test
    public void test3() {
        List<Message> phonesIfElse = messages.groupBy(PatternMatchingTest::messagesToCategory).get("PHONE").get().toList();
        System.out.println("Phones: " + phonesIfElse);
        List<Message> phonePatternMatching = messages.groupBy(PatternMatchingTest::messagesToCategoryPM).get("PHONE").get().toList().toList();
        System.out.println("Phones: " + phonePatternMatching);

        assertThat(phonesIfElse.toJavaList(), hasSize(3));
        assertThat(phonePatternMatching.toJavaList(), hasSize(3));
    }

    private static String messagesToCategory(Message e) {
        if (Phone.validate(e.getSource())) {
            return "PHONE";
        } else if (Mail.validate(e.getSource())) {
            return "MAIL";
        } else if (GG.validate(e.getSource())) {
            return "GG";
        }
        return "OTHER";
    }

    private static String messagesToCategoryPM(Message e) {
        return Match(e.getSource()).of(
                Case($(Phone::validate), "PHONE"),
                Case($(Mail::validate), "MAIL"),
                Case($(GG::validate), "GG"),
                Case($(), "OTHER"));
    }
}
