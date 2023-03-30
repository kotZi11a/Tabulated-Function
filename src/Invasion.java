import functions.ArrayTabulatedFunction;
import functions.InappropriateFunctionPointException;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.IOException;

class MyClass
{
    public static void main(String[] args) throws InappropriateFunctionPointException, IllegalArgumentException, IOException {
        TabulatedFunction fun1 = new LinkedListTabulatedFunction(0, 10, 10);
        TabulatedFunction fun2 = new ArrayTabulatedFunction(0, 10, 10);
        TabulatedFunction fun3 = new ArrayTabulatedFunction(0, 5, 5);
        System.out.println(fun1.toString());
        System.out.println(fun2.toString());

        System.out.println(fun1.equals(fun1));
        System.out.println(fun1.equals(fun2));
        System.out.println(fun1.equals(fun3));

        System.out.println(fun1.hashCode());
        System.out.println(fun2.hashCode());
        System.out.println(fun3.hashCode());


    }
}
