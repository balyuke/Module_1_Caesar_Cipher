package com.javarush.balyuke.demo;

import java.io.IOException;
import java.util.*;

import static com.javarush.balyuke.caesar.CaesarAlphabet.*;
import static com.javarush.balyuke.caesar.CaesarCipher.*;
import static com.javarush.balyuke.consoleui.constants.ConstantsPrint.*;
import static com.javarush.balyuke.demo.Utils.*;
import static com.javarush.balyuke.file.constants.ConstantsFiles.*;

// если закрыть Scanner, то вылетаешь из верхнего меню
public class CaesarCipherMain {

    public static void inputFile() throws IOException {
    System.out.println("\nWorking method inputFile");

    ArrayList<String> stringsInFile  = readFileToArrayList(DEMO_IN_FILE);
    if (stringsInFile.size() < 2) {
        System.out.println(PRINT_CHECK_FILE + DEMO_IN_FILE);
        return;
    }
    String input = null;
    //System.out.println("print stringsFile: " + stringsFile);

    int offset;
    if (!stringsInFile.get(0).isEmpty() && stringsInFile.get(0) != null)
        input = stringsInFile.get(0);

//        try {
//            //System.out.println(input);
//            offset = Integer.valueOf(input);
//            if (offset >= 0 && offset < lenAlph)
//                System.out.println("\nInput Offset value: " + offset);
//            else {
//                System.out.println("\nValue Offset must between 1 and " + (lenAlph - 1) + "\n");
//                System.out.println();
//                return;
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("\nmethod inputFile Unrecognized Offset option: " + input + "\n");
//            return;
//        }

    //заглушка
    offset = 3;

    stringsInFile.remove(input);
    System.out.println(stringsInFile);

    ArrayList<String> stringsOutFile  = readFileToArrayList(DEMO_OUT_FILE);
    for (String str : stringsInFile){
        String message = str;
        System.out.print("\nInput the text message to be encrypted: "+message);
        //String encryptMessage = encoding(message, offset);
        String encryptMessage = encrypt(message, offset);
        stringsOutFile.add(encryptMessage);
        System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage);
        System.out.print(PRINT_DECRYPTED_TEXT);
        //System.out.print(decoding(encryptMessage, offset));
        System.out.print(decrypt(encryptMessage, offset));
        System.out.println();
    }

    clearFileContent(DEMO_OUT_FILE);
    writeFileFromArrayList(DEMO_OUT_FILE, stringsOutFile, false);

}

    public static void inputManual() {
    System.out.println("\nWorking method inputManual");
    Scanner scanner = new Scanner(System.in);
    String input = null;
    try  {
        int offset = 0;
        System.out.print(PRINT_ENTER_OFFSET_VALUE);
        while(scanner.hasNext()) {
            input = scanner.nextLine();
            if (isInt(input)) {
                offset = Integer.parseInt(input);
                if (offset >= 0 && offset < LEN_ALPH) break;
                else System.err.println (PRINT_HINT_OFFSET + (LEN_ALPH -1)+ "\n");
            }
            else
                System.err.println ( PRINT_UNRECOGNIZED_INPUT_OPTION + input + "\n");
            System.out.print(PRINT_ENTER_OFFSET_VALUE);
        }
        System.out.print(PRINT_ENTER_THE_TEXT_MESSAGE_TO_BE_ENCRYPTED);
        String message = scanner.nextLine();

        // Crypt
        //String encryptMessage = encoding(message, offset);
        String encryptMessage = encrypt(message, offset);
        System.out.println(PRINT_ENCRYPTED_TEXT + ANSI_RED + encryptMessage + ANSI_RESET);

        // Decrypt
        System.out.print(PRINT_DECRYPTED_TEXT + ANSI_GREEN + decrypt(encryptMessage, offset) + ANSI_RESET);

        // Brute Force for Console
        System.out.println(PRINT_BRUTE_FORCE);
        List<String> listBruteForceConsole =  getListAllDecrypt(encryptMessage, ALPHABET_BRUTE);
        Set<String> setWordsDictFile = readFileToSetString(DICTIONARY_FILE);
        System.out.println(PRINT_ENCRYPTED_TEXT + encryptMessage + PRINT_COUNT_OFFSET + ALPHABET_BRUTE.length());
        int correctOffset = 0;
        int countWordMatches = 0;
        for(int i = 0; i < listBruteForceConsole.size(); i++) {
            System.out.println(PRINT_TRY_OFFSET + (listBruteForceConsole.size() - i - 1) + PRINT_DECRYPTED_TEXT + listBruteForceConsole.get(i) + "   ...i=" + i);
            //if (i == 38) {
            String s = replaceSymbol(listBruteForceConsole.get(i), ALPHABET_PUNCTUATION_MARKS);
            Set<String> setInputWords = getSetString(s);

            int countWords = countsWordsSet(setInputWords, setWordsDictFile);
            if (countWords > countWordMatches) {
                countWordMatches = countWords;
                correctOffset = i;
            }
            //}
        }

        System.out.println(PRINT_DECRYPTED_BRUTOFORCE_TEXT + ANSI_GREEN + listBruteForceConsole.get(correctOffset) + ANSI_RESET);

        System.out.println();
    }
    catch (NoSuchElementException e){
        System.out.println(e.getMessage());
    }
}


}