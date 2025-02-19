package ru.javarush.sikorskaya.cryptoanalizer;

public class Validator
{
    public static boolean isValid(String text)
    {
        return text.contains(" ") && text.contains(".") && text.contains(",");
    }
}