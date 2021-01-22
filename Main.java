import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args){
        int[] source = new int[10];
        for(int i=0; i < source.length; i++){
            source[i] = 10 - i;
        }
        // int[] dest = Arrays.copyOf(source, source.length);
        // for(int i = 0; i < dest.length;i++){
        //     System.out.println(dest[i]);
        // }
        // System.out.println(java.util.Arrays.copyOfRange(source,0,source.length - 1));
        System.out.println(java.util.Arrays.toString(source));
        java.util.Arrays.sort(source);
        System.out.println(java.util.Arrays.toString(source));
    }
}
