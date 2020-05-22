import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * @author Barry
 * */
public class ReverseMap {

    public static Map<Object, Set<Object>> getInverted(Map<Object, Object> mp) {
        Map<Object, Set<Object>> ans = new HashMap<>();
        for(Map.Entry<Object, Object> entry : mp.entrySet()){
            Object key = entry.getValue();
            Object val = entry.getKey();
            if(ans.containsKey(key)){
                Set<Object> temp = ans.get(key);
                temp.add(val);
                ans.put(key, temp);
            }else{
                Set<Object> set = new HashSet<>();
                set.add(val);
                ans.put(key, set);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Map<Object,Object> m = new HashMap<Object,Object>();
        m.put("Key1", new Integer(2));
        m.put("Key2", new Integer(5));
        m.put("Key4", new Integer(2));
        m.put("Key5", new Integer(8));
        m.put("Key6", new Integer(18));
        m.put("Key7", new Integer(24));
        System.out.println("hashmap is " + m);
        System.out.println("inverted: " + ReverseMap.getInverted(m));

        /*
        * Output:
        * hashmap is {Key2=5, Key1=2, Key6=18, Key5=8, Key4=2, Key7=24}
          inverted: {2=[Key1, Key4], 18=[Key6], 5=[Key2], 8=[Key5], 24=[Key7]}
        *
        * */

    }
}
