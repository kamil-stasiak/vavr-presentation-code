import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple2;

public class Tuples {
    public static <T1, T2, G1, G2> Function1<Tuple2<T1, T2>, Tuple2<G1, G2>> tuple(Function2<T1, T2, Tuple2<G1, G2>> function) {
        return (Tuple2<T1, T2> tuple) -> tuple.map(function);
    }
}
