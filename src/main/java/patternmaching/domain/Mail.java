package patternmaching.domain;

public class Mail {
    public static boolean validate(String text) {
        return text.contains("@");
    }
}
