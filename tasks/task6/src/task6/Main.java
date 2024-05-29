package task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // задаем имя файла
        String fileName = "Kashtanka.txt";

        // создаем объект BufferedReader для чтения из файла
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла: " + e.getMessage());
            return;
        }

        // считываем текст из файла
        StringBuilder text = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
            return;
        }

        // закрываем файл
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка при закрытии файла: " + e.getMessage());
            return;
        }

        // задаем N
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N: ");
        int N = scanner.nextInt();
        scanner.close();

        // разбиваем текст на слова
        Pattern wordPattern = Pattern.compile("\\w+");
        Matcher wordMatcher = wordPattern.matcher(text);
        List<String> words = new ArrayList<>();
        while (wordMatcher.find()) {
            words.add(wordMatcher.group().toLowerCase());
        }

        // подсчитываем количество повторений каждого слова
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // выбираем слова, которые встречаются ровно N раз
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() == N) {
                result.add(entry.getKey());
            }
        }

        // выводим результат
        System.out.println("Слова, которые встречаются в тексте ровно " + N + " раз:");
        for (String word : result) {
            System.out.println(word);
        }
    }
}
