import java.util.Optional;

public class StringsHelper {
    public static Integer textLength(String text) {
        return Optional.ofNullable(text)
                .map(String::length)
                .orElse(0);
    }
}
