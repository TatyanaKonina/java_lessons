package fileLab.src.main.java.method;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static String inputDir = "/home/tdkonina/java_lessons/fileLab/src/main/java/method/";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userOutput = "";
        HashMap<Character, Integer> map = new HashMap<>();
        for (int attempts = 0; attempts < 5 | !userOutput.equals("\\q") | map == null; attempts++) {
            System.out.println("Print file name:");
            userOutput = in.nextLine();
            Parser parser = new Parser();
            if (attempts == 0 | map == null) {
                userOutput = inputDir + userOutput;
                map = parser.parser(userOutput);
                if (map == null) {
                    System.out.println(
                            "We can't find your file in current dir\nPlease, write the whole path and we try it again");
                } else {
                    for (Map.Entry<Character, Integer> pair : map.entrySet()) {
                        System.out.println(pair.getKey() + " " + pair.getValue());
                    }
                }
            }
        }
    }
}
