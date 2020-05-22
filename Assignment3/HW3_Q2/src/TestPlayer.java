/**
 * @author Barry
 * @date 03.06.2020
 * */
public class TestPlayer {

    public static void main(String[] args) {
        Player p1 = new Player("barry", "nyu", "1");
        Player p2 = new Player("barry", "nyu", "1");
        Player p3 = new Player("barry", "nyu", "2");

        PlayerCompare pc = new PlayerCompare();
        /* 1. the result should be true but return false,
              because the reference of object is not the same.

           2. after rewrite the equal method in Player,
              we compare the content of Player class then we can get what we want.
        */

        // the following statement get True, False, False
        System.out.println(pc.compare(p1, p2));
        System.out.println(pc.compare(p1, p3));
        System.out.println(pc.compare(p2, p3));
    }
}
