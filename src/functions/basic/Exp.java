package functions.basic;

import functions.Function;

public class Exp implements Function {

    public double getLeftDomainBorder() {
        return Double.MIN_VALUE;
    }


    public double getRightDomainBorder() {
        return Double.MIN_VALUE;
    }


    public double getFunctionValue(double x) {
        return Math.exp(x);
    }
}
