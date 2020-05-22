package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public class Test {
    public static void main(String[] args) {
        Luger l1 = new Luger("A", 20, Luger.Pattern.LARGE);
        Luger l2 = new Luger("A", 20, Luger.Pattern.MEDIUM);
        Luger l3 = new Luger("A", 20, Luger.Pattern.LARGE);
        Luger l4 = new Luger("B", 20, Luger.Pattern.LARGE);
        System.out.println("Test for Luger:");
        System.out.println(l1.equals(l2)); //false
        System.out.println(l1.equals(l3)); //true
        System.out.println(l1.equals(l4)); //false
        System.out.println("-----------------------------");

        Curler c1 = new Curler("A", 25, 10);
        Curler c2 = new Curler("A", 25, 10);
        Curler c3 = new Curler("B", 25, 10);
        System.out.println("Test for Curler:");
        System.out.println(c1.equals(c2)); //true
        System.out.println(c2.equals(c3)); //false
        System.out.println("-----------------------------");

        Bobsledder b1 = new Bobsledder("C", 22, "Red", 10.5);
        Bobsledder b2 = new Bobsledder("C", 22, "Red", 10);
        Bobsledder b3 = new Bobsledder("C", 22, "Red", 10.5);
        System.out.println("Test for Bobsledder:");
        System.out.println(b1.equals(b2)); // false;
        System.out.println(b1.equals(b3)); // true
        System.out.println("-----------------------------");

        SpeedSkater s1 = new SpeedSkater("D", 10, 10, SpeedSkater.SkateColor.BLUE);
        SpeedSkater s2 = new SpeedSkater("D", 10, 10, SpeedSkater.SkateColor.YELLOW);
        SpeedSkater s3 = new SpeedSkater("D", 10, 10, SpeedSkater.SkateColor.BLUE);
        System.out.println("Test for SpeedSkater:");
        System.out.println(s1.equals(s2)); //false
        System.out.println(s1.equals(s3)); // true
        System.out.println("-----------------------------");

        FigureSkater f1 = new FigureSkater("E", 20,20, FigureSkater.FigureSkateColor.GREEN);
        FigureSkater f2 = new FigureSkater("E", 20, 20, FigureSkater.FigureSkateColor.RED);
        FigureSkater f3 = new FigureSkater("E", 20, 20, FigureSkater.FigureSkateColor.RED);
        System.out.println("Test for FigureSkater:");
        System.out.println(f1.equals(f2)); // false
        System.out.println(f2.equals(f3)); // true
        System.out.println("-----------------------------");


        CrossCountrySkier cc1 = new CrossCountrySkier("F", 25,25, "RED");
        CrossCountrySkier cc2 = new CrossCountrySkier("F", 25,25, "RED");
        CrossCountrySkier cc3 = new CrossCountrySkier("F", 25, 25, "YELLOW");
        System.out.println("Test for CrossCountrySkier:");
        System.out.println(cc1.equals(cc2)); //true
        System.out.println(cc1.equals(cc3)); //false;
        System.out.println("-----------------------------");

        MogulSkier m1 = new MogulSkier("G", 23, 10, "BLUE");
        MogulSkier m2 = new MogulSkier("G", 25, 10, "BLUE");
        MogulSkier m3 = new MogulSkier("G", 23,10,"BLUE");
        System.out.println("Test for MogulSkier:");
        System.out.println(m1.equals(m2)); // false
        System.out.println(m1.equals(m3)); // true
        System.out.println("-----------------------------");
    }
}
