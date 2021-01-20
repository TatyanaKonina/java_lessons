import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner num = new Scanner(System.in);
        int first, second = 50;
        boolean isTrue = true;
        System.out.print("enter num: ");
        first = num.nextInt();

        isTrue = first == 51 ? true : false;

        System.out.println(isTrue);

        if (first > 50 || isTrue) 
            System.out.println("more than 50");
        else {
            System.out.println("lessl");
        }

        switch(first){
            case 51: 
                System.out.println("== 51");
                break;
            case 55 : {
                System.out.println(" == 55");
                break;
            }
            default : {
                System.out.println(first);
            }
        }
        
    }
}
