package Java;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /** Необходимые данные для выполнения задачи **/

        String path = "test.txt";
        List<String> stringListOne = new ArrayList<>() {
        };
        List<String> stringListTwo = new ArrayList<>();
        int i;
        String result;

        /** Решение задачи **/
        try {
            stringListOne = fileToList(path);
        } catch (IOException e) {
            throw new RuntimeException("Не правильный путь к файлу");
        }


        i = cinValue();
        if(i >= stringListOne.size()){
            throw new RuntimeException("Число превышает количество строк в файле или равно максимальному");
        }

        stringListTwo = step(stringListOne,i);//Разделение на строки
        for (var el: stringListTwo) {
            System.out.println(el);
        }

        i = cinValue();
        if(i > stringListOne.size()){
            throw new RuntimeException("Число превышает количество оставшихся строк в файле.");
        }

        stringListOne = step(stringListOne,i);
        for (var el: stringListOne) {
            System.out.println(el);
        }

        result = equalsList(stringListTwo,stringListOne);
        System.out.println(result);

    }

    /** Конвертирует информацию из файла построчно в Список строк **/
    private static List<String> fileToList(String fileName) throws IOException {
        List<String> stringList = new ArrayList<>();
        File f = new File(fileName);

        /** Для создания файла в директории src если файла как такавого не существует, но подумал кому это надо :D **/
//        if(!f.exists()){
//            f.createNewFile();
//
//        }


        try (Scanner file = new Scanner(f)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (file.hasNextLine()) {
                stringBuilder.append(file.nextLine());
                stringList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }

    /** Разделение на массивы для текущей поставленной задачи **/
    private static List<String> step(List<String> string,int i){
        String[] mass = new String[i];
        for (int counter = 0; counter<i; counter++){
            mass[counter] = string.get(counter);
        }
        for (int counter = 0; counter<i; counter++){
            string.remove(0);
        }
        return Arrays.stream(mass).toList();
    }

    /** Ввод значения i пользователем с консоли **/
    private static int cinValue(){
        int i;
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            if (scanner.hasNextInt()){
                i = scanner.nextInt();
                break;
            } else {
                throw new RuntimeException("Число i не целочисленное или совсем не является числом");
            }
        }
        return i;
    }

    /** Сравнение списка строк **/

    private static String equalsList(List<String> listOne,List<String> listTwo){
        String resultList = new String("");

        for (int i = 0; i < listOne.size(); i++) {
            String word = listOne.get(i);
            String[] words = word.split(" ");
            for (String el:listTwo) {
                resultList += equals(word,el,resultList);
            }
        }

        return resultList;
    }

    /** Сравнение строк **/
    private static String equals(String fileString,String string,String stringBuilder){
        String[] words = fileString.split(" ");
        StringBuilder resultStringBuilder = new StringBuilder();
        int counter = 0;
        for (String el: words) {
            if(el.length() < 3){
                continue;
            } else if(string.contains( el )) {

                resultStringBuilder.append(fileString).append(" : ").append(string).append('\n');
                counter = 0;
                return resultStringBuilder.toString();

            }

        }
        if(stringBuilder.contains(resultStringBuilder)){
            return "";
        }
        return resultStringBuilder.append(fileString).append(":?").toString();
    }


}
