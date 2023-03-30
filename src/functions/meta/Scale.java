package functions.meta;

import functions.Function;

public class Scale implements Function {

    Function func;
    double x,y;


    public Scale(Function fun, double xx, double xy){
        x = xx;
        y = xy;
        func = fun;
    }

    public double getLeftDomainBorder() {
        return func.getLeftDomainBorder() * x;
    }


    public double getRightDomainBorder() {
        return func.getRightDomainBorder() * x;
    }


    public double getFunctionValue(double x) {
        return func.getFunctionValue(x) * y;
    }
}
