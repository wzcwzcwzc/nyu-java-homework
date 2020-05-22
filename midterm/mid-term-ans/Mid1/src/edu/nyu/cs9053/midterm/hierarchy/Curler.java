package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public class Curler extends WinterSportPlayer{

    private double brushLength;

    public Curler(String name, int age, double brushLength) {
        super(name, age);
        this.brushLength = brushLength;
    }

    public double getBrushLength() {
        return brushLength;
    }

    public void setBrushLength(double brushLength) {
        this.brushLength = brushLength;
    }

    public boolean equals(WinterSportPlayer sp){
        boolean res = false;
        if(sp.getClass().equals(this.getClass())){
            res =  this.brushLength == ((Curler) sp).getBrushLength()
                    && super.getName().equals(sp.getName())
                    && super.getAge() == sp.getAge();
        }
        return res;
    }
}
