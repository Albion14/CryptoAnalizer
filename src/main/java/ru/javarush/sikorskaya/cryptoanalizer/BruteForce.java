package ru.javarush.sikorskaya.cryptoanalizer;

public class BruteForce
{
    private final Cipher cipher;

    public BruteForce(Cipher cipher)
    {
        this.cipher = cipher;
    }

    public String bruteForce(String encryptedText)
    {
        char[] alphabet = Cipher.getAlphabet();
        for (int key = 0; key < alphabet.length; key++)
        {
            String decryptedText = cipher.decrypt(encryptedText, key);

            if (isMeaningfulText(decryptedText))
            {
                return decryptedText;
            }
        }
        return "Ключ не найден.";
    }

    private boolean isMeaningfulText(String text)
    {
        return text.contains(" ") && text.matches(".*[аеёиоуыэюя].*");
    }
}