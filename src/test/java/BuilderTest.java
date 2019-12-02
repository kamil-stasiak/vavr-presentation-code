import io.vavr.control.Option;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import org.junit.Test;

@Builder
@Value
@ToString
class Person2 {
    String name;
    String surname;
    String age;
}


public class BuilderTest {
    @Test
    public void test() {
        Option.of(Person2.builder())
                .map(e -> e.age("12"))
                .map(e -> e.name("Imie"))
                .map(e -> e.surname("Nazwisko"))
                .map(Person2.Person2Builder::build)
                .forEach(System.out::println);
    }
}
