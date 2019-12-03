package gof2lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LambdaVisitor<A> implements Function<Object, A> {

    private Map<Class<?>, Function<Object, A>> fMap = new HashMap<>();

    public <B> LambdaVisitor<A> on(Class<B> clazz, Function<B, A> f) {
        fMap.put(clazz, (Function<Object, A>) f);
        return this;
    }

    @Override
    public A apply(Object o) {
        return fMap.get(o.getClass()).apply(o);
    }
}
