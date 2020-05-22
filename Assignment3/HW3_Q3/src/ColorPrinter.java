/**
 * @author Barry
 * @date 03.06.2020
 */
public class ColorPrinter extends AbstractColorPrinter {

    @Override
    public void colorCombo(COLORS color){
        switch (color){
            case YELLOW:
                System.out.println("Primary color");
                break;
            case RED:
                System.out.println("Primary color");
                break;
            case BLUE:
                System.out.println("Primary color");
                break;
            case GREEN:
                System.out.println("Formed by combination of yellow and blue");
                break;
            case BLACK:
                System.out.println("Formed by combination of red yellow and blue");
                break;
            default:
                System.out.println("invalid color");
        }
    }
}
