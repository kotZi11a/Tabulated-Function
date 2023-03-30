package functions;
public class FunctionPoint
{
    private double x_cord, y_cord;

    public FunctionPoint(double x, double y)
    {
        x_cord = x;
        y_cord = y;
    }

    public FunctionPoint(FunctionPoint point)
    {
        x_cord = point.x_cord;
        y_cord = point.y_cord;
    }

    public FunctionPoint()
    {
        x_cord = 0;
        y_cord = 0;
    }

    public void setX(double x)
    {
        x_cord = x;
    }

    public void setY(double y)
    {
        y_cord = y;
    }

    public double getX()
    {
        return x_cord;
    }

    public double getY()
    {
        return y_cord;
    }
    
    public void printPoint()
    {
        System.out.println(x_cord + " " + y_cord);
    }

    public String toString(){return "(" + x_cord + "; " + y_cord + ")";}

    public boolean equals(Object o){return (o instanceof FunctionPoint && ((FunctionPoint) o).x_cord == x_cord && ((FunctionPoint) o).y_cord == y_cord);}

    public int hashCode(){
        int res1, res2;
        double a = x_cord + y_cord;
        long b = Double.doubleToLongBits(a);

        res1 = (int) b;
        b = (b >> 32);
        res2 =(int) b;
        return res1 + res2;
    }

    public Object clone(){
        FunctionPoint n = new FunctionPoint();
        n.x_cord = x_cord;
        n.y_cord = y_cord;
        return n;
    }
}
