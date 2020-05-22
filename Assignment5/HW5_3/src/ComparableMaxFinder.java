import java.util.ArrayList;
import java.util.List;
/**
 * @author Barry
 * */
public class ComparableMaxFinder<T extends Comparable<T>> {

    private List<T> elementData = new ArrayList<>();

    ComparableMaxFinder(){ }

    public void add(T t){
        elementData.add(t);
    }

    public T max(){
        if(elementData.size() == 0){
            System.out.println("no element in ComparableMaxFinder");
            return null;
        }
        T maxElement = elementData.get(0);
        for(T element : elementData){
            if(maxElement.compareTo(element) < 0){
                maxElement = element;
            }
        }
        return maxElement;
    }
}
