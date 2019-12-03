import io.vavr.API;
import io.vavr.CheckedFunction0;
import io.vavr.Lazy;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

import static java.lang.String.format;

public class LazyTest {

    @Test
    public void timeout() {
        Lazy<String> lazy = API.Lazy(() -> {
            try {
                Thread.sleep(3000);
                return "Message";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Message";
            }
        });

        Instant start = Instant.now();
        System.out.println(lazy.get());
        Instant afterFirsCall = Instant.now();
        System.out.println(lazy.get());
        Instant afterSecondCall = Instant.now();

        System.out.println(format("Duration between start and first call: %d milliseconds",
                Duration.between(start, afterFirsCall).toMillis()));
        System.out.println(format("Duration between start and second call: %d milliseconds",
                Duration.between(start, afterSecondCall).toMillis()));
    }

    @Test
    public void timeout2() {
        CheckedFunction0<String> integerCheckedFunction0 = API.CheckedFunction(() -> {
            Thread.sleep(2000);
            return "Message";
        });

        Instant start = Instant.now();
        System.out.println(integerCheckedFunction0.unchecked().apply());
        Instant afterFirsCall = Instant.now();
        System.out.println(integerCheckedFunction0.unchecked().apply());
        Instant afterSecondCall = Instant.now();

        System.out.println(format("Duration between start and first call: %d milliseconds",
                Duration.between(start, afterFirsCall).toMillis()));
        System.out.println(format("Duration between start and second call: %d milliseconds",
                Duration.between(start, afterSecondCall).toMillis()));
    }

}
