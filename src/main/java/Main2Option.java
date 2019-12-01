import io.vavr.collection.List;

import java.util.Optional;

public class Main2Option {

    private static Person person = Person.builder().age(42).name(null).build();

    public static void main(String[] args) {
        List.of(1, 2 ,3).equals(List.of(1, 2, 3));
    }
}
