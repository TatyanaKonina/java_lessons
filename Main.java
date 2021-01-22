import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args){
        System.out.println(func(3,4));
        //int temp = func(3,4);
    }
    public static int func (int a,int b) {
        int result = 1;
        for (int i = 0; i < b;i++){
            result *=a;
        }
        return result;
    }

}
