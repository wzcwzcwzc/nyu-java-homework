/**
 * @author Barry
 * */
public class Complex extends Number implements Comparable<Number>, Cloneable {

    private double real;
    private double img;

    Complex(){
        this.real = 0;
        this.img = 0;
    }

    Complex(double a){
        this.real = a;
        this.img = 0;
    }

    Complex(double a, double b){
        this.real = a;
        this.img = b;
    }

    @Override
    public int intValue() {
        return (int) this.abs();
    }

    @Override
    public long longValue() {
        return (long) this.abs();
    }

    @Override
    public float floatValue() {
        return (float) this.abs();
    }

    @Override
    public double doubleValue() {
        return this.abs();
    }

    public Complex add(Complex c){
        return new Complex(this.real + c.real, this.img + c.img);
    }

    public Complex subtract(Complex c){
        return new Complex(this.real - c.real, this.img - c.img);
    }

    public Complex multiply(Complex c){
        return new Complex(this.real * c.real - this.img * c.img, this.img * c.real + this.real * c.img);
    }

    public Complex divide(Complex c){
        return new Complex((this.real * c.real + this.img * c.img)/ (Math.pow(c.real, 2) + Math.pow(c.img, 2)),
                (this.img * c.real - this.real * c.img) / (Math.pow(c.real, 2) + Math.pow(c.img, 2)));

    }

    public double getRealPart(){
        return this.real;
    }

    public double getImaginaryPart(){
        return this.img;
    }

    public double abs(){
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.img, 2));
    }

    @Override
    public Complex clone(){
        Complex c = null;
        try{
            // use deep copy
            c = (Complex) super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public String toString(){
        return this.real + " + " + this.img + "i";
    }

    @Override
    public int compareTo(Number o) {
        if(o instanceof Double){
            return Double.compare(this.doubleValue(), o.doubleValue());
        }else if(o instanceof Integer){
            return Integer.compare(this.intValue(), o.intValue());
        }else if(o instanceof Float){
            return Float.compare(this.floatValue(), o.floatValue());
        }else if(o instanceof Long){
            return Long.compare(this.longValue(), o.longValue());
        }else if(o instanceof Complex){
            if(this.abs() < ((Complex) o).abs()){
                return -1;
            }else if(this.abs() == ((Complex) o).abs()){
                return 0;
            }else{
                return 1;
            }
        }
        // invalid
        return Integer.MIN_VALUE;
    }
}
