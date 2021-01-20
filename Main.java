import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner num = new Scanner(System.in);
        int first,second,result;
        System.out.print("first num : ");
        first = num.nextInt();
        System.out.print("second num : ");
        second = num.nextInt();

        result = first + second;
        result ++;
        System.out.println(result);
    }
}
