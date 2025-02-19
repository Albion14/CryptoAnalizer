package ru.javarush.sikorskaya.cryptoanalizer;

import java.util.*;

public class StatisticalAnalyzer
{

    public Map<Character, Integer> getCharacterStatistics(String text)
    {
        Map<Character, Integer> stats = new HashMap<>();
        for (char c : text.toCharArray())
        {
            if (Character.isLetter(c))
            {
                c = Character.toLowerCase(c);
                stats.put(c, stats.getOrDefault(c, 0) + 1);
            }
        }
        return stats;
    }

    public List<Character> getSortedCharactersByFrequency(Map<Character, Integer> stats)
    {
        List<Character> sortedChars = new ArrayList<>(stats.keySet());
        sortedChars.sort((a, b) -> stats.get(b) - stats.get(a));
        return sortedChars;
    }

    public String statisticalAnalysis(String encryptedText, Map<Character, Integer> stats)
    {
        Map<Character, Integer> encryptedStats = getCharacterStatistics(encryptedText);

        List<Character> encryptedSortedChars = getSortedCharactersByFrequency(encryptedStats);
        List<Character> statsSortedChars = getSortedCharactersByFrequency(stats);

        for (int i = 0; i < Math.min(3, encryptedSortedChars.size()); i++)
        {
            char encryptedChar = encryptedSortedChars.get(i);
            char statsChar = statsSortedChars.get(i);

            int key = encryptedChar - statsChar;

            Cipher cipher = new Cipher();
            String decryptedText = cipher.decrypt(encryptedText, key);

            if (isMeaningfulText(decryptedText))
            {
                return decryptedText;
            }
        }
        return "";
    }

    private boolean isMeaningfulText(String text)
    {
        return text.contains(" ") && text.matches(".*[аеёиоуыэюя].*");
    }
}