package functions.basic;

import functions.Function;

public class Log  implements Function {


    public double getLeftDomainBorder() {
        return Double.MIN_VALUE;
    }


    public double getRightDomainBorder() {
        return Double.MAX_VALUE;
    }


    public double getFunctionValue(double x) {
        return Math.log(x);
    }
}
