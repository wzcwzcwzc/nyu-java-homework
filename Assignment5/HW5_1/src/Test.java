/**
 * @author Barry
 * */
public class Test {
    // Main method
    public static void main(String[] args) {
        // Create two comparable circles
        Circle1 circle1 = new Circle1(6);
        Circle1 circle2 = new Circle1(4);
        Rectangle1 rectangle1 = new Rectangle1(3,4);
        Rectangle1 rectangle2 = new Rectangle1(4,5);
        // Display the max circle
        Circle1 circle = (Circle1)GeometricObject1.max(circle1, circle2);
        System.out.println("The max circle's radius is " + circle.getRadius());
        System.out.println(circle);

        Rectangle1 rectangle = (Rectangle1) GeometricObject1.max(rectangle1, rectangle2);
        System.out.println("The max rectangle's length is " + rectangle.getLength() + " and" +
                "the width is " + rectangle.getWidth());
        System.out.println(rectangle);
    }
}
