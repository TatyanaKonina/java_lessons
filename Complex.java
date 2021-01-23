public class Complex {
    private final double realPart;
    private final double imagPart;
    public Complex(){
        this.imagPart = 0.0;
        this.realPart = 0.0;
    }
    public Complex(double realPart,double imagPart){
        this.imagPart = imagPart;
        this.realPart = realPart;
    }
    public Complex addition(Complex object){
        Complex a = this;
        return new Complex(a.imagPart + object.imagPart,a.realPart + object.realPart);
    }
    public static Complex addition(Complex x,Complex y){
        return new Complex(x.imagPart + y.imagPart,x.realPart + y.realPart);
    }
    public Complex subtraction(Complex object){
        Complex a = this;
        return new Complex(a.imagPart - object.imagPart,a.realPart - object.realPart);
    }
    public static Complex subtraction(Complex x,Complex y){
        return new Complex(x.imagPart - y.imagPart,x.realPart - y.realPart);
    }
    public Complex multiplication(Complex object) {
        Complex a = this;
        return new Complex(a.realPart * object.realPart - a.imagPart * object.imagPart, 
        a.realPart * object.imagPart + a.imagPart * object.realPart);
    }
    public static Complex multiplication(Complex x,Complex y) {
        return new Complex(x.realPart * y.realPart - x.imagPart * y.imagPart, 
        x.realPart * y.imagPart + x.imagPart * y.realPart);
    }

    public Complex division(Complex object) {
        Complex a = this;
        double denom = Math.pow(object.imagPart,2) + Math.pow(object.realPart,2);
        double realPart = (a.realPart * object.realPart + a.imagPart*object.imagPart) / denom;
        double imagPart = (object.realPart*a.imagPart - a.realPart * object.imagPart)/denom;
        return new Complex(realPart, imagPart);
    }
    public static Complex division(Complex x,Complex y) {
        double denom = Math.pow(y.imagPart,2) + Math.pow(y.realPart,2);
        double realPart = (x.realPart * y.realPart + x.imagPart*y.imagPart) / denom;
        double imagPart = (y.realPart*x.imagPart - x.realPart * y.imagPart)/denom;
        return new Complex(realPart, imagPart);
    }
    public double abs() {
        return Math.sqrt(Math.pow(realPart,2) + Math.pow(imagPart,2));
    }
    public double arg(){
        return Math.atan(imagPart/realPart);
    }

    public String printTrigomometricForm(){
        Complex a = this;
        return a.abs() + "(cos(" + a.arg() + ")" +
                       "i*sin(" + a.arg() + "))";
    }
    public String printComplex() {
        if (imagPart == 0) return realPart + "";
        if (realPart == 0) return imagPart + "i";
        if (imagPart <  0) return realPart + " - " + (-imagPart) + "i";
        return realPart + " + " + imagPart + "i";
    }
}
