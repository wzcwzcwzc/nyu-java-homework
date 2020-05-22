package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public class IceSkater extends WinterSportPlayer {
    private int skateSize;

    public IceSkater(String name, int age, int skateSize) {
        super(name, age);
        this.skateSize = skateSize;
    }

    public int getSkateSize() {
        return skateSize;
    }

    public void setSkateSize(int skateSize) {
        this.skateSize = skateSize;
    }
}
