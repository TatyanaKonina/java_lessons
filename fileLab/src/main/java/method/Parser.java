package fileLab.src.main.java.method;

import java.io.*;

public class Parser {
    private String text;

    public String parser(String path){
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            // String str = in.readLine().trim();
            // text+= str;
            for (String line;(line = in.readLine()) != null;) {
                text+= line;
            }
            in.close();
            return text;
        } catch (IOException ex) {
            // System.out.println(ex.getMessage());
            return null;
        }
    }

    // private InputStream openFile(String path) throws FileNotFoundException {
    //     InputStream is = this.getClass().getResourceAsStream(path);
    //     return is;
    // }
}
