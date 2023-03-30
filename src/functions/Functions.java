package functions;

import functions.meta.*;

public class Functions {

    public static Function shift(Function f, double shiftX, double shiftY){
        Shift f1 = new Shift(f, shiftX, shiftY);
        return f1;
    }

    public static Function scale(Function f, double scaleX, double scaleY){
        Scale f1 = new Scale(f, scaleX, scaleY);
        return f1;
    }

    public static Function power(Function f, double power){
        Power f1 = new Power(f, power);
        return f1;
    }

    public static Function sum(Function f1, Function f2){
        Sum fun = new Sum(f1, f2);
        return fun;
    }

    public static Function mult(Function f1, Function f2){
        Mult fun = new Mult(f1, f2);
        return  fun;
    }

    public static Function composition(Function f1, Function f2){
        Composition fun = new Composition(f1, f2);
        return fun;
    }
}
