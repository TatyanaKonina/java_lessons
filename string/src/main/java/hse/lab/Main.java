package hse.lab;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name = "";
        String surname = "";
        String patronymic = "";
        String date = "";
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Введите имя");
            name = in.nextLine();
            System.out.println("Введите фамилию");
            surname = in.nextLine();
            System.out.println("Введите отчество, если его нет напишите 'нет' ");
            patronymic = in.nextLine();
            if (!(name.equals("") | surname.equals("") | patronymic.equals(""))) {
                break;
            }
            System.out.println("Все поля обязательны для заполнения, попробуйте снова");
        }
        while (true) {
            System.out.println("Введите дату рождения");
            date = in.nextLine();
            SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
            myFormat.setLenient(false);
            try {
                Calendar calendar = Calendar.getInstance();               
                calendar.setTime(myFormat.parse(date));
                Date personDate = calendar.getTime();
                Date currentDate = new Date();
                if(currentDate.before(personDate)){
                    throw new DateTimeException("Введенная дата рождения еще не наступила.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Введены некорректные данные о дате рождения\n" + "Пожалуйста, повторите ввод.");
            }
        }
        Person person = new Person(surname, name, patronymic, date);
        System.out.println(person);

    }
}
