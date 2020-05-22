import java.util.concurrent.SynchronousQueue;

public class Square extends Rectangle{

    private double side = 1.0;

    public Square(){}

    public Square(double side) {
        super(side, side);
        this.side = side;
    }

    public Square(double side, String color, boolean filled) {
        super(side, side);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public void setWidth(double side){
        super.setWidth(side);

    }

    @Override
    public void setLength(double side){
        super.setLength(side);
    }

    @Override
    public String toString() {
        return "Square[" +
                "Rectangle[" +
                "Shape[" +
                "color=" + getColor() + "," +
                "filled=" + isFilled() + "]" + "," +
                "width=" + side + "," +
                "length=" + side + "]]";
    }

    // there is no need to rewrite the getArea() and getPerimeter()
}
