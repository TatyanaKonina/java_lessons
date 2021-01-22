import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int[]a;
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("enter n");
        n = in.nextInt();
        a = new int [n];
        for(int i = 0; i<n;i++){
            System.out.print("enter num");
            a[i] = in.nextInt();
        }
        System.out.println("len: " + a.length);

    }
}
