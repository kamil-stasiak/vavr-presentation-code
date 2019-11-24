package patternmaching;

import io.vavr.collection.List;
import patternmaching.domain.GG;
import patternmaching.domain.Mail;
import patternmaching.domain.Message;
import patternmaching.domain.Phone;

import static io.vavr.API.*;

// Pattern matching
// Należy podjąć decyzję na podstawie wzorca - typu albo danych
// Compile time: switch-case is good enough
// Runtime: bunch of if-else statement
// 1. this file - Match().of() - Case($())
// 2. Predicates
// 3. Scala like destructing (magic)

public class Main1 {
    public static void main(String[] args) {
        final List<Message> messages = List.of(
                new Message("+48123456789", "Message 1"), // phone
                new Message("example@mail.com", "Message 2"), // mail
                new Message("example@mail.com", "Message 3"),  // mail
                new Message("+48123456789", "Message 4"), // phone
                new Message("17242367", "Message 5"), // gg
                new Message("example@mail.com", "Message 6"),  // mail
                new Message("17242367", "Message 5"), // gg
                new Message("+48123456789", "Message 7")); // phone

        System.out.println("Phones: " + messages.groupBy(Main1::messagesToCategory).get("PHONE"));
        System.out.println("Phones: " + messages.groupBy(Main1::messagesToCategoryPM).get("PHONE"));
    }

    private static String messagesToCategory(Message e) {
        if (Phone.validate(e.getSource())) {
            return "PHONE";
        }
        if (Mail.validate(e.getSource())) {
            return "MAIL";
        }
        if (GG.validate(e.getSource())) {
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
