package file.src.main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static String resourcesPath = "/file/src/resources/";
    private final static String resultPath = "/file/src/result/";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userOutput = "";
        HashMap<Character, Integer> map = new HashMap<>();
        map = null;
        for (int attempts = 0; !(attempts > 5 | userOutput.equals("\\q") | map != null); attempts++) {
            System.out.println("Print file name:");
            userOutput = in.nextLine();
            Result result = new Result();
            if (attempts == 0) {
                userOutput = System.getProperty("user.dir") + resourcesPath + userOutput;
                System.out.println(userOutput);
            }
            map = result.parser(userOutput);
            if (map == null) {
                System.out.println(
                        "We can't find your file in current dir\nPlease, write the whole path and we try it again");
                continue;
            }
            else{

            }
        }

        // for (Map.Entry<Character, Integer> pair : map.entrySet()) {
        //     System.out.println(pair.getKey() + " " + pair.getValue());
        // }

    }
}
