import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class RuntimeException1 extends RuntimeException {
}

class RuntimeException2 extends RuntimeException {
}

public class TryTest {

    @Test
    public void defaultMessageForAllExceptions() {
        Try<String> failure = Try.of(this::alwaysErrorMethod)
                .map(String::toUpperCase)
                .recover(exception -> {
                    Void of = Match(exception).of(
                            Case($(instanceOf(RuntimeException1.class)), o -> run(() -> logError(o))),
                            Case($(instanceOf(RuntimeException1.class)), o -> run(() -> logError(o)))
                    );
                    return "Default message for all exceptions";
                });

        assertTrue(failure.isSuccess());
    }

    @Test
    public void test2() {
        Try<String> failure = Try.of(this::alwaysErrorMethod)
                .map(String::toUpperCase)
                .recover(exception -> Match(exception).of(
                        Case($(instanceOf(RuntimeException1.class)), e -> {
                            run(() -> logError(e));
                            return "Runtime Exception 1";
                        }),
                        Case($(instanceOf(RuntimeException2.class)), e -> {
                            run(() -> logError(e));
                            return "Runtime Exception 1";
                        })
                ));

        assertTrue(failure.isSuccess());
    }

    @Test
    public void tesOk() {
        Try<String> success = Try.of(this::alwaysOK)
                .map(String::toUpperCase)
                .recover(exception -> Match(exception).of(
                        Case($(instanceOf(RuntimeException1.class)), e -> {
                            run(() -> logError(e));
                            return "Runtime Exception 1";
                        }),
                        Case($(instanceOf(RuntimeException2.class)), e -> {
                            run(() -> logError(e));
                            return "Runtime Exception 1";
                        })
                ));

        assertTrue(success.isSuccess());
        assertEquals(success.get(), "MESSAGE");
    }

    private void logError(RuntimeException e) {
        System.out.println("Some error occurred" + e);
    }

    private String alwaysErrorMethod() {
        throw new RuntimeException1();
    }

    private String alwaysOK() {
        return "message";
    }
}
