public class Test {

    public static void main(String[] args) {
        MaxFinder m = new MaxFinder<Number>();
        MaxFinder ml = new MaxFinder<Number>();
        MaxFinder mf = new MaxFinder<Number>();

        ComparableMaxFinder cm = new ComparableMaxFinder();
        MaxFinder mm = new MaxFinder<Integer>();

        Complex c1 = new Complex(10,10);
        Complex c2 = new Complex(10, 3);
        Complex c3 = new Complex(10, 5);

        Integer i1 = new Integer(10);
        Integer i2 = new Integer(11);
        Integer i3 = new Integer(10);

        Double d1 = new Double(10.5);
        Double d2 = new Double(15.2);
        Double d3 = new Double(8.7);

        Long l1 = new Long(100);
        Long l2 = new Long(200);

        Float f1 = new Float(10.2);
        Float f2 = new Float(11.2);

        cm.add(c1);
        cm.add(c2);
        cm.add(c3);

        m.add(d1);
        m.add(d2);
        m.add(d3);

        mm.add(i1);
        mm.add(i2);
        mm.add(i3);

        ml.add(l1);
        ml.add(l2);

        mf.add(f1);
        mf.add(f2);

        Complex maxComplex = (Complex) cm.max();
        Double maxDouble = (Double) m.max();
        Integer maxInt = (Integer) mm.max();
        Float maxFloat = (Float) mf.max();
        Long maxLong = (Long) ml.max();

        System.out.println("the max complex by using comparableMaxFinder is " + maxComplex);
        System.out.println("the max double by using MaxFinder<Number> is " + maxDouble);
        System.out.println("the max Integer by using MaxFinder<Integer> is " + maxInt);
        System.out.println("the max float by using MaxFinder<Number> is " + maxFloat);
        System.out.println("the max Long by using MaxFinder<Number> is " + maxLong);
    }
}
