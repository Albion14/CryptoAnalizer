package ru.javarush.sikorskaya.cryptoanalizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager
{
    public static String readFile(String fileName)
    {
        try
        {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return "";
        }
    }

    public static void writeFile(String fileName, String content)
    {
        try
        {
            Files.write(Paths.get(fileName), content.getBytes());
        } catch (IOException e)
        {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}