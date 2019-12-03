import io.vavr.API;
import io.vavr.control.Try;
import org.junit.Test;
import serializer.TrySerializator;
import serializer.TrySerializator2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrySerializatorTest {
    @Test
    public void serializeTest() {
        Try<String> message = TrySerializator.serialize("abc.txt", "Message");
        assertTrue(message.isSuccess());

        Try<String> deserialize = TrySerializator.deserialize("abc.txt", String.class);
        assertTrue(deserialize.isSuccess());

        assertEquals(deserialize.get(), "Message");
    }

    @Test
    public void serializeTest2() {
        Try<String> message = TrySerializator2.serialize("abc.txt", "Message");
        assertTrue(message.isSuccess());

        Try<String> deserialize = TrySerializator2.deserialize("abc.txt", String.class);
        assertTrue(deserialize.isSuccess());

        assertEquals(deserialize.get(), "Message");
    }

    @Test
    public void serializeTest2Failure() {
        Try<Person> message = TrySerializator2.serialize("abc.txt", new Person(10, "Bob"));
        assertTrue(message.isFailure());
    }

    @Test
    public void serializeTest2Monads() {
        TrySerializator2.serialize("abc.txt", new Person(10, "Bob"))
                .flatMap((serialized) -> TrySerializator2.deserialize("abc.txt", Person.class))
                .map(deserialized -> new Person(deserialized.getAge() + 1, "Alice"))
                .forEach(API::println);
    }
}
