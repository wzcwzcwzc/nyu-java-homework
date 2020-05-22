package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Barry
 */
public class CrossCountrySkier extends Skier {

    private String crossCountrySkiColor;

    CrossCountrySkier(String name, int age, int skiLength, String crossCountrySkiColor){
        super(name, age, skiLength);
        this.crossCountrySkiColor = crossCountrySkiColor;
    }

    public String getCrossCountrySkiColor() {
        return crossCountrySkiColor;
    }

    public void setCrossCountrySkiColor(String crossCountrySkiColor) {
        this.crossCountrySkiColor = crossCountrySkiColor;
    }

    public boolean equals(Skier skier) {
        boolean res = false;
        if (skier.getClass().equals(this.getClass())) {
            res = super.getName().equals(skier.getName())
                    && super.getAge() == skier.getAge()
                    && super.getSkiLength() == skier.getSkiLength()
                    && this.crossCountrySkiColor.equals(((CrossCountrySkier) skier).getCrossCountrySkiColor());
        }
        return res;
    }
}
