import java.io.*;

public class Exercise2 {

    public static void main(String[] args) throws IOException{

        int n = 10;
        int[] v = new int[n];
        FileReader f = null;
        BufferedReader in = null;

        try{
            f = new FileReader("dati.txt");
            in = new BufferedReader(f);
            int i = 0;
            String linea = in.readLine();
            while (linea != null) {
                v[i] = Integer.parseInt(linea);
                linea = in.readLine();
                i++;
            }
        }catch(IndexOutOfBoundsException e) {
            System.err.print("Caught IndexOutOfBoundException: ");
            e.printStackTrace();
        }catch (NumberFormatException e){
            System.err.print("Caught NumberFormatException: ");
            e.printStackTrace();
        }catch(IOException e){
            System.err.print("Caught IOException: ");
            e.printStackTrace();
        }finally{
            if(f != null){
                f.close();
            }else{
                System.out.println("FileReader not open");
            }
            if(in != null){
                in.close();
            }else{
                System.out.println("BufferReader not open");
            }
        }

        for(int val : v){
            System.out.println(val);
        }
    }
}
