package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public class Sledder extends WinterSportPlayer {

    private String sledderColor;

    public Sledder(String name, int age, String sledderColor) {
        super(name, age);
        this.sledderColor = sledderColor;
    }

    public String getSledderColor() {
        return sledderColor;
    }

    public void setSledderColor(String sledderColor) {
        this.sledderColor = sledderColor;
    }
}
