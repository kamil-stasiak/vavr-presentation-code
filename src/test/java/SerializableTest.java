import io.vavr.control.Option;
import lombok.Value;
import org.junit.Test;

import java.io.NotSerializableException;
import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Value
class Message implements Serializable {
    String text;
}

@Value
class Message2 {
    String text;
}

public class SerializableTest {

    @Test
    public void serializeSome() {
        String filename = "file.ser";
        SerializeUtils.serialize(filename, Option.of("Message"));
        Option deserialize = SerializeUtils.deserialize(filename, Option.class);
        assertTrue(deserialize.isDefined());
    }

    @Test
    public void serializeNone() {
        String filename = "file.ser";
        SerializeUtils.serialize(filename, Option.none());
        Option deserialize = SerializeUtils.deserialize(filename, Option.class);
        assertTrue(deserialize.isEmpty());
    }

    @Test
    public void serializeSerializable() {
        String filename = "file.ser";
        SerializeUtils.serialize(filename, Option.of(new Message("message text")));
        Option<Message> deserialize = SerializeUtils.deserialize(filename, Option.class);
        assertTrue(deserialize.isDefined());
        assertEquals(deserialize.get().getText(), "message text");
    }

    @Test(expected = NullPointerException.class)
    public void serializeNotSerializable() {
        String filename = "file.ser";
        SerializeUtils.serialize(filename, Option.of(new Message2("message text")));
        Option<Message> deserialize = SerializeUtils.deserialize(filename, Option.class);
        assertTrue(deserialize.isDefined());
        assertEquals(deserialize.get().getText(), "message text");
    }
}
