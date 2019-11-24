package patternmaching;

class Numbers {
    static boolean isOdd(Integer number) {
        return !isEven(number);
    }

    static boolean isEven(Integer number) {
        return number % 2 == 0;
    }
}
