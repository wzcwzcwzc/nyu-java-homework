/**
 * @author Barry
 * */
public class Rectangle1 extends GeometricObject1{

    private double length;
    private double width;

    public Rectangle1(){}

    public Rectangle1(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    int compareTo(GeometricObject1 go1) {
        Rectangle1 r = (Rectangle1) go1;
        return Double.compare(this.length * this.width, r.length * r.width);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
