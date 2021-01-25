public class Main {
    public static void main(String[] args) {
        // Complex a = new Complex(5.0, 6.0);
        // Complex b = new Complex(-3.0, 4.0);
        // System.out.println("a            = " + a);
        // System.out.println("b            = " + b);
        // // System.out.println("Re(a)        = " + a.realPart;
        // // System.out.println("Im(a)        = " + a.imagPart);
        // System.out.println("b + a        = " + b.addition(a));
        // System.out.println("a - b        = " + a.subtraction(b));
        // System.out.println("a * b        = " + a.multiplication(b));
        // System.out.println("b * a        = " + b.multiplication(a));
        // System.out.println("a / b        = " + a.division(b));
        // System.out.println("(a / b) * b  = " + a.division(b).multiplication(b));
        // System.out.println("trigonometric form      = " + a.printTrigomometricForm());
        // System.out.println("algera form      = " + a.fromTrigonometric());

        Complex[][] d = { { new Complex(1), new Complex(2), new Complex(3) }, 
                          { new Complex(4), new Complex(5),new Complex(6) }, 
                          { new Complex(7), new Complex(8), new Complex(9)} };
        Matrix D = new Matrix(d);
        D.printMatrix();        
        System.out.println();
        Matrix B = D.transpose();
        B.printMatrix();        
        System.out.println();

        D.addition(D).printMatrix();
        System.out.println();

        D.subtraction(D).printMatrix();
        System.out.println();

        System.out.println(D.determinant1());


        
    }
}
