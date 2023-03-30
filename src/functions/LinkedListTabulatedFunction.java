package functions;

public class LinkedListTabulatedFunction implements TabulatedFunction {

    FunctionNode head;
    private int size;

    public LinkedListTabulatedFunction(double leftX, double rightX, FunctionPoint[] arrpoints){

        if (leftX >= rightX || arrpoints.length < 2)
            throw new IllegalArgumentException("left >= right or points < 2");

        FunctionNode points = new FunctionNode();
        head = points;
        double temp = leftX;
        double step = (rightX - leftX) / (arrpoints.length - 1);

        for (int i = 0; i <= arrpoints.length - 1; i++)
        {
            FunctionNodeaddNodeToTail();
            points = points.next;
            FunctionPoint a = arrpoints[i];
            points.data = a;
            temp += step;
        }
    }


    public FunctionNode FunctionNodegetNodeByIndex(int index){
        FunctionNode temp;
        temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

            return temp;
    }

    public FunctionNode FunctionNodeaddNodeToTail(){
        FunctionNode newnod = new FunctionNode();
        FunctionNode temp;
        temp = head.prev;
        temp.next = newnod;
        head.prev = newnod;
        newnod.next = head;
        newnod.prev = temp;
        size += 1;
        return newnod;
    }

    public FunctionNode FunctionNodeaddNodeByIndex(int index){

        if (index > size || index <= 0) throw new FunctionPointIndexOutOfBoundsException();

        FunctionNode newnod = new FunctionNode();
        FunctionNode temp;
        temp = head;

        if (index <= size/2) {

            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

            temp.prev.next = newnod;
            newnod.prev = temp.prev;
            temp.prev = newnod;
            newnod.next = temp;

            size += 1;
            return newnod;
        }

        else {
            index = size - index;

            for (int i = 0; i < index; i++) {
                temp = temp.prev;
            }

            temp.prev.next = newnod;
            newnod.prev = temp.prev;
            temp.prev = newnod;
            newnod.next = temp;

            size += 1;
            return newnod;
        }
    }

    public FunctionNode FunctionNodedeleteNodeByIndex(int index){
        FunctionNode temp, del;
        temp = head;

        if (index <= size/2) {

            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            del = temp;
            temp = del.prev;
            temp.next = del.next;
            temp.next.prev = temp;
            size -= 1;
            return temp;

        }

        else {
            index = size - index;

            for (int i = 0; i < index; i++) {
                temp = temp.prev;
            }

            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            size -= 1;
            return temp;

        }
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException, InappropriateFunctionPointException
    {
        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException("left >= right or points < 2");
        FunctionNode points = new FunctionNode();
        head = points;
        size = 0;
        double temp = leftX;
        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++){
            FunctionPoint a = new FunctionPoint(temp, 0);
            FunctionNodeaddNodeToTail().data = a;
            temp += step;
        }

    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException, InappropriateFunctionPointException
    {

        if (leftX >= rightX || values.length < 2)
            throw new IllegalArgumentException("left >= right or points < 2");

        FunctionNode points = new FunctionNode();
        head = points;
        double temp = leftX;
        double step = (rightX - leftX) / (values.length - 1);

        for (int i = 0; i < values.length; i++)
        {
            FunctionNodeaddNodeToTail();
            points = points.next;
            FunctionPoint a = new FunctionPoint(temp, values[i]);
            points.data = a;
            temp += step;
        }
    }

    public String toString(){
        String res = new String();
        FunctionNode temp = head.next;
        while (temp.data != null) {
            res += "(" + temp.data.getX() + "; " + temp.data.getY() + ") ";
            temp = temp.next;
        }
        return res;
    }

    public boolean equals(Object o){
        if (!(o instanceof TabulatedFunction))
            return false;
        if (o instanceof LinkedListTabulatedFunction && head.equals(((LinkedListTabulatedFunction) o).head))
            return true;
        if (o instanceof ArrayTabulatedFunction)
        {
            if (size == ((ArrayTabulatedFunction) o).getPointsCount()) {
                for (int i = 0; i < size; i++)
                    if (getPointX(i+1) != ((ArrayTabulatedFunction) o).getPointX(i) || getPointY(i+1) != ((ArrayTabulatedFunction) o).getPointY(i))
                        return false;
                return true;
            }
        }
        return false;
    }

    public int hashCode(){
        return head.hashCode();
    }

    public Object clone(){
        LinkedListTabulatedFunction a = null;

        try {
            a = new LinkedListTabulatedFunction(getLeftDomainBorder(), getRightDomainBorder(), getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }

        FunctionNode temp = head.next;

        for(int i = 1; i <= size; i++)
        {

            try {
                a.setPoint(i, temp.data);
            } catch (InappropriateFunctionPointException e) {
                throw new RuntimeException(e);
            }

            temp = temp.next;
        }

        return a;
    }

    public double getLeftDomainBorder() {
        return head.next.data.getX();
    }

    public double getRightDomainBorder() {
        return head.prev.data.getX();
    }

    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            int i = 1;

            while ((int) x > getPointX(i))
                i++;

            FunctionPoint left = FunctionNodegetNodeByIndex(i).data;
            FunctionPoint right = FunctionNodegetNodeByIndex(i).next.data;

            if (right == null)
                return getPointY(i);

            return left.getY() + ((right.getY() - left.getY()) / (right.getX() - left.getX())) * (x - left.getX());

        }
        throw new IllegalArgumentException();
    }

