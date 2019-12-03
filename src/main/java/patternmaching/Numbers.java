package patternmaching;

public class Numbers {
    public static boolean isOdd(Integer number) {
        return !isEven(number);
    }

    public static boolean isEven(Integer number) {
        return number % 2 == 0;
    }
}
