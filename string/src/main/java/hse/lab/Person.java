package hse.lab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Person {

    private String surname;
    private String name;
    private String patronymic;
    private String date;

    Person(String surname, String name, String patronymic, String date) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date = date;
    }

    private String getGenger() {

        String[][] patronymicEnd = { { "овна", "евна", "ична" }, { "ович", "евич", "ич" } };
        String[][] surnameEnd = { { "ова", "ева", "ина", "ая", "яя", "екая", "цкая" },
                { "ов", "ев", "ин", "ын", "ой", "цкий", "ский", "цкой", "ской" } };

        Map<Integer, String> map = new HashMap<Integer, String>() {
            {
                put(0, "Женский");
                put(1, "Мужской");
                put(2, "Невозможно определить");
            }
        };
        int genderId = 2;
        if (patronymic.equals("нет")) {
            genderId = findEnd(surname, surnameEnd);
        } else {
            genderId = findEnd(patronymic, patronymicEnd);

        }
        return map.get(genderId);
    }

    private String getEnd(String str, int count) {
        return str.substring(str.length() - count, str.length());
    }

    private int findEnd(String str, String[][] ends) {
        for (int i = 0; i < ends.length; i++) {
            for (int j = 0; j < ends[i].length; j++) {
                if (ends[i][j].equals(getEnd(str, ends[i][j].length()))) {
                    return i;
                }
            }
        }
        return 2;
    }

    // Преобразование фамилии и инициалов с учетом регистра
    private String getInitials() {
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        String ini1 = name.substring(0, 1).toUpperCase() + ". ";
        if (patronymic.equals("нет")) {
            return surname + " " + ini1;
        } else {
            String ini2 = patronymic.substring(0, 1).toUpperCase() + ". ";
            return surname + " " + ini1 + ini2;
        }
    }

    private int getAge() {
        Calendar birthDay = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            birthDay.setTime(sdf.parse(date));// all done
        } catch (ParseException e) {
            throw new DateTimeException("Ошибка данных");
        }

        int age = Calendar.getInstance().get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) <= birthDay.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    private String getPostfix() {
        int age = getAge();
        int lastNumber = age % 10;
        boolean b = (age % 100 >= 11) && (age % 100 <= 14);
        String postfix = "";

        if (lastNumber == 1) {
            postfix = "год";
        } else if (lastNumber == 0 || lastNumber >= 5 && lastNumber <= 9) {
            postfix = "лет";
        } else if (lastNumber >= 2 && lastNumber <= 4) {
            postfix = "года";
        }
        if (b)
            postfix = "лет";
        return postfix;
    }

    // Вывод в консоль преобразованных данных в соответствии с условиями задачи
    @Override
    public String toString() {
        return getInitials() + "\n" + getAge() + " " + getPostfix() + "\n" + "Пол " + getGenger();
    }

}
