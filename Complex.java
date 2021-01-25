

public class Complex {
    private final double realPart;
    private final double imagPart;
    private final double angle;
    private final double abs;
    public Complex(){
        this.imagPart = 0.0;
        this.realPart = 0.0;
        this.angle = 0.0;
        this.abs = 0.0;
    }
    public Complex(double realPart,double imagPart){
        this.imagPart = imagPart;
        this.realPart = realPart;
        this.angle = Math.atan(this.imagPart/this.realPart);
        this.abs = Math.sqrt(Math.pow(realPart,2) + Math.pow(imagPart,2));
    }
    public Complex(double realPart){
        this.imagPart = 0.0;
        this.realPart = realPart;
        this.angle = 0.0;
        this.abs = 0.0;
    }
    public double getReal(){
        return this.realPart;
    }

    public double getImaginary(){
        return this.imagPart;
    }
    // public Complex makeAlgebraForm(double angle, double abs) {
    //     double imagPart = Math.cos(angle) * abs;
    //     double realPart = Math.sin(angle) * abs;
    //     return new Complex(realPart,imagPart);
    // }
    public Complex addition(Complex object){
        Complex a = this;
        return new Complex(a.realPart + object.realPart,a.imagPart + object.imagPart);
    }
    public static Complex addition(Complex x,Complex y){
        return new Complex(x.realPart + y.realPart,x.imagPart + y.imagPart);
    }
    public Complex subtraction(Complex object){
        Complex a = this;
        return new Complex(a.realPart - object.realPart,a.imagPart - object.imagPart);
    }
    public static Complex subtraction(Complex x,Complex y){
        return new Complex(x.realPart - y.realPart,x.imagPart - y.imagPart);
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
    public Complex sin() {
        return new Complex(Math.sin(realPart) * Math.cosh(imagPart), 
                           Math.cos(realPart) * Math.sinh(imagPart));
    }
    public Complex cos() {
        return new Complex(Math.cos(realPart) * Math.cosh(imagPart),
                           -Math.sin(realPart) * Math.sinh(imagPart));
    }
    public String printTrigomometricForm(){

        return String.format ("%.2f *(cos(%.2f) + i*sin(%.2f))\n",abs,angle,angle);
    }
    public String toString() {
        if (imagPart == 0) return String.format("%.2f",realPart);
        if (realPart == 0) return String.format("%.2f",imagPart);
        if (imagPart <  0) return String.format("%.2f - %.2fi",realPart,(-imagPart));
        return String.format("%.2f + %.2fi",realPart,imagPart);
    }

    public Complex fromTrigonometric(){
        double realPart = Math.cos(angle) * abs;
        double imagPart = Math.sin(angle) * abs;
        return new Complex(realPart,imagPart);
    }
    public Complex scale(double alpha) {
        return new Complex(alpha * realPart, alpha * imagPart);
    }
}
