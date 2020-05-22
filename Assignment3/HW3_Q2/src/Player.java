/**
 * @author Barry
 * @date 03.06.2020
 * */
public class Player {

    private String name;
    private String team;
    private String num;

    Player(String name, String team, String num){
        this.name = name;
        this.team = team;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean equals(Player p){
        return (this.name.equals(p.name) && this.team.equals(p.team)
        && this.num.equals(p.num));
    }
}