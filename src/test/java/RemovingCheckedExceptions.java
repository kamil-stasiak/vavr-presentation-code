import io.vavr.API;
import io.vavr.CheckedFunction0;
import io.vavr.Function0;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.function.Supplier;

import static java.util.Calendar.SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RemovingCheckedExceptions {

    public static Supplier<String> standardSupplier = () -> "Hello";

    public static Supplier<String> standardSupplierWithDelay = () -> {
        try {
            Thread.sleep(SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "MESSAGE";
    };

    CheckedFunction0<String> checkedExceptionSupplier = API.CheckedFunction(() -> {
        Thread.sleep(SECOND);
        return "MESSAGE";
    });


    @Test
    // Move try catch one level above
    public void checkedException() {
        try {
            String message = checkedExceptionSupplier.apply();
            assertEquals(message, "MESSAGE");
        } catch (Throwable throwable) {
            fail();
        }
    }

    @Test
    // remove try catch
    public void uncheckedException() {
        checkedExceptionSupplier.unchecked();

        Option<Integer> maybeMessageLength = Option.of(checkedExceptionSupplier)
                .map(CheckedFunction0::unchecked)
                .map(Function0::apply)
                .map(String::length);

        assertEquals(maybeMessageLength.get(), Integer.valueOf("MESSAGE".length()));
    }


}
