import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Barry
 * @date 03.11.20
 * */
public class Matrix {

    private int n;
    private int m;
    private double[][] matrix;

    Matrix(int n, int m){
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    // save content to a file

    public void save(String fileName) throws IOException {
        double[][] m = this.matrix;
        FileWriter myFile = null;
        // there is no need for us to catch NullPointerException
        try{
            myFile = new FileWriter(fileName);
            for (double[] row : m) {
                for (int j = 0; j < m[0].length; j++) {
                    myFile.write(row[j] + " ");
                }
                myFile.write("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }finally {
            if(myFile != null){
                myFile.close();
            }else{
                System.out.println("FileWriter is not open");
            }
        }
    }

    public static Matrix read(String fileName) throws IOException {
        // create matrix and return
        BufferedReader reader = null;
        List<List<String>> mat = new ArrayList<>();
        Matrix ans = new Matrix(0, 0);

        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            if("".equals(line)){
                throw new WrongMatrixValuesException("the matrix value is empty");
            }else{
                int size = line.split(" ").length;
                while(line != null){
                    // do string split and store it into matrix
                    String[] temp = line.split(" ");
                    List<String> row = new ArrayList<>(Arrays.asList(temp));
                    // if the row of data is not following value or dimension
                    for(String str : temp){
                        if(!str.matches("[-+]?\\d*\\.?\\d+")){
                            throw new WrongMatrixValuesException("the matrix value is invalid");
                        }
                    }
                    if(row.size() != size){
                        throw new WrongMatrixDimensionException("the dimension is invalid(not aligned)");
                    }
                    mat.add(row);
                    line = reader.readLine();
                }

                int row = mat.size();
                int col = mat.get(0).size();
                Matrix mm = new Matrix(mat.size(), mat.get(0).size());
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        mm.matrix[i][j] = Double.parseDouble(mat.get(i).get(j));
                    }
                }
                ans = mm;
            }
        } catch (IOException | WrongMatrixValuesException | WrongMatrixDimensionException e){
            e.printStackTrace();
            System.exit(0);
        }finally {
            if(reader != null){
                reader.close();
            }else{
                System.out.println("the BufferedReader is not opened");
            }
        }
        return ans;
    }

    public Matrix sum(Matrix mat){
        // this.matrix sum with m
        Matrix ans = new Matrix(mat.n, mat.m);
        try{
            if(this.n != mat.n || this.m != mat.m ){
                throw new WrongMatrixDimensionException("the dimension of two matrix is wrong, they cannot add");
            }
            double[][] mm = new double[this.n][this.m];
            for(int i = 0; i < this.n; i++){
                for(int j = 0; j < this.m; j++){
                    mm[i][j] = this.matrix[i][j] + mat.matrix[i][j];
                }
            }
            ans.matrix = mm;
        }catch(WrongMatrixDimensionException e){
            e.printStackTrace();
            System.exit(0);
        }
        return ans;
    }

    public Matrix product(Matrix mat){
        // m*n mat (n * m)
        Matrix ans = new Matrix(this.n, mat.m);
        try{
            if(this.m != mat.n){
                throw new WrongMatrixDimensionException("the dimension of two matrix is wrong, they cannot multiple");
            }
            double[][] mm = new double[this.n][mat.m];
            for(int i = 0; i < this.n; i++) {
                for (int j = 0; j < mat.m; j++) {
                    for (int k = 0; k < this.m; k++) {
                        mm[i][j] += this.matrix[i][k] * mat.matrix[k][j];
                    }
                }
            }
            ans.matrix = mm;
            return ans;
        }catch(WrongMatrixDimensionException e){
            e.printStackTrace();
            System.exit(0);
        }
        return ans;
    }

    public void printMatrix(Matrix mat){
        double[][] mm = mat.matrix;
        for (double[] row : mm) {
            for (int j = 0; j < mm[0].length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws IOException {

        Matrix m1 = read("m1.txt");
        Matrix m2 = read("m2.txt");

        // test sum
//        Matrix ans = m1.sum(m2);
//        ans.save("m3.txt");

        //test multiply
        Matrix ans = m1.product(m2);
        ans.save("m3.txt");
    }
}
