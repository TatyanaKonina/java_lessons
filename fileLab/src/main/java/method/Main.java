package fileLab.src.main.java.method;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class Main {
    private final static String inputDir = "";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userOutput = "";
        String text = "";
        for (int attempts = 0; attempts < 5 | !userOutput.equals("\\q") | text != null; attempts++) {
            System.out.println("Print file name:");
            userOutput = in.nextLine();
            Parser parser = new Parser();
            // userOutput = "../../resources/" + userOutput;
            text = parser.parser(userOutput);
            if (attempts == 0 & text == null) {
                System.out.println(
                    "We can't find your file in this dir\nPlease, write the whole path and we try it again");
            }
        }
    }
}
