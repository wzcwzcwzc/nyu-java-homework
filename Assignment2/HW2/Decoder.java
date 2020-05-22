import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Zancheng Wang
// zw2316@nyu.edu
// Assignment #2
// 12 Feb 2020
// 2 hours
//
// This program can decode users‘ input

class Decoder{

    // use HEX_MAP to store the <key, value> of hexadecimal
    private static final Map<Integer, String> HEX_MAP;
    static{
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "0");
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(7, "7");
        map.put(8, "8");
        map.put(9, "9");
        map.put(10, "a");
        map.put(11, "b");
        map.put(12, "c");
        map.put(13, "d");
        map.put(14, "e");
        map.put(15, "f");
        HEX_MAP = Collections.unmodifiableMap(map);
    }

    public static String getBin(int num){
        return Integer.toBinaryString(num);
    }

    public static String getOctal(int num){
        return Integer.toOctalString(num);
    }

    public static String getHex(int num){
        if (num == 0) return "0";
        StringBuilder res = new StringBuilder();

        // keep using last 4 bits of binary string to form res in hexadecimal when num < 0
        if (num < 0){
            while (num != 0){
                int x = num & 0xf;
                res.append(HEX_MAP.get(x));
                num >>>= 4;
            }
        }else{
            while (num != 0) {
                int x = num % 16;
                res.append(HEX_MAP.get(x));
                num = num / 16;
            }
        }
        return res.reverse().toString();
    }

    public static String getHexAtri(int num){
        return Integer.toString(num, 36);
    }

    public static String findNonAscii(String str){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) >= 128){
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }

    public static int[] findUnicode(String str){
        int[] uni_num = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            uni_num[i] = str.charAt(i);
        }
        return uni_num;
    }

    public static void mode1(String inputNum){
        System.out.println("the binary of the number is: " + getBin(Integer.parseInt(inputNum)));
        System.out.println("the octal of the number is: " + getOctal(Integer.parseInt(inputNum)));
        System.out.println("the hexadecimal of the number is: " + getHex(Integer.parseInt(inputNum)));
        System.out.println("the hexatridecimal of the number is: " + getHexAtri(Integer.parseInt(inputNum)));
        System.out.println("");
    }

    public static void mode2(String target, String inputNum){
        int input = Integer.parseInt(inputNum);
        if("binary".equals(target)){
            for(int i = 1; i <= input; i++){
                System.out.println("binary of " + i + ": " + getBin(i));
            }
        }else if("octal".equals(target)){
            for(int i = 1; i <= input; i++){
                System.out.println("octal of " + i + ": " + getOctal(i));
            }
        }else if("hexadecimal".equals(target)){
            for(int i = 1; i <= input; i++){
                System.out.println("hexadecimal of " + i + ": " + getHex(i));
            }
        }else if("hexatridecimal".equals(target)){
            for(int i = 1; i <= input; i++){
                System.out.println("hexatridecimal of " + i + ": " + getHexAtri(i));
            }
        }else{
            System.out.println("invalid target");
        }
    }

    public static void mode3(String str){
        String non_ascii = findNonAscii(str);
        int[] uni_num = findUnicode(non_ascii);
        System.out.println("the non_ascii characters: " + non_ascii);
        System.out.println("the represent of these characters in unicode: ");
        for (int value : uni_num) {
            System.out.print(" " + value);
        }
        System.out.println("");
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please choose the mode (1, 2, 3), enter 0 to exit: ");
        while(input.hasNext()){
            String mode = input.nextLine();
            if("1".equals(mode)){
                System.out.println("you choose the mode 1 (convert base 10 number to binary, octal, hexadecimal and hexatridecimal)");
                System.out.println("Please enter your number: ");
                mode1(input.nextLine());
            }else if("2".equals(mode)){
                System.out.println("you choose the mode 2 (target, base)");
                System.out.println("Please enter your target (binary, octal, hexadecimal or hexatridecimal): ");
                String target = input.nextLine().trim();
                System.out.println("Please enter your number: ");
                String inputNum = input.nextLine().trim();
                mode2(target, inputNum);
            }else if("3".equals(mode)){
                System.out.println("you choose the mode 3 (String ascii to unicode)");
                System.out.println("Please enter a string: ");
                String str = input.nextLine();
                mode3(str);
            }else if("0".equals(mode)){
                System.out.println("Bye :)");
                System.exit(0);
            }else{
                System.out.println("Wrong Input format");
            }
            System.out.println("Please choose the mode (1, 2, 3), enter 0 to exit: ");
        }
    }
}