    public int getPointsCount() {
        return size ;
    }

    public FunctionPoint getPoint(int index) {
        return FunctionNodegetNodeByIndex(index).data;
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {

            if(index == 1 && point.getX() < FunctionNodegetNodeByIndex(index).next.data.getX())
                FunctionNodegetNodeByIndex(index).data = point;
            else if(index == size && point.getX() > FunctionNodegetNodeByIndex(index).prev.data.getX())
                FunctionNodegetNodeByIndex(index).data = point;
            else if(point.getX() > FunctionNodegetNodeByIndex(index).prev.data.getX() && point.getX() < FunctionNodegetNodeByIndex(index).next.data.getX())
                FunctionNodegetNodeByIndex(index).data = point;
            else
                throw new InappropriateFunctionPointException();

    }

    public double getPointX(int index) {
            return FunctionNodegetNodeByIndex(index).data.getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {

            if(index == 0 && x < FunctionNodegetNodeByIndex(index).next.data.getX())
                FunctionNodegetNodeByIndex(index).data.setX(x);
            else if(index == size && x > FunctionNodegetNodeByIndex(index).prev.data.getX())
                FunctionNodegetNodeByIndex(index).data.setX(x);
            else if(x > FunctionNodegetNodeByIndex(index).prev.data.getX() && x < FunctionNodegetNodeByIndex(index).next.data.getX())
                FunctionNodegetNodeByIndex(index).data.setX(x);
            else
                throw new InappropriateFunctionPointException();
    }

    public double getPointY(int index) {
            return FunctionNodegetNodeByIndex(index).data.getY();
    }

    public void setPointY(int index, double y) {
        FunctionNodegetNodeByIndex(index).data.setY(y);
    }

    public void deletePoint(int index) {
        if (size < 3)  throw new IllegalStateException();
        FunctionNodedeleteNodeByIndex(index);
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        int index = 0;
        for (int i = 1; i < size; i++) {
            if (point.getX() == FunctionNodegetNodeByIndex(i).data.getX())
            {
                throw new InappropriateFunctionPointException();
            }
            if (point.getX() > FunctionNodegetNodeByIndex(i).data.getX()){
                index = i + 1;
            }
        }

        FunctionNodeaddNodeByIndex(index).data = point;
    }

    public void print() {
        FunctionNode temp = head.next;

        while (temp.data != null)
        {
            System.out.print(temp.data.getX() + " ");
            temp = temp.next;
        }

        System.out.println();
        temp = temp.next;

        while (temp.data != null)
        {
            System.out.print(temp.data.getY() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void printX() {
        FunctionNode temp = head.next;
        while (temp.data != null)
        {
            System.out.print(temp.data.getX() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void printY() {
        FunctionNode temp = head.next;

        while (temp.data != null)
        {
            System.out.print(temp.data.getY() + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
