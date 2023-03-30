package functions;
import java.lang.System;
public class ArrayTabulatedFunction implements TabulatedFunction
{
    private FunctionPoint[] points;

    public ArrayTabulatedFunction(double leftX, double rightX, FunctionPoint[] arrpoints) {

        if (leftX >= rightX || arrpoints.length < 2)
            throw new IllegalArgumentException("left >= right or points < 2");

        points = new FunctionPoint[arrpoints.length];
        double temp = leftX;
        double step = (rightX - leftX) / (arrpoints.length - 1);

        for (int i = 0; i < arrpoints.length; i++) {
            FunctionPoint a = arrpoints[i];
            points[i] = a;
            temp += step;
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException, InappropriateFunctionPointException
    {

        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException("left >= right or points < 2");

        points = new FunctionPoint[pointsCount];
        double temp = leftX;
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++)
        {
            FunctionPoint a = new FunctionPoint(temp, 0);
            points[i] = a;
            temp += step;
        }
    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException, InappropriateFunctionPointException
    {
        if (leftX >= rightX || values.length < 2)
            throw new IllegalArgumentException("left >= right or points < 2");

        points = new FunctionPoint[values.length];
        double temp = leftX;
        double step = (rightX - leftX) / (values.length - 1);

        for (int i = 0; i < values.length; i++) {
            FunctionPoint a = new FunctionPoint(temp, values[i]);
            points[i] = a;
            temp += step;
        }
    }

    public String toString(){
        String res = new String();
        for(int i = 0; i < points.length; i++) {
            res += "(";
            res += points[i].getX() + "; " + points[i].getY() + ") ";
        }
        return (res);
    }

    public boolean equals(Object o){
       if (!(o instanceof TabulatedFunction))
           return false;
       if (o instanceof ArrayTabulatedFunction && points.equals(((ArrayTabulatedFunction) o).points))
           return true;
       if (o instanceof LinkedListTabulatedFunction)
       {
           if (((LinkedListTabulatedFunction) o).getPointsCount() == points.length) {
               for (int i = 0; i < points.length; i++)
                   if (points[i].getX() != ((LinkedListTabulatedFunction) o).getPointX(i + 1) || points[i].getY() != ((LinkedListTabulatedFunction) o).getPointY(i + 1))
                       return false;
               return true;
           }
       }
       return false;
    }

    public int hashCode(){
        return points.hashCode();
    }

    public Object clone(){
        ArrayTabulatedFunction a = new ArrayTabulatedFunction(getLeftDomainBorder(),getRightDomainBorder(),points);
        return a;
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }

    public double getFunctionValue(double x) {

        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder())
        {

            for (int i = 0; i < points.length - 1; i++)
            {
                if (x >= points[i].getX() && x < points[i + 1].getX())
                {
                    FunctionPoint left = points[i];
                    FunctionPoint right = points[i + 1];
                    return left.getY() + ((right.getY() - left.getY()) / (right.getX() - left.getX())) * (x - left.getX());
                }
            }
        }
        return (0.0 / 0.0);
    }

    public int getPointsCount() {
        return points.length;
    }

    public FunctionPoint getPoint(int index) {
        if (index >= 0 && index < points.length)
        {
            return points[index];
        }
        throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {

        if (index >= 0 && index < points.length)
        {
            if(index == 0 && point.getX() < points[index + 1].getX())
                points[index] = point;
            else if(index == points.length - 1 && point.getX() > points[index - 1].getX())
                points[index] = point;
            else if(point.getX() > points[index - 1].getX() && point.getX() < points[index + 1].getX())
                points[index] = point;
            else

            throw new InappropriateFunctionPointException();
        }

        else throw new FunctionPointIndexOutOfBoundsException();
    }

    public double getPointX(int index) {
        if (index >= 0 && index < points.length) {
            return points[index].getX();
        }

        throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {

        if (index >= 0 && index < points.length)
        {
            if(index == 0 && x < points[index + 1].getX())
                points[index].setX(x);
            else if(index == points.length - 1 && x > points[index - 1].getX())
                points[index].setX(x);
            else if(x > points[index - 1].getX() && x < points[index + 1].getX())
                points[index].setX(x);
            else
                throw new InappropriateFunctionPointException();
        }

        else throw new FunctionPointIndexOutOfBoundsException();
    }

    public double getPointY(int index) {
        if (index >= 0 && index < points.length)
        {
            return points[index].getY();
        }

        throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPointY(int index, double y) {
        if (index >= 0 && index < points.length)
        {
            points[index].setY(y);
        }

        else throw new FunctionPointIndexOutOfBoundsException();
    }

    public void deletePoint(int index) {

        if (points.length < 3)
            throw new IllegalStateException();

        if (index >= 0 && index < points.length) {
            FunctionPoint[] massive = new FunctionPoint[points.length - 1];
            System.arraycopy(points, 0, massive, 0, index);
            System.arraycopy(points, index + 1, massive, index, points.length - index - 1);
            points = massive;
        }

        else throw new FunctionPointIndexOutOfBoundsException();
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        int index = 0;

        for (int i = 0; i < points.length; i++) {

            if (point.getX() == points[i].getX()) {
                throw new InappropriateFunctionPointException();
            }

            if (point.getX() > points[i].getX()) {
                index = i + 1;
            }
        }

            FunctionPoint[] massive = new FunctionPoint[points.length + 1];
            System.arraycopy(points, 0, massive, 0, index);
            massive[index] = point;
            System.arraycopy(points, index, massive, index + 1, points.length - index);
            points = massive;
    }

    public void print() {
        for (FunctionPoint point : points) {
            System.out.print(point.getX() + " ");
        }
        System.out.println();
        for (FunctionPoint point : points) {
            System.out.print(point.getY() + " ");
        }
        System.out.println();

    }

    public void printX() {

        for (FunctionPoint point : points) {
            System.out.print(point.getX() + " ");
        }
        System.out.println();

    }

    public void printY() {

        for (FunctionPoint point : points) {
            System.out.print(point.getY() + " ");
        }
        System.out.println();

    }
}
