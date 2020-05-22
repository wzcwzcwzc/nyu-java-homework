package edu.nyu.cs9053.midterm.hierarchy;

public class SpeedSkater extends IceSkater {

    enum SkateColor{
        RED,YELLOW, BLUE;
    }
    private SkateColor speedSkateColor;

    public SpeedSkater(String name, int age, int skateSize, SkateColor speedSkateColor) {
        super(name, age, skateSize);
        this.speedSkateColor = speedSkateColor;
    }

    public SkateColor getSpeedSkateColor() {
        return speedSkateColor;
    }

    public void setSpeedSkateColor(SkateColor speedSkateColor) {
        this.speedSkateColor = speedSkateColor;
    }

    public boolean equals(IceSkater skater){
        boolean res = false;
        if(this.getClass().equals(skater.getClass())){
            res = super.getName().equals(skater.getName())
                    && super.getAge() == skater.getAge()
                    && super.getSkateSize() == skater.getSkateSize()
                    && this.speedSkateColor.equals(((SpeedSkater)skater).getSpeedSkateColor());
        }
        return res;
    }
}
