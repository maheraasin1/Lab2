package encryption2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // Method to encrypt a single word
    public static String encryptWord(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        StringBuilder prefix = new StringBuilder();
        StringBuilder suffix = new StringBuilder();

        // Handling special characters at the beginning and end of the word
        while (!Character.isLetterOrDigit(word.charAt(0))) {
            prefix.append(word.charAt(0));
            word = word.substring(1);
            if (word.isEmpty()) break;
        }

        while (!word.isEmpty() && !Character.isLetterOrDigit(word.charAt(word.length() - 1))) {
            suffix.append(word.charAt(word.length() - 1));
            word = word.substring(0, word.length() - 1);
            if (word.isEmpty()) break;
        }

        if (word.isEmpty()) {
            return prefix.toString() + suffix.reverse().toString();
        }

        // Processing the main part of the word
        word = word.toLowerCase();

        if (word.matches("\\d+")) {
            // Numbers: Add 2501
            int num = Integer.parseInt(word);
            return prefix + String.valueOf(num + 2501) + suffix;
        }

        if (word.length() == 1) {
            // Single letters: Shift to the next letter
            char letter = word.charAt(0);
            if (letter == 'z') {
                return prefix + "a" + suffix;
            }
            return prefix + String.valueOf((char) (letter + 1)) + suffix;
        }

        int evenLetters = 0;
        int oddLetters = 0;
        for (char letter : word.toCharArray()) {
            if (isEvenLetter(letter)) {
                evenLetters++;
            } else {
                oddLetters++;
            }
        }

        if (evenLetters >= 2) {
            return prefix + swapPairs(word) + suffix;
        }

        if (oddLetters >= 3) {
            return prefix + swapFirstAndLast(word) + suffix;
        }

        return prefix + word + suffix;
    }

    private static boolean isEvenLetter(char letter) {
        int position = letter - 'a' + 1;
        return position % 2 == 0;
    }

    private static String swapPairs(String word) {
        StringBuilder swapped = new StringBuilder(word);
        for (int i = 0; i < word.length() - 1; i += 2) {
            char temp = swapped.charAt(i);
            swapped.setCharAt(i, swapped.charAt(i + 1));
            swapped.setCharAt(i + 1, temp);
        }
        return swapped.toString();
    }

    private static String swapFirstAndLast(String word) {
        if (word.length() <= 1) return word;
        StringBuilder swapped = new StringBuilder(word);
        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        swapped.setCharAt(0, last);
        swapped.setCharAt(word.length() - 1, first);
        return swapped.toString();
    }

    // Method to process the file (read from input, write to output)
    public static void processFile(String inputFileName, String outputFileName) {
        try (Scanner fileReader = new Scanner(new File(inputFileName));
             FileWriter fileWriter = new FileWriter(outputFileName)) {

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] words = line.split("\\s+");
                StringBuilder encryptedLine = new StringBuilder();

                for (String word : words) {
                    encryptedLine.append(encryptWord(word)).append(" ");
                }

                fileWriter.write(encryptedLine.toString().trim() + System.lineSeparator());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        boolean encryptMore = true;

        while (encryptMore) {
            String inputFileName;
            String outputFileName;

            // Ask for input file until a valid file is entered
            while (true) {
                System.out.print("Enter input file name: ");
                inputFileName = inputScanner.nextLine();
                File inputFile = new File(inputFileName);
                if (inputFile.exists()) {
                    break;
                } else {
                    System.out.println("File not found. Please try again.");
                }
            }

            // Ask for output file name
            System.out.print("Enter output file name: ");
            outputFileName = inputScanner.nextLine();

            // Process the input file and write the output
            processFile(inputFileName, outputFileName);

            // Ask if the user wants to encrypt more files
            System.out.print("Would you like to encrypt more text (yes/no)? ");
            String response = inputScanner.nextLine().toLowerCase();
            encryptMore = response.equals("yes");
        }

        inputScanner.close();
    }
}


