package functions;

import functions.basic.Cos;

import java.io.*;

public class TabulatedFunctions {

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws InappropriateFunctionPointException {

        //if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder())
            //throw new IllegalArgumentException();
        TabulatedFunction tab = new LinkedListTabulatedFunction(leftX, rightX, pointsCount);
        for(int i = 1; i < pointsCount; i++)
            tab.setPointY(i, function.getFunctionValue(tab.getPointX(i)));
        return tab;
    }

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        int countPoints = function.getPointsCount();
        out.write(countPoints);

        for (int i = 0; i <= countPoints; i++) {
            out.write((int) function.getPointX(i));
            out.write((int) function.getPointY(i));
        }
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException, InappropriateFunctionPointException {
        int type = in.read();
        double leftX = in.read();
        double rightX = in.read();
        int pointCount = in.read();
        TabulatedFunction fun;

        if (type == 1) {
            fun = new LinkedListTabulatedFunction(leftX, rightX, pointCount);
        }
        else {
            fun = new ArrayTabulatedFunction(leftX, rightX, pointCount);
        }
        return fun;
}

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException {
        out.write(function.getPointsCount() + " ");
        for(int i = 1; i <= function.getPointsCount(); i++) {
            out.write(function.getPointX(i) + " " + function.getPointY(i) + " ");
        }
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException, InappropriateFunctionPointException {
        StreamTokenizer read = new StreamTokenizer(in);
        int currentToken = read.nextToken();

        int pointCount = (int)read.nval;
        currentToken = read.nextToken();


        FunctionPoint[] points = new FunctionPoint[pointCount];
        int i = 0;
        while (currentToken != StreamTokenizer.TT_EOF){
            double x = read.nval;
            currentToken = read.nextToken();
            double y = read.nval;
            currentToken = read.nextToken();
            FunctionPoint point = new FunctionPoint(x, y);
            points[i] = point;
            i++;
        }
        double right = points[points.length-2].getX(), left = points[1].getX();

        TabulatedFunction fun = new LinkedListTabulatedFunction(left, right, points);
        fun.print();
        return fun;
    }

    }




