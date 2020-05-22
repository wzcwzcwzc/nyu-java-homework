package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Barry
 */
public class MogulSkier extends Skier {

    private String mogulSkiBootColor;

    public MogulSkier(String name, int age, int skiLength, String mogulSkiBootColor) {
        super(name, age, skiLength);
        this.mogulSkiBootColor = mogulSkiBootColor;
    }

    public String getMogulSkiBootColor() {
        return mogulSkiBootColor;
    }

    public void setMogulSkiBootColor(String mogulSkiBootColor) {
        this.mogulSkiBootColor = mogulSkiBootColor;
    }

    public boolean equals(Skier skier) {
        boolean res = false;
        if (skier.getClass().equals(this.getClass())) {
            res = super.getName().equals(skier.getName())
                    && super.getAge() == skier.getAge()
                    && super.getSkiLength() == skier.getSkiLength()
                    && this.mogulSkiBootColor.equals(((MogulSkier) skier).getMogulSkiBootColor());
        }
        return res;
    }
}
