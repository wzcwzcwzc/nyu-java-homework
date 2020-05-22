/**
 * @author Barry
 * @date 03.06.2020
 * */
public enum COLORS {
    /**
     * define the enum type COLORS
     */
    BLACK, BLUE, GREEN, RED, YELLOW;

    ColorPrinter cp;

    COLORS(){
         cp = new ColorPrinter();
    }

    public String printColor(COLORS color){
        switch (color){
            case BLUE:
                display("display BLUE");
                return "BLUE";
            case BLACK:
                display("display BLACK");
                return "BLACK";
            case GREEN:
                display("display GREEN");
                return "GREEN";
            case RED:
                display("display RED");
                return "RED";
            case YELLOW:
                display("display YELLOW");
                return "YELLOW";
            default:
                return "Illegal color";
        }
    }

    public void display(String color){
        System.out.println(color);
    }

    public void colorCombo(COLORS color){
        cp.colorCombo(color);
    }
}
