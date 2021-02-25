package fileLab.src.main.java.method;

import java.io.*;
import java.util.HashMap;

public class Parser {
    private HashMap<Character, Integer> map = new HashMap<>();

    public HashMap<Character, Integer> parser(String path) {
        try {
            FileInputStream file = new FileInputStream(new File(path));
            while (file.available() > 0) {
                char ch = (char) file.read();
                if (map.containsKey(ch)) {
                    map.replace(ch, map.get(ch) + 1);
                } else {
                    map.put(ch, 1);
                }
            }
            file.close();
            return map;
        } catch (IOException ex) {
            return null;
        }
    }
}
