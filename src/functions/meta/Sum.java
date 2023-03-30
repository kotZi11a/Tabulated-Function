package functions.meta;

import functions.Function;

public class Sum implements Function {

    private Function func1, func2;

    public Sum(Function fun1, Function fun2){
        func1 = fun1;
        func2 = fun2;
    }
    public double getLeftDomainBorder() {
        if (func1.getLeftDomainBorder() >= func2.getLeftDomainBorder())
            return func1.getLeftDomainBorder();
        else
            return func2.getLeftDomainBorder();
    }


    public double getRightDomainBorder() {
        if (func1.getRightDomainBorder() <= func2.getRightDomainBorder())
            return func1.getRightDomainBorder();
        else
            return func2.getRightDomainBorder();
    }


    public double getFunctionValue(double x) {
        return func2.getFunctionValue(x) + func1.getFunctionValue(x);
    }
}
