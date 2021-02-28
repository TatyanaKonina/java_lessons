package file.src.main.java.method;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static String inputDir = "/home/tdkonina/java_lessons/file/src/main/java/method/";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userOutput = "";
        HashMap<Character, Integer> map = new HashMap<>();
        map = null;
        for (int attempts = 0; !(attempts > 5 | userOutput.equals("\\q") | map != null); attempts++) {
            System.out.println(!(attempts > 5 | userOutput.equals("\\q") | map != null));
            userOutput = in.nextLine();
            Parser parser = new Parser();

            if (attempts == 0) {
                userOutput = inputDir + userOutput;
            }
            map = parser.parser(userOutput);
            if (map == null) {
                System.out.println(
                        "We can't find your file in current dir\nPlease, write the whole path and we try it again");
                continue;
            }
        }
        for (Map.Entry<Character, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }

    }
}
