package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 * */
public class Luger extends WinterSportPlayer {
    enum Pattern{
        SMALL, MEDIUM, LARGE;
    }
    private Pattern trouserPattern;

    public Luger(String name, int age, Pattern trouserPattern) {
        super(name, age);
        this.trouserPattern = trouserPattern;
    }

    public Pattern getTrouserPattern() {
        return trouserPattern;
    }

    public void setTrouserPattern(Pattern trouserPattern) {
        this.trouserPattern = trouserPattern;
    }

    public boolean equals(WinterSportPlayer sp){
        boolean res = false;
        if(sp.getClass().equals(this.getClass())){
            res =  this.trouserPattern.equals(((Luger) sp).getTrouserPattern())
                    && super.getName().equals(sp.getName())
                    && super.getAge() == sp.getAge();
        }
        return res;
    }
}
