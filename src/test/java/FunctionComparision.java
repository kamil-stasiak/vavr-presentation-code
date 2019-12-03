import io.vavr.Function1;
import org.junit.Test;

public class FunctionComparision {
    @Test
    public void test() {
        Function1<String, Integer> length = String::length;
        Function1<String, Integer> length2 = String::length;
    }
}
