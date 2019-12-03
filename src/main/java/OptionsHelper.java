import java.util.Optional;

import static java.util.Objects.isNull;

public class OptionsHelper {
    public static Integer textLength(String text) {
        return Optional.ofNullable(text)
                .map(String::length)
                .orElse(0);
    }

    public static Integer textLength2(String text) {
        if (isNull(text)) {
            return 0;
        }
        return text.length();
    }

    public static String formatMessage(Integer integer) {
        return "Twoje imię ma " +  integer + " znaków";
    }
}
