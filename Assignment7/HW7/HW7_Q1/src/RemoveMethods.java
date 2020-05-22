import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * @author Barry
 * */
public class RemoveMethods {

    public static void remAllStack(Stack<Object> stack, Object item) {
        Stack<Object> tempStack = new Stack<>();
        while(!stack.isEmpty()){
            Object val = stack.pop();
            if(!item.equals(val)){
                tempStack.push(val);
            }
        }

        while(!tempStack.isEmpty()){
            Object val = tempStack.pop();
            stack.push(val);
        }
    }

    public static void remAllQueue(Queue<Object> queue, Object item) {

        Queue<Object> tempQueue = new LinkedList<>();
        while(!queue.isEmpty()){
            Object val = queue.poll();
            if(!item.equals(val)){
                tempQueue.add(val);
            }
        }

        while(!tempQueue.isEmpty()){
            Object val = tempQueue.poll();
            queue.add(val);
        }
    }

    public static void main(String[] args) {
        Stack<Object> stk = new Stack<Object>();
        stk.push(new Integer(24));
        stk.push(new Integer(2));
        stk.push(new Integer(9));
        stk.push(new Integer(2));
        stk.push(new Integer(7));
        stk.push(new Integer(10));
        stk.push(new Integer(16));
        System.out.println("begin: stk is " + stk);
        RemoveMethods.remAllStack(stk, new Integer(2));
        System.out.println("end: stk is " + stk);
        RemoveMethods.remAllStack(stk, new Integer(24));
        System.out.println("end: stk is " + stk);

        Queue<Object> q = new LinkedList<>(); // you should probably find a concrete class for this
        q.offer(new Integer(24));
        q.offer(new Integer(2));
        q.offer(new Integer(9));
        q.offer(new Integer(2));
        q.offer(new Integer(7));
        q.offer(new Integer(10));
        q.offer(new Integer(16));
        System.out.println("begin: q is " + q);
        RemoveMethods.remAllQueue(q, new Integer(2));
        System.out.println("end: q is " + q);
        RemoveMethods.remAllQueue(q, new Integer(24));
        System.out.println("end: q is " + q);

    }
}
