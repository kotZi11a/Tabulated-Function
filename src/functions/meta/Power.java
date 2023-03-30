package functions.meta;

import functions.Function;

public class Power implements Function {

    Function func;
    double deg;

    public Power(Function fun, double degree){
        func = fun;
    }

    public double getLeftDomainBorder() {
        return func.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return func.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return Math.pow(func.getFunctionValue(x), deg);
    }
}
