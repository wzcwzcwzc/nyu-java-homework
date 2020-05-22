/**
 * @author Barry
 * */
public class Circle1 extends GeometricObject1 {

    private double radius;

    Circle1(){}

    public Circle1(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    int compareTo(GeometricObject1 go1) {
        Circle1 c = (Circle1) go1;
        return Double.compare(this.radius, c.radius);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
