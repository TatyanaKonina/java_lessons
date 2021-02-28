package hse.lab;

import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Result {
    private HashMap<Character, Integer> map = new HashMap<>();

    public HashMap<Character, Integer> parser(String path) {
        try {
            File file = new File(path);
            // создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            // создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                char[] myCharArray = line.toCharArray();
                for (char ch : myCharArray) {
                    if (map.containsKey(ch)) {
                        map.replace(ch, map.get(ch) + 1);
                    } else {
                        map.put(ch, 1);

                    }
                }
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            reader.close();
            return map;
        } catch (IOException ex) {
            return null;
        }
    }

    public void writeToFile(String pathToFile) {
        try {
            System.out.println("\nWriting to file: " + pathToFile);
            // Files.newBufferedWriter() uses UTF-8 encoding by default
            File file = new File(pathToFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            for (Map.Entry<Character, Integer> pair : map.entrySet()) {
                fileOutputStream.write((pair.getKey() + " " + pair.getValue() + "\n").getBytes());
                System.out.println(pair.getKey() + " " + pair.getValue());
            }
            fileOutputStream.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
