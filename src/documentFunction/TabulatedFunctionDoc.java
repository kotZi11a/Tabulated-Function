package documentFunction;

import functions.*;

public class TabulatedFunctionDoc {
    private TabulatedFunction function;
    String documentName;
    boolean saveFile = true;

    public void newFunction(double leftX, double rightX, int pointsCount) throws InappropriateFunctionPointException {
        TabulatedFunction fun = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        function = fun;
        saveFile = false;
    }

    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount) throws InappropriateFunctionPointException {
        TabulatedFunction fun;
        fun = TabulatedFunctions.tabulate(function,leftX,rightX,pointsCount);
        function = fun;
        saveFile = false;
    }


}
