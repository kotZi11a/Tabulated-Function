package functions.basic;

import functions.Function;

public class TrigonometricFunction implements Function {

    public double getLeftDomainBorder() {
        return Double.MIN_VALUE;
    }


    public double getRightDomainBorder() {
        return Double.MAX_VALUE;
    }


    public double getFunctionValue(double x) {

        return 0;
    }
}
