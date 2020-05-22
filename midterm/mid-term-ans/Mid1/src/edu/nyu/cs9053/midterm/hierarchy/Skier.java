package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public class Skier extends WinterSportPlayer {

    private int skiLength;

    Skier(String name, int age, int skiLength) {
        super(name, age);
        this.skiLength = skiLength;
    }

    public int getSkiLength() {
        return skiLength;
    }

    public void setSkiLength(int skiLength) {
        this.skiLength = skiLength;
    }
}
