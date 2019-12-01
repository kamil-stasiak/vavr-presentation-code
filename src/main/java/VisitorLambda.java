import lombok.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Value class Circle {final double radius;}

@Value class Square { final double side;}

@Value class Rectangle {final double weidht;final double height;}

class LambdaVisitor<A> implements Function<Object, A> {

    private Map<Class<?>, Function<Object, A>> fMap = new HashMap<>();

    <B> LambdaVisitor<A> on(Class<B> clazz, Function<B, A> f) {
        fMap.put(clazz, (Function<Object, A>) f);
        return this;
    }

    @Override
    public A apply(Object o) {
        return fMap.get(o.getClass()).apply(o);
    }
}

public class VisitorLambda {
    static Function<Object, Double> areaVisitor = new LambdaVisitor<Double>()
            .on(Square.class, s -> s.getSide() * s.getSide())
            .on(Circle.class, c -> Math.PI * c.getRadius() * c.getRadius())
            .on(Rectangle.class, r -> r.getHeight() * r.getWeidht());

    static Function<Object, Double> perimeterVisitor = new LambdaVisitor<Double>()
            .on(Square.class, s -> 4 * s.getSide())
            .on(Circle.class, c -> 2 * Math.PI * c.getRadius())
            .on(Rectangle.class, r -> 2 * r.getHeight() + 2 * r.getWeidht());

    public static void main(String[] args) {
        List<Object> figures = Arrays.asList(new Circle(4), new Square(5), new Rectangle(6, 7));

        double totalArea = figures.stream().map(areaVisitor).reduce(0.0, Double::sum);
        System.out.println("Total area = " + totalArea);

        double totalPerimeter = figures.stream().map(perimeterVisitor).reduce(0.0, Double::sum);
        System.out.println("Total perimeter = " + totalPerimeter);
    }
}
