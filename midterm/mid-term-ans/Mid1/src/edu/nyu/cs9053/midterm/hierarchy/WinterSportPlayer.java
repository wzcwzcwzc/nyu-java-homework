package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public abstract class WinterSportPlayer {
    private String name;
    private int age;

    WinterSportPlayer(){ }

    WinterSportPlayer(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
