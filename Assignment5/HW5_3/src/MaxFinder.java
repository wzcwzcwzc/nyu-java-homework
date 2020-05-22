import java.util.ArrayList;
import java.util.List;
/**
 * @author Barry
 * */
public class MaxFinder<T> {

    private List<T> elementData = new ArrayList<>();

    MaxFinder(){ }

    public void add(T t){
        elementData.add(t);
    }

    public T max(){
        if(elementData.size() == 0){
            System.out.println("no element in MaxFinder");
            return null;
        }
        T maxElement = elementData.get(0);
        for(T element : elementData){
            if(element instanceof Integer){
                if((int) maxElement < (int) element) {
                    maxElement = element;
                }
            }else if(element instanceof Double){
                if(Double.compare((double) maxElement, (double) element) < 0){
                    maxElement = element;
                }
            }else if(element instanceof Float){
                if(Float.compare((float) maxElement, (float) element) < 0){
                    maxElement = element;
                }

            }else if(element instanceof Long){
                if((long) maxElement < (long) element){
                    maxElement = element;
                }
            }
        }
        return maxElement;
    }
}
