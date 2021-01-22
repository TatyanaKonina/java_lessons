
import java.util.Arrays;
public class Main {
    public static void main(String[] args){
        Person vitya = new Person(120,"vitya");
        System.out.println( vitya.name);
        Person vlad = new Person();
        System.out.println(vlad.name);
        vitya.say("Geaorge");
        Person dima = new Person("dIMA");
        System.out.println(dima.height);

        Student freshman = new Student(76,"ivan",2);
        freshman.tell();
    }
}
