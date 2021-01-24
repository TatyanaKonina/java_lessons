public class Main {
    public static void main(String[] args) {
        Complex a = new Complex(5.0, 6.0);
        Complex b = new Complex(-3.0, 4.0);

        System.out.println("a            = " + a);
        System.out.println("b            = " + b);
        // System.out.println("Re(a)        = " + a.realPart;
        // System.out.println("Im(a)        = " + a.imagPart);
        System.out.println("b + a        = " + b.addition(a));
        System.out.println("a - b        = " + a.subtraction(b));
        System.out.println("a * b        = " + a.multiplication(b));
        System.out.println("b * a        = " + b.multiplication(a));
        System.out.println("a / b        = " + a.division(b));
        System.out.println("(a / b) * b  = " + a.division(b).multiplication(b));
        System.out.println("trigonometric form      = " + a.printTrigomometricForm());
        System.out.println("algera form      = " + a.fromTrigonometric());
    }
}
