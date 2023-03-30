package functions.meta;

import functions.Function;

public class Composition implements Function {

    Function func1, func2;

    public Composition(Function fun1, Function fun2){
        func1 = fun1;
        func2 = fun2;
    }

    public double getLeftDomainBorder() {
        return func1.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return func2.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return func1.getFunctionValue(func2.getFunctionValue(x));
    }
}
