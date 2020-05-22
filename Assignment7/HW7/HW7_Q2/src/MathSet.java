import java.util.HashSet;
import java.util.Set;
/**
* @author Barry
* */
public class MathSet<E> extends HashSet<E> {

    public Set<E> intersection(Set<E> s2) {
        MathSet<E> ans = new MathSet<>();
        for(E ele : s2){
            if(this.contains(ele)){
                ans.add(ele);
            }
        }
        return ans;
    }

    public Set<E> union(Set<E> s2) {
        MathSet<E> ans = new MathSet<>();
        ans.addAll(this);
        ans.addAll(s2);
        return ans;
    }

    public <T> Set<Pair<E,T>> cartesianProduct(Set<T> s2) {
        MathSet<Pair<E, T>> ans = new MathSet<>();
        for(E ele : this){
            for(T t : s2){
                ans.add(new Pair(ele, t));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // create two MathSet objects of the same type.
        // See if union and intersection works.
        MathSet<Integer> m1 = new MathSet<>();
        MathSet<Integer> m2 = new MathSet<>();
        m1.add(1);
        m1.add(5);
        m1.add(7);
        m2.add(5);
        m2.add(6);
        m2.add(7);
        m2.add(9);

        Set<Integer> intersectionTest = m1.intersection(m2);
        Set<Integer> unionTest = m1.union(m2);

        // 5, 7
        System.out.println("Test for intersection");
        for(Integer i : intersectionTest){
            System.out.println(i);
        }
        System.out.println("---------------------------");
        // 1 5 6 7 9
        System.out.println("Test for union");
        for(Integer i : unionTest){
            System.out.println(i);
        }


        // create another MathSet object of a different type
        // calculate the cartesian product of this set with one of the
        // above sets
        System.out.println("---------------------------");
        System.out.println("Test for Cartesian product");
        MathSet<Integer> m3 = new MathSet<>();
        m3.add(10);
        m3.add(15);
        MathSet<Pair<Integer, Integer>> cartTest = (MathSet<Pair<Integer, Integer>>) m1.cartesianProduct(m3);
        for(Pair<Integer, Integer> pair : cartTest){
            System.out.println(pair.toString());
        }

        /*
        * Test for Cartesian product
            [Pair: 7, 15]
            [Pair: 1, 15]
            [Pair: 1, 10]
            [Pair: 7, 10]
            [Pair: 5, 15]
            [Pair: 5, 10]
        * */

        System.out.println("Test2 for Cartesian product");
        MathSet<Pair<Integer, Integer>> cartTest2 = (MathSet<Pair<Integer, Integer>>) m3.cartesianProduct(m1);
        for(Pair<Integer, Integer> pair : cartTest2){
            System.out.println(pair.toString());
        }

        /*
        * Test for Cartesian product
            [Pair: 15, 1]
            [Pair: 15, 5]
            [Pair: 10, 5]
            [Pair: 15, 7]
            [Pair: 10, 7]
            [Pair: 10, 1]
        * */
    }
}
