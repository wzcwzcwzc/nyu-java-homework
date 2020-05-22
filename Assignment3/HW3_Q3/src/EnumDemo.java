/**
 * @author Barry
 * @date 03.06.2020
 * */
public class EnumDemo {

    public static void main(String[] args) {

//        COLORS color = COLORS.RED;
        COLORS red = COLORS.RED;
        COLORS black = COLORS.BLACK;
        COLORS blue = COLORS.BLUE;
        COLORS green = COLORS.GREEN;
        COLORS yellow = COLORS.YELLOW;

        red.printColor(COLORS.RED);
        blue.printColor(COLORS.BLUE);
        green.printColor(COLORS.GREEN);
        yellow.printColor(COLORS.YELLOW);
        black.printColor(COLORS.BLACK);

        red.colorCombo(COLORS.RED);
        blue.colorCombo(COLORS.BLUE);
        green.colorCombo(COLORS.GREEN);
        yellow.colorCombo(COLORS.YELLOW);
        black.colorCombo(COLORS.BLACK);
    }
}
