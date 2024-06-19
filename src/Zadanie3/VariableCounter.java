package Zadanie3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableCounter {
    public static void main(String[] args) {
        String fileName = "src/Zadanie3/Test.java"; // miejsce do wstawienia pliku zamiast mojego
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int primitiveVariablesCount = 0;
            int stringVariablesCount = 0;

            while ((line = reader.readLine()) != null) {

                line = line.replaceAll("\"[^\"]*\"|//[^\\n]*|/\\*(.|\\n)*?\\*/", "");

                Matcher matcher = Pattern.compile("\\b(String|int|double|float|boolean|char|byte|short|long)\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)\\b").matcher(line);
                while (matcher.find()) {
                    String type = matcher.group(1);
                    String name = matcher.group(2);

                    if (type.equals("String")) {
                        stringVariablesCount++;
                    } else {
                        primitiveVariablesCount++;
                    }
                }
            }

            reader.close();

            System.out.println("Liczba prymitywnych zmiennych: " + primitiveVariablesCount);
            System.out.println("Liczba zmiennych typu String: " + stringVariablesCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

