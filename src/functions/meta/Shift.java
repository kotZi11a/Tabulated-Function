package functions.meta;

import functions.Function;

public class Shift implements Function {

    Function func;
    double x,y;

    public Shift(Function fun, double deltaX, double deltaY){
        func = fun;
        x = deltaX;
        y = deltaY;
    }

    public double getLeftDomainBorder() {
        return func.getLeftDomainBorder() + x;
    }

    public double getRightDomainBorder() {
        return func.getRightDomainBorder() + x;
    }

    public double getFunctionValue(double x) {
        return func.getFunctionValue(x) + y;
    }
}
