package patternmaching.domain;

public class Phone {
    public static boolean validate(String text) {
        return text.startsWith("+48");
    }
}
