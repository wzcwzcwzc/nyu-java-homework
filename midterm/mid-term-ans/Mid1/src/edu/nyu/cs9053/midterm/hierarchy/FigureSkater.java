package edu.nyu.cs9053.midterm.hierarchy;
/**
 * @author Barry
 */
public class FigureSkater extends IceSkater {

    enum FigureSkateColor{
        RED, BLUE, GREEN;
    }
    private FigureSkateColor figureSkateColor;

    FigureSkater(String name, int age, int skateSize, FigureSkateColor figureSkateColor) {
        super(name, age, skateSize);
        this.figureSkateColor = figureSkateColor;
    }

    public FigureSkateColor getFigureSkateColor() {
        return figureSkateColor;
    }

    public void setFigureSkateColor(FigureSkateColor figureSkateColor) {
        this.figureSkateColor = figureSkateColor;
    }

    public boolean equals(IceSkater skater){
        boolean res = false;
        if(this.getClass().equals(skater.getClass())){
            res = super.getName().equals(skater.getName())
                    && super.getAge() == skater.getAge()
                    && super.getSkateSize() == skater.getSkateSize()
                    && figureSkateColor.equals(((FigureSkater)skater).figureSkateColor);
        }
        return res;
    }
}
