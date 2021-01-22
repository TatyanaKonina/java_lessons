public class Person {
    public int height = 0;
    public String name = "Default";
    public void say (String name){
        System.out.println("Hello, " + name);
    }
    public Person(int h, String n){
        height = h;
        name = n;
    }
    public Person() {
        height = 180;
    }
    public Person(String n) {
        name = n;
    }

}