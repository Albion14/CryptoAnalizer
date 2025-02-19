package ru.javarush.sikorskaya.cryptoanalizer;
import java.util.Map;
import java.util.Scanner;

public class CaesarCipher
{
    public static void main(String[] args)
    {
        Cipher cipher = new Cipher();
        BruteForce bruteForce = new BruteForce(cipher);
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Выберите действие:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка с ключом");
            System.out.println("3. Brute force");
            System.out.println("4. Статистический анализ");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Введите входной файл:");
                    String inputFile = scanner.nextLine();
                    System.out.println("Введите выходной файл:");
                    String outputFile = scanner.nextLine();
                    System.out.println("Введите ключ:");
                    int key = scanner.nextInt();
                    String text = FileManager.readFile(inputFile);
                    String encryptedText = cipher.encrypt(text, key);
                    FileManager.writeFile(outputFile, encryptedText);
                    System.out.println("Шифрование завершено. " +
                            "Результат записан в файл: " + outputFile);
                    break;
                case 2:
                    System.out.println("Введите входной файл:");
                    inputFile = scanner.nextLine();
                    System.out.println("Введите выходной файл:");
                    outputFile = scanner.nextLine();
                    System.out.println("Введите ключ:");
                    key = scanner.nextInt();
                    text = FileManager.readFile(inputFile);
                    String decryptedText = cipher.decrypt(text, key);
                    FileManager.writeFile(outputFile, decryptedText);
                    System.out.println("Расшифровка с ключом завершена. " +
                            "Результат записан в файл: " + outputFile);
                    break;
                case 3:
                    System.out.println("Введите входной файл:");
                    inputFile = scanner.nextLine();
                    System.out.println("Введите выходной файл:");
                    outputFile = scanner.nextLine();
                    text = FileManager.readFile(inputFile);
                    String bruteForceResult = bruteForce.bruteForce(text);
                    FileManager.writeFile(outputFile, bruteForceResult);
                    System.out.println("Расшифровка методом Brute force завершена. " +
                            "Результат записан в файл: " + outputFile);
                    break;
                case 4:
                    System.out.println("Введите зашифрованный файл:");
                    String encryptedFile = scanner.nextLine();
                    System.out.println("Введите файл для статистического анализа:");
                    String statsFile = scanner.nextLine();
                    System.out.println("Введите выходной файл:");
                    outputFile = scanner.nextLine();
                    String encryptedTextForAnalysis = FileManager.readFile(encryptedFile);
                    String statsText = FileManager.readFile(statsFile);
                    StatisticalAnalyzer analyzer = new StatisticalAnalyzer();
                    Map<Character, Integer> stats = analyzer.getCharacterStatistics(statsText);
                    String decryptedTextByStats = analyzer.statisticalAnalysis(encryptedTextForAnalysis, stats);
                    FileManager.writeFile(outputFile, decryptedTextByStats);
                    System.out.println("Расшифровка методом статистического анализа завершена. " +
                            "Результат записан в файл: " + outputFile);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}