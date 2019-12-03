import io.vavr.API;
import io.vavr.control.Try;

import java.io.*;

import static io.vavr.API.Failure;
import static io.vavr.API.Success;

public class TrySerializator {

    public static <T> Try<T> serialize(String filename, T object) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(object);
            out.close();
            file.close();

            return API.Success(object);
        } catch (IOException ex) {
            return Failure(ex);
        }
    }

    public static <T> Try<T> deserialize(String filename, Class<T> clazz) {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            T object = (T) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println(object);
            return Success(object);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IOException is caught");
            return Failure(ex);
        }
    }
}

//        Try.of(() -> {
//                FileOutputStream file = new FileOutputStream(filename);
//                ObjectOutputStream out = new ObjectOutputStream(file);
//
//                out.writeObject(object);
//                out.close();
//                file.close();
//                return object;
//                })
