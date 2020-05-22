import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javafx.util.Pair;

public class ResultPrinter {

	MathOperation op;
	
	public ResultPrinter(MathOperation op) {
		this.op = op;
	}
	
	public void go(double a, double b) {
		System.out.println("result is " + op.operation(a, b));
	}

	public static void go(double a, double b, MathOperation op) {
		 System.out.println("result is " + op.operation(a, b));
	}
	
	public static void go(Collection<Pair<Double,Double>> c, MathOperation op) {

		for (Pair<Double, Double> pair : c) {
			System.out.println("result is " + op.operation(pair.getKey(), pair.getValue()));
		}
	}


	
	public static void main(String[] args) {

		/*
		* pass simple add MathOperation to constructor test
		* */
		MathOperation op = new MathOperation() {
			@Override
			public double operation(double a, double b) {
				return a + b;
			}
		};
		ResultPrinter rp = new ResultPrinter(op);
		rp.go(3.0, 4.0);

		/*
		* pass Lambda to static go method does multiplication test
		* */
		ResultPrinter.go(4.0, 2.0, (a, b) -> (a * b));

		/*
		* pass collections and math operation to go method to do division
		* */
		ArrayList<Pair<Double,Double>> al = new ArrayList<Pair<Double,Double>>();
		Pair<Double, Double> p = new Pair<Double, Double>(3.0, 4.0);
		al.add(p);
		p = new Pair<Double, Double>(5.0, 6.0);
		al.add(p);
		p = new Pair<Double, Double>(7.0, 8.0);
		al.add(p);
		ResultPrinter.go(al, (a, b) -> (a / b));
	}
}
