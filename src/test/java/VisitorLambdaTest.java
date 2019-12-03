import gof2lambda.Circle;
import gof2lambda.LambdaVisitor;
import gof2lambda.Rectangle;
import gof2lambda.Square;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class VisitorLambdaTest {

    static Function<Object, Double> areaVisitor = new LambdaVisitor<Double>()
            .on(Square.class, s -> s.getSide() * s.getSide())
            .on(Circle.class, c -> Math.PI * c.getRadius() * c.getRadius())
            .on(Rectangle.class, r -> r.getHeight() * r.getWidth());

    static Function<Object, Double> perimeterVisitor = new LambdaVisitor<Double>()
            .on(Square.class, s -> 4 * s.getSide())
            .on(Circle.class, c -> 2 * Math.PI * c.getRadius())
            .on(Rectangle.class, r -> 2 * r.getHeight() + 2 * r.getWidth());


    @Test
    public void visitorTest() {
        List<Object> figures = Arrays.asList(new Circle(4), new Square(5), new Rectangle(6, 7));

        double totalArea = figures.stream().map(areaVisitor).reduce(0.0, Double::sum);
        System.out.println("Total area = " + totalArea);

        double totalPerimeter = figures.stream().map(perimeterVisitor).reduce(0.0, Double::sum);
        System.out.println("Total perimeter = " + totalPerimeter);
    }

}