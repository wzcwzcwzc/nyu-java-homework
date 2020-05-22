/**
 * @author Barry
 * */
abstract class GeometricObject1 implements Comparable {

    abstract int compareTo(GeometricObject1 go1);

    static GeometricObject1 max(GeometricObject1 go1, GeometricObject1 go2){
        if(go1.compareTo(go2) > 0){
            return go1;
        }else if(go1.compareTo(go2) == 0){
            System.out.println("two shapes are the same, just return one of them");
            return go2;
        }else{
            return go2;
        }
    }
}
