import io.vavr.API;
import io.vavr.control.Try;

import java.io.*;

import static io.vavr.API.Failure;
import static io.vavr.API.Success;

public class TrySerializator2 {

    public static <T> Try<T> serialize(String filename, T object) {
        return Try.of(() -> {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(object);

            out.close();
            file.close();
            return object;
        });
    }

    public static <T> Try<T> deserialize(String filename, Class<T> clazz) {
        return Try.of(() -> {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            T object = (T) in.readObject();

            in.close();
            file.close();

            return object;
        });
    }
}