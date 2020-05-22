/**
 * @author Barry
 * */
public class Complex implements Cloneable {

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
        if(this.img == 0){
            return String.valueOf(this.real);
        }
        return this.real + " + " + this.img + "i";
    }
}
