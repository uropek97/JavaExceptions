import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lesson3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arr[] = new String[6];
        try {
            arr = gettingData(scanner, 6);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        var arrayList = new ArrayList<>(Arrays.asList(arr));
        String sex = "";
        try {
            sex = getSex(arrayList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String data = "";
        try {
            data = getBirthDay(arrayList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String number = "";
        try {
            number = getPhoneNumber(arrayList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String lastname = "";
        try {
            lastname = getName(arrayList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String name= "";
        try {
            name = getName(arrayList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String patronymic = "";
        try {
            patronymic = getName(arrayList);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            write(lastname, name, patronymic, data, number, sex);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String[] gettingData(Scanner scanner, int len){
        System.out.print("Введите фимилию, имя, отчество, дату рождения(dd.mm.yyyy), номер телефона" +
                "(целое беззнаковое число без форматирования, пол (символ латиницей f или m). \n" +
                "Вводите данные, разделённые пробелом.\n" +
                "Ввод: ");
        var input = scanner.nextLine();
        var arr = input.split(" ");
        if(arr.length != len)
            throw new IllegalArgumentException("Неверное количество аргументов");
        return arr;
    }

    public static String getSex(ArrayList<String> arrayList){
        String sex = "";
        for(var str : arrayList){
            if(str.length()==1){
                if(str.equals("m")){
                    sex = "Мужской";
                    break;
                }
                if(str.equals("f")){
                    sex = "Женский";
                    break;
                }
            }

        }
        if(sex.equals(""))
            throw new IllegalArgumentException("Не удалось распознать пол");
        arrayList.remove(sex);
        return sex;
    }

    public static String getPhoneNumber(ArrayList<String> arrayList){
        var phoneNumber = "";
        for(var str : arrayList){
            if(isDigit(str)){
                phoneNumber = str;
                break;
            }
        }
        if(phoneNumber.equals(""))
            throw new IllegalArgumentException("Не удалось распознать номер телефона");
        arrayList.remove(phoneNumber);
        return phoneNumber;
    }

    public static boolean isDigit(String str){
        try {
            Long.parseLong(str);
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    public static String getBirthDay(ArrayList<String> arrayList){
        var birthDay = "";
        for(var str : arrayList){
            if(isData(str)){
                birthDay = str;
                break;
            }
        }
        if(birthDay.equals(""))
            throw new IllegalArgumentException("Не удалось распознать дату рождения");
        arrayList.remove(birthDay);
        return birthDay;
    }

    public static boolean isData(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);
        try{
            format.parse(str);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    public static String getName(ArrayList<String> arrayList){
        var name = "";
        for(var str : arrayList){
            if(str.length() > 1 & isName(str)){
                name = str;
                break;
            }
        }
        if(name.equals(""))
            throw new IllegalArgumentException("Неверно введённые ФИО");
        arrayList.remove(name);
        return name;
    }

    public static boolean isName(String str){
        return str.matches("^[a-zA-Zа-яА-ЯёЁ]+$");
    }

    public static void write(String lastname, String name, String patronymic, String birthDay, String phoneNumber, String sex){
        if(lastname.equals("") || name.equals("") || patronymic.equals("") || birthDay.equals("")
                || phoneNumber.equals("") || sex.equals(""))
            throw new RuntimeException("Неверные данные для записи в файл");
        String filename = lastname + ".txt";
        File file = new File(filename);
        try(FileWriter fw = new FileWriter(file, true)){
            fw.write(String.format("%s %s %s %s %s %s\n", lastname, name, patronymic, birthDay, phoneNumber, sex));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
