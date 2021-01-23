public class Main {
    public static void main(String[]args){
        final int b = 11;
        //b = 55;//error
        int x = Math.mult(12,54);
        System.out.println(x);
        Math a = new Math();
        System.out.println(a.x);
        Math.x = 225;
        System.out.println(a.x);
        Math a1 = new Math();
        System.out.println(a1.x);
    }
}
