package ru.javarush.sikorskaya.cryptoanalizer;

public class Cipher
{
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static char[] getAlphabet()
    {
        return ALPHABET;
    }

    public String encrypt(String text, int key)
    {
        return shiftText(text, key);
    }

    public String decrypt(String text, int key)
    {
        return shiftText(text, -key);
    }

    private String shiftText(String text, int key)
    {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray())
        {
            result.append(shiftCharacter(c, key));
        }
        return result.toString();
    }

    private char shiftCharacter(char c, int key)
    {
        int index = findIndexInAlphabet(c);
        if (index == -1) {
            return c;
        }
        int shiftedIndex = (index + key) % ALPHABET.length;
        if (shiftedIndex < 0)
        {
            shiftedIndex += ALPHABET.length;
        }
        return ALPHABET[shiftedIndex];
    }

    private int findIndexInAlphabet(char c)
    {
        for (int i = 0; i < ALPHABET.length; i++)
        {
            if (ALPHABET[i] == c)
            {
                return i;
            }
        }
        return -1;
    }
}