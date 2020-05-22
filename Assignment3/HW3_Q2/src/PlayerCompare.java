/**
 * @author Barry
 * @date 03.06.2020
 * */
public class PlayerCompare{

    PlayerCompare(){}

    public boolean compare(Player p1, Player p2){
        if(p1.equals(p2)){
            System.out.println("same player");
            return true;
        }else{
            System.out.println("different players");
            return false;
        }
    }
